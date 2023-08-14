package com.wentong.ladder.http;

import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.entity.MetaHttpRequestEntity;
import com.wentong.ladder.http.bodygen.FormBodyGenerator;
import com.wentong.ladder.http.bodygen.JSONBodyGenerator;
import com.wentong.ladder.http.bodygen.RequestBodyGenerator;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HttpGetter {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private RequestBodyGenerator requestBodyGenerator;

    private static final List<RequestBodyGenerator> GEN_LIST = List.of(new FormBodyGenerator(), new JSONBodyGenerator());

    @NotNull
    public static Response getResponse(MetaHttpRequestEntity metaHttpRequestEntity, JSONObject param) throws IOException {
        String requestType = metaHttpRequestEntity.getRequestType();
        if (Objects.equals(requestType, "GET")) {
            Request request = new Request.Builder()
                    .url(metaHttpRequestEntity.getUrl())
                    .build();
            return CLIENT.newCall(request).execute();
        } else if (Objects.equals(requestType, "POST")) {
            RequestBody body = getRequestBody(metaHttpRequestEntity.getContentType(), param);
            Request request = new Request.Builder()
                    .url(metaHttpRequestEntity.getUrl())
                    .post(body)
                    .build();
            return CLIENT.newCall(request).execute();

        } else {
            throw new IllegalArgumentException("不支持的协议类型" + requestType);
        }
    }

    private static RequestBody getRequestBody(String contentType, JSONObject param) {
        MediaType mediaType = MediaTypeContainer.map.get(contentType);
        if (mediaType == null) {
            throw new IllegalArgumentException("不支持的 contentType" + contentType);
        }

        return GEN_LIST.stream().filter(gen -> gen.isSupport(contentType)).findFirst().orElseThrow(() -> new IllegalArgumentException("不支持的 contentType" + contentType)).generate(param);
    }

}
