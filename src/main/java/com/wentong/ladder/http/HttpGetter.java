package com.wentong.ladder.http;

import com.alibaba.fastjson2.JSON;
import com.wentong.ladder.entity.MetaHttpRequestEntity;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class HttpGetter {

    @NotNull
    public static Response getResponse(MetaHttpRequestEntity metaHttpRequestEntity, Object o) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String requestType = metaHttpRequestEntity.getRequestType();
        if (Objects.equals(requestType, "GET")) {
            Request request = new Request.Builder()
                    .url(metaHttpRequestEntity.getUrl())
                    .build();
            return client.newCall(request).execute();
        } else if (Objects.equals(requestType, "POST")) {
            RequestBody body = RequestBody.create(JSON.toJSONString(o), MediaType.get(metaHttpRequestEntity.getContentType() + "; charset=" + metaHttpRequestEntity.getCharacterEncoding()));
            Request request = new Request.Builder()
                    .url(metaHttpRequestEntity.getUrl())
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                return response;
            }
        } else {
            throw new IllegalArgumentException("不支持的协议类型" + requestType);
        }
    }

}
