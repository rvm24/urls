package com.shortener.url.app.repository;
import org.springframework.data.repository.CrudRepository;

import com.shortener.url.app.model.URLEntity;
/**
 * 
 * @author RaviVarma
 *
 */
public interface URLRepository extends CrudRepository<URLEntity,String> {
	
}