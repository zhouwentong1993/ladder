package com.wentong.ladder.vo;

import com.alibaba.fastjson2.JSONObject;
import lombok.ToString;

/**
 * HTTP 映射标准对象
 */
@ToString
public class HttpMappingVo {
    public boolean success;
    public String message;
    public JSONObject data;
}
