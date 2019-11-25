package com.shortener.url.app.service;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.shortener.url.app.AppConfig;
import com.shortener.url.app.model.URLEntity;
import com.shortener.url.app.repository.URLRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@EnableAutoConfiguration
public class URLConverterServiceTest {
	private static final String LOCAL_URL="http://localhost:8080/";
	private static final String LONG_URL="https://google.com/";
	
	@Autowired
	private URLConverterService urlConverterService;
	
	@Autowired
	private URLRepository urlRepository;
	
	
	/**
     * 
     */
    @Test
    public void testURLConverterService() {
        //assert correct type/impl
        assertThat(urlConverterService, instanceOf(URLConverterService.class));
        assertEquals(urlConverterService!=null,true);
        assertEquals(urlRepository!=null,true);
    }
	
    /**
     * 
     */
	@Test
	public void testShortenURL() {
		String url=urlConverterService.shortenURL(LOCAL_URL, LONG_URL);
		assertEquals(url!=null,true);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetLONG_URLFromID() throws Exception {
		String uniqueId=null;
		URLEntity urlEntity=null;
		String url=urlConverterService.shortenURL(LOCAL_URL, LONG_URL);
		assertEquals(url!=null,true);
		Iterator<URLEntity> itrUrls=urlRepository.findAll().iterator();
		while(itrUrls.hasNext()) {
			urlEntity=itrUrls.next();
			if(urlEntity.getUrl().equals(LONG_URL)) {
				uniqueId=urlEntity.getId().toString();
				break;
			}
		}
		assertEquals(uniqueId!=null,true);
		assertEquals(LONG_URL,urlConverterService.getLongURLFromID(uniqueId));
	}
}
