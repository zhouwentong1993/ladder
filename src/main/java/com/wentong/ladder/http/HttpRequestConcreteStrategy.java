package com.wentong.ladder.http;

import com.wentong.ladder.exceptions.HttpRequestException;
import com.wentong.ladder.http.interceptor.HttpRequestInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpRequestConcreteStrategy implements HttpRequestStrategy {

    private final HttpRequestInterceptor interceptor;

    protected HttpRequestConcreteStrategy(HttpRequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public Response sendRequest(Request request) {
        try {
            interceptor.beforeRequest(request);
            Response response = doRequest(request);
            interceptor.afterRequest(request, response);
            return response;
        } catch (Exception e) {
            interceptor.exception(request, null, e);
            throw new HttpRequestException(e);
        }
    }

    public Response doRequest(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            return response;
        }
    }

}
