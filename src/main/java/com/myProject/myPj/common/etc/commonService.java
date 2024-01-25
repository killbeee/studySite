package com.myProject.myPj.common.etc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class commonService {
	
    public static  Map<String, String> parseQueryString(String queryString) throws UnsupportedEncodingException {
    	String decodedData = URLDecoder.decode(queryString, "UTF-8");
        Map<String, String> map = new HashMap<>();
        String[] pairs = decodedData.split("&");
        
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                map.put(key, value);
            }
        }

        return map;
    }
}
