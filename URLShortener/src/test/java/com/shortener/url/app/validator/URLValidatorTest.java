package com.shortener.url.app.validator;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
public class URLValidatorTest {
	private static final String LONG_URL="https://google.com/";
	@Autowired
	private URLValidator urlValidator;
	
	/**
     * 
     */
    @Test
    public void testURLValidator() {
    	//assert correct type/impl
        assertThat(urlValidator, instanceOf(URLValidator.class));
        assertEquals(urlValidator!=null,true);
    }
    
    @Test
    public void testValidateURL() {
    	assertEquals(urlValidator.validateURL(LONG_URL),true);
    }
}
