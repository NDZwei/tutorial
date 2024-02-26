package com.ndz.app.controller;

import com.ndz.app.dto.BaseResponse;
import org.springframework.http.HttpStatus;

/*
    author: NMDuc
    created_at: 2/23/2024
    github: https://github.com/NDZwei
*/
public abstract class BaseController {
    protected BaseResponse getResponse200() {
        BaseResponse rs = new BaseResponse();
        rs.setStatus(HttpStatus.OK.value());
        rs.setMessage(HttpStatus.OK.name());
        return rs;
    }

    protected BaseResponse getResponse200(String message) {
        BaseResponse rs = new BaseResponse();
        rs.setStatus(HttpStatus.OK.value());
        rs.setMessage(message);
        return rs;
    }

    protected BaseResponse getResponse200(Object object) {
        BaseResponse rs = new BaseResponse();
        rs.setData(object);
        rs.setStatus(HttpStatus.OK.value());
        rs.setMessage(HttpStatus.OK.name());
        return rs;
    }

    protected BaseResponse getResponse400() {
        BaseResponse rs = new BaseResponse();
        rs.setStatus(HttpStatus.BAD_REQUEST.value());
        rs.setMessage(HttpStatus.BAD_REQUEST.name());
        return rs;
    }

    protected BaseResponse getResponse400(String message) {
        BaseResponse rs = new BaseResponse();
        rs.setStatus(HttpStatus.BAD_REQUEST.value());
        rs.setMessage(message);
        return rs;
    }

    protected BaseResponse getResponse500() {
        BaseResponse rs = new BaseResponse();
        rs.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        rs.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return rs;
    }

    protected BaseResponse getResponse500(String message) {
        BaseResponse rs = new BaseResponse();
        rs.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        rs.setMessage(message);
        return rs;
    }
}
