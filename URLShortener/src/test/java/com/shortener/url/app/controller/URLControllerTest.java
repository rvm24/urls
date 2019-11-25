package com.shortener.url.app.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.view.RedirectView;

import com.shortener.url.app.AppConfig;

/**
 * 
 * @author RaviVarma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@EnableAutoConfiguration
public class URLControllerTest {
	@Autowired
	private URLController urlController;
	
	@Autowired
	private ShortenRequest shortenRequest;
	
	@Spy
	private HttpServletRequest request;
	
	@Spy
	private HttpServletResponse response;
	

	/**
     * 
     */
    @Test
    public void testURLController() {
        //assert correct type/impl
        assertThat(urlController, instanceOf(URLController.class));
        assertEquals(urlController!=null,true);
        assertEquals(shortenRequest!=null,true);
    }

	
	/**
	 * RequestMethod.POST
	 * @throws Exception
	 */
	@Test
    public void testShortenUrl() throws Exception {
		shortenRequest.setUrl("https://google.com");
		String shortUrl=urlController.shortenUrl(shortenRequest, request);
		assertEquals(shortUrl!=null,true);
    }
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRedirectUrl() throws Exception {
		shortenRequest.setUrl("https://google.com");
		String shortUrl=urlController.shortenUrl(shortenRequest, request);
		assertEquals(shortUrl!=null,true);
		
		RedirectView redirectView = urlController.redirectUrl(request, response);
		assertEquals(redirectView!=null,true);
		assertEquals(redirectView.getUrl()!=null,true);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRedirectUrlWithId() throws Exception {
		String id="abcdef123";
		shortenRequest.setUrl("https://google.com");
		String shortUrl=urlController.shortenUrl(shortenRequest, request);
		assertEquals(shortUrl!=null,true);
		
		RedirectView redirectView = urlController.redirectUrl(id,request, response);
		assertEquals(redirectView!=null,true);
		assertEquals(redirectView.getUrl()!=null,true);
	}
}