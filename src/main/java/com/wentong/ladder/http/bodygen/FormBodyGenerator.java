package com.wentong.ladder.http.bodygen;

import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.http.MediaTypeContainer;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.Objects;

public class FormBodyGenerator implements RequestBodyGenerator {

    @Override
    public RequestBody generate(JSONObject jsonObject) {
        FormBody.Builder builder = new FormBody.Builder();
        jsonObject.forEach((k, v) -> builder.add(k, v.toString()));
        return builder.build();
    }

    @Override
    public MediaType getMediaType() {
        return MediaTypeContainer.map.get("application/x-www-form-urlencoded");
    }

    @Override
    public boolean isSupport(String contentType) {
        return Objects.equals(contentType, "application/x-www-form-urlencoded");
    }


}
