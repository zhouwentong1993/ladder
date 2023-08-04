package com.wentong.ladder.http;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public interface HttpRequestStrategy {

    Response sendRequest(Request request) throws IOException;

}
