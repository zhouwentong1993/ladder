package com.wentong.ladder.http.bodygen;

import com.alibaba.fastjson2.JSONObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 针对不同的 post 请求，构造 RequestBody。
 * 注意：每添加一个子类时，需要在 MediaTypeContainer 中添加对应的 MediaType。
 */
public interface RequestBodyGenerator {

    RequestBody generate(JSONObject jsonObject);

    MediaType getMediaType();

    boolean isSupport(String contentType);

}
