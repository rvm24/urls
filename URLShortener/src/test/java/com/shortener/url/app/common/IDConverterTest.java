package com.shortener.url.app.common;

import com.shortener.url.app.AppConfig;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * 
 * @author RaviVarma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@EnableAutoConfiguration
public class IDConverterTest {
	
	private Long idVal=1L;
	
	//DI
    @Spy
    private IDConverter idConverter;
    
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
    
    /**
     * 
     */
    @Test
    public void testIDConverter() {
        //assert correct type/impl
        assertThat(idConverter, instanceOf(IDConverter.class));
        assertEquals(idConverter!=null,true);
        //assert true
        assertThat(IDConverter.charList!=null, is(true));
    }
    
    /**
     * 
     */
    @Test
    public void testcreateUniqueID() {
    	assertEquals(IDConverter.createUniqueID(idVal)!=null,true);
    }
}
