package com.shortener.url.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import javax.annotation.PreDestroy;


@Configuration
@EnableRedisRepositories(basePackages = "com.shortener.url.app.repository")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	@Autowired
    RedisConnectionFactory factory;
	
	@PreDestroy
    public void cleanRedis() {
        factory.getConnection()
            .flushDb();
    }
}