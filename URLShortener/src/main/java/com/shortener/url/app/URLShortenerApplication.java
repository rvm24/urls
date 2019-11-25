package com.shortener.url.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author RaviVarma
 * Spring boot Application to start
 */
@SpringBootApplication
public class URLShortenerApplication {
	/**
	 * 
	 * @param args
	 */
    public static void main(final String[] args) {
        SpringApplication.run(URLShortenerApplication.class, args);
    }
}
