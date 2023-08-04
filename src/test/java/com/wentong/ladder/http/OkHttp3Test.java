package com.wentong.ladder.http;

import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OkHttp3Test {

    @Test
    void testOkHttp3Get() throws Exception {
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        Assertions.assertEquals(200, response.code());
    }

    @Test
    void testOkHttp3Post() throws Exception {
        RequestBody body = RequestBody.create("中国的面积'[]", MediaType.get("application/json; charset=utf-8"));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    }

}
