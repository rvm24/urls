package com.shortener.url.app.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shortener.url.app.service.URLConverterService;
import com.shortener.url.app.validator.URLValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 
 * @author RaviVarma
 *
 */
@RestController
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
    private final URLConverterService urlConverterService;
    private static final String LOCAL_URL="http://localhost:8080/";
    

    /**
     * 
     * @param urlConverterService
     */
    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    /**
     * 
     * @param shortenRequest
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shortener", method=RequestMethod.POST, consumes = {"application/json"})
    public String shortenUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
        LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
        String longUrl = shortenRequest.getUrl();
        if (URLValidator.INSTANCE.validateURL(longUrl)) {
            String localURL = (request!=null && request.getRequestURL()!=null)?request.getRequestURL().toString():LOCAL_URL;
            String shortenedUrl = urlConverterService.shortenURL(localURL, shortenRequest.getUrl());
            LOGGER.info("Shortened url to: " + shortenedUrl);
            return shortenedUrl;
        }
        throw new Exception("Please enter a valid URL");
    }

    /**
     * 
     * @param id
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        LOGGER.info("Received shortened url to redirect: {}",id);
        String redirectUrlString = urlConverterService.getLongURLFromID(id);
        LOGGER.info("Original URL: " + redirectUrlString);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        return redirectView;
    }
    
    /**
     * 
     * @param id
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws Exception
     */
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public RedirectView redirectUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        RedirectView redirectView = new RedirectView();
        String[] urls=redirectView.getHosts();
        if(urls!=null && urls.length>0) {
        	 for(String url:urls) {
             	LOGGER.info("URL: " + url);	
             }
        }
        else {
        	redirectView.setUrl(LOCAL_URL);
        }
        return redirectView;
    }
}

/**
 * 
 * @author RaviVarma
 *
 */
@Component
class ShortenRequest{
    private String url;

    @JsonCreator
    public ShortenRequest() {

    }

    @JsonCreator
    public ShortenRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


