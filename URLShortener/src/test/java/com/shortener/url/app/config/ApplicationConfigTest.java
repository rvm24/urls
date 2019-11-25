package com.shortener.url.app.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shortener.url.app.AppConfig;

/**
 * 
 * @author RaviVarma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@EnableAutoConfiguration
public class ApplicationConfigTest {
	@Autowired
	private ApplicationConfig appconfig;
	
	/**
     * 
     */
    @Test
    public void testApplicationConfig() {
        //assert correct type/impl
        assertThat(appconfig, instanceOf(ApplicationConfig.class));
        assertEquals(appconfig!=null,true);
    }
	
	@Test
    public void testCleanRedis() {
	   assertEquals(true,appconfig.factory!=null);
       appconfig.cleanRedis();
	   assertEquals(true,appconfig.factory!=null);
    }
}