package com.wentong.ladder.http.interceptor;

import okhttp3.Request;
import okhttp3.Response;

public interface HttpRequestInterceptor {

    void beforeRequest(Request request);

    void afterRequest(Request request, Response response);

    void exception(Request request, Response response, Exception e);

}
