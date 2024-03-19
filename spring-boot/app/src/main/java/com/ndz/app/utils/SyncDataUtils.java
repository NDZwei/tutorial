package com.ndz.app.utils;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.entity.AdministrativeUnit;
import com.ndz.app.security.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

/*
    author: NMDuc
    created_at: 3/19/2024
    github: https://github.com/NDZwei
*/
public class SyncDataUtils {
    private static final Logger logger = LoggerFactory.getLogger(SyncDataUtils.class);

    public static HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer" + " " + token);
        headers.add("Accept", "application/json, text/plain, */*");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        return headers;
    }

    public static String postLogin() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");
            MultiValueMap<String, String> urlencoded = new LinkedMultiValueMap<>();
            urlencoded.add("username", Constants.username);//username
            urlencoded.add("password", Constants.password);//password
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(urlencoded, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JwtResponse> response = restTemplate.exchange(UrlConstants.URL_LOGIN_TO, HttpMethod.POST, request, JwtResponse.class);
            if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().getAccess_token();
            }
        } catch (Exception e) {
            logger.error("Error when call api login at : {}, error: {}", LocalDateTime.now(), e.getMessage());
        }
        return null;
    }

    public static void sendAdministrative(List<AdministrativeUnitDto> administrativeUnits) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String accessToken = postLogin();
            if(accessToken != null) {
                HttpHeaders headers = createHeaders(accessToken);
                HttpEntity<List<AdministrativeUnitDto>> request = new HttpEntity<>(administrativeUnits, headers);
                restTemplate.exchange(UrlConstants.URL_ADMINISTRATIVE_UNIT_SAVE_LIST, HttpMethod.POST, request, Boolean.class);
                logger.info("=====End sync administrative at: {}", LocalDateTime.now() + "=====");
            } else {
                logger.error("Token is null");
            }
        } catch (Exception e) {
            logger.error("Error sending administrative with error: {}", e.getMessage());
        }
    }
}
