package com.wentong.ladder.http;

import okhttp3.MediaType;

import java.util.Map;

public final class MediaTypeContainer {

    public static Map<String, MediaType> map = Map.of("application/json", MediaType.get("application/json; charset=utf-8"), "application/x-www-form-urlencoded", MediaType.get("application/x-www-form-urlencoded"));

}
