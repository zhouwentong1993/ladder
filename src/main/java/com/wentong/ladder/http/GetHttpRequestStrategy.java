//package com.wentong.ladder.http;
//
//import com.wentong.ladder.http.interceptor.HttpRequestInterceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import java.io.IOException;
//
//public class GetHttpRequestStrategy extends AbstractHttpRequestStrategy {
//
//    protected GetHttpRequestStrategy(HttpRequestInterceptor interceptor) {
//        super(interceptor);
//    }
//
//    @Override
//    public Response doRequest(Request request) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        try (Response response = client.newCall(request).execute()) {
//            return response;
//        }
//    }
//}
