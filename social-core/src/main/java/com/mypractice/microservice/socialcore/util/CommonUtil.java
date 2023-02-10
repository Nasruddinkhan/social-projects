package com.mypractice.microservice.socialcore.util;

import org.springframework.util.StringUtils;

import java.util.Set;

public class CommonUtil {
    private CommonUtil(){}

    /**
     *
     * @param value
     * @return
     */
    public static Set<String> convertStringToSet(final String value){
        return StringUtils.commaDelimitedListToSet(value);
    }
}
