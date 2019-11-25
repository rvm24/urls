package com.shortener.url.app.model;

import java.io.Serializable;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * 
 * @author RaviVarma
 *
 */
@RedisHash("urlEntity")
public class URLEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 451470761714340995L;
	private static final Logger LOGGER = LoggerFactory.getLogger(URLEntity.class);
	
    
    @Id
	private Long id=new Random().nextLong();
    private String shortUrl;
    private String url;
    
    public URLEntity() {
    	this.url = "url";
        this.shortUrl = "shortUrl:";
    }

    /**
     * 
     * @param url
     * @param shortUrl
     */
    public URLEntity(String url,String shortUrl) {
    	this.url = url;
        this.shortUrl = shortUrl;
        LOGGER.info("url {} and shortUrl {}",this.url,this.shortUrl);
    }
    
    public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

    @Override
    public String toString() {
    	return "{id:"+id+",shortUrl:"+shortUrl+",url:"+url+"}";
    }
}
