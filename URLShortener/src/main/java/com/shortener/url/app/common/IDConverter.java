package com.shortener.url.app.common;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 
 * IDConvertor to generate unique id for the db 
 *
 */
@Component
public class IDConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(IDConverter.class);
	public static final List<Character> charList=initCharList();
    
   /**
    * 
    * @return
    */
    private static List<Character> initCharList() {
    	List<Character> indexToCharTable= new ArrayList<>();
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            indexToCharTable.add(c);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            indexToCharTable.add(c);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            indexToCharTable.add(c);
        }
        return indexToCharTable;
    }

    /**
     * 
     * @param id
     * @return
     */
    private static List<Integer> convertToBase62ID(Long idObj) {
        List<Integer> digits = new LinkedList<>();
        long id=idObj.longValue();
        while(id > 0) {
            int remainder = (int)(id % 62);
            ((LinkedList<Integer>) digits).addFirst(remainder);
            id /= 62;
        }
        LOGGER.info("id {}",id);
        return digits;
    }

    /**
     * 
     * @param id
     * @return
     */
    public static String createUniqueID(Long id) {
    	if(charList.size()==0) {
    		initCharList();
    	}
    	
        List<Integer> base62ID = convertToBase62ID(id);
        StringBuilder uniqueURLID = new StringBuilder();
        for (int digit: base62ID) {
        	LOGGER.info("digit {}",digit);
        	LOGGER.info("chartindex {}",charList.get(digit));
            uniqueURLID.append(charList.get(digit));
        }
        return uniqueURLID.toString();
    }
}
