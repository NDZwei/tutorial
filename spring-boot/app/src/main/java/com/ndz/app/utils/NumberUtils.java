package com.ndz.app.utils;

import org.springframework.util.ObjectUtils;

/*
    author: NMDuc
    created_at: 3/19/2024
    github: https://github.com/NDZwei
*/
public class NumberUtils {
    public static Double calculatePercent(Long numerator, Long denominator){
        if(denominator == null || denominator == 0 || numerator == null || numerator == 0) return  0d;
        if(!ObjectUtils.isEmpty(denominator)){
            double rate = ((double)numerator/(double)denominator) * 100;
            return Math.ceil(rate * 100) / 100;
        }
        return 0d;
    }
}
