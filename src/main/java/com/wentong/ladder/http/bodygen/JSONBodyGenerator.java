package com.wentong.ladder.http.bodygen;

import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.http.MediaTypeContainer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class JSONBodyGenerator implements RequestBodyGenerator {

    @Override
    public RequestBody generate(JSONObject jsonObject) {
        return RequestBody.create(jsonObject.toJSONString(), getMediaType());
    }

    @Override
    public MediaType getMediaType() {
        return MediaTypeContainer.map.get("application/json");
    }

}
