package com.wentong.ladder.http.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class HttpRequestLoggingInterceptor implements HttpRequestInterceptor {

    @Override
    public void beforeRequest(Request request) {
        log.info("request url:{}", request.url());
        log.info("request method:{}", request.method());
        log.info("request body:{}", request.body());

    }

    @Override
    public void afterRequest(Request request, Response response) {
        log.info("request url:{}", request.url());
        log.info("request body:{}", request.body());
        log.info("response code:{}", response.code());
    }

    @Override
    public void exception(Request request, Response response, Exception e) {
        log.info("request url:{}", request.url());
        log.info("request method:{}", request.method());
        log.info("request body:{}", request.body());
        log.info("response code:{}", response.code());
        log.error("exception:", e);

    }
}
