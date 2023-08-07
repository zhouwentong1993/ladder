package com.wentong.ladder.http;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 本来想做成策略类，但是由于 OKHTTP 封装得不需要拆分 GET 和 POST 请求了，故只有一个实现类。
 */
public interface HttpRequestStrategy {

    Response sendRequest(Request request) throws IOException;

}
