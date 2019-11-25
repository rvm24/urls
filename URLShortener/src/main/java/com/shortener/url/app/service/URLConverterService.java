package com.shortener.url.app.service;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.url.app.common.IDConverter;
import com.shortener.url.app.model.URLEntity;
import com.shortener.url.app.repository.URLRepository;

/**
 * 
 * @author RaviVarma
 *
 */
@Service
public class URLConverterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLConverterService.class);
    
    @Autowired
    private URLRepository urlRepository;
    
    /**
     * formatLocalURLFromShortener -  removes the endpoint (last index)
     * @param localURL
     * @return
     */
    private String getAppURL(String localURL) {
    	LOGGER.info("Local URL {}", localURL);
        String[] addressComponents = localURL.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
            if(i==0)sb.append("//");
        }
        sb.append('/');
        return sb.toString();
    }
    
    /**
     * 
     * @param localURL
     * @param longUrl
     * @return
     */
    public String shortenURL(String localURL, String longUrl) {
        LOGGER.info("shortenURL >> Local URL {}", localURL);
        LOGGER.info("shortenURL >> Shortening {}", longUrl);
        URLEntity urlEntity =null;
        String shortenedURL=null;
        String uniqueID = "";
        Iterator<URLEntity> itrURLEntity=urlRepository.findAll().iterator();
        
        while(itrURLEntity.hasNext()) {
    		urlEntity=itrURLEntity.next();
    		if(urlEntity.getUrl().equals(longUrl)) {
    			LOGGER.debug("shortenURL >> Id {}",urlEntity.getId());
        		LOGGER.debug("shortenURL >> Short URL {}",urlEntity.getShortUrl());
        		LOGGER.debug("shortenURL >> Long URL {}",urlEntity.getUrl());
        		shortenedURL=urlEntity.getShortUrl();
    		}
    	}
        
        if(shortenedURL==null) {
        	 while(uniqueID.equals("")) {
             	uniqueID = IDConverter.createUniqueID(new URLEntity().getId());
             }
             LOGGER.debug("shortenURL >> uniqueID {}", uniqueID);
             String baseString = getAppURL(localURL);
             LOGGER.debug("shortenURL >> baseString {}", baseString);
             shortenedURL = baseString + uniqueID;
             LOGGER.debug("shortenURL >> Shortened {}", shortenedURL);
             urlRepository.save(new URLEntity(longUrl,shortenedURL));
        }
        LOGGER.info("shortenURL >> Shortened {}", shortenedURL);
        return shortenedURL;
    }

    /**
     * 
     * @param uniqueID
     * @return
     * @throws Exception
     */
    public String getLongURLFromID(String uniqueID) throws Exception {
        LOGGER.info("getLongURLFromID >> uniqueID {}",uniqueID);
    	URLEntity urlEntity =null;
    	Iterator<URLEntity> itr=null;
    	LOGGER.info("getLongURLFromID >> uniqueID {}",urlRepository.findById(uniqueID).isPresent());
    	if(urlRepository.findById(uniqueID)!=null && urlRepository.findById(uniqueID).isPresent()) {
    		LOGGER.debug("getLongURLFromID >> Id {}",urlRepository.findById(uniqueID));
    		urlEntity = urlRepository.findById(uniqueID).get();
        	LOGGER.debug("getLongURLFromID >> Long URL {}",urlEntity.getUrl());
        	LOGGER.debug("getLongURLFromID >> Short URL {}",urlEntity.getShortUrl());
        	LOGGER.debug("getLongURLFromID >> Long URL {}",urlEntity.getId());
    	}
    	else {
    		itr=urlRepository.findAll().iterator();
	    	while(itr.hasNext()) {
	    		urlEntity=itr.next();
	    		if(urlEntity.getId().toString().equals(uniqueID)) {
	        		LOGGER.debug("getLongURLFromID >> Id 2 {}",urlEntity.getId());
	        		LOGGER.debug("getLongURLFromID >> Short URL 2 {}",urlEntity.getShortUrl());
	        		LOGGER.debug("getLongURLFromID >> Long URL 2 {}",urlEntity.getUrl());
	    			break;
	    		}
	    	}
    	}
        LOGGER.info("Converting shortened URL back to {}", urlEntity.getUrl());
        return urlEntity.getUrl();
    }
}
