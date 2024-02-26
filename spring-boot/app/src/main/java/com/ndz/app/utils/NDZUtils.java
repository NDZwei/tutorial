package com.ndz.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;

/*
    author: NMDuc
    created_at: 2/23/2024
    github: https://github.com/NDZwei
*/
public class NDZUtils {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static String IMAGE_PATH = "";
    public static final String SECRET_KEY = "secret-key-ndz-course";
    public static final Integer TIME_OUT_TOKEN = 12;
    public static final Integer TIME_OUT_REFRESH_TOKEN = 24;

    public static String getHashPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean matchesPassword(String oldPassword, String newPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(newPassword, oldPassword);
    }

    public static Double calculatePercent(Long numerator, Long denominator){
        if(denominator == null || denominator == 0 || numerator == null || numerator == 0) return  0d;
        if(!ObjectUtils.isEmpty(denominator)){
            double rate = ((double)numerator/(double)denominator) * 100;
            return Math.ceil(rate * 100) / 100;
        }
        return 0d;
    }

}
