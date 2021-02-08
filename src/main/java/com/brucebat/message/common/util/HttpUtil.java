package com.brucebat.message.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * http工具类
 *
 * @author Sun Tianyu
 * @version 1.0
 * @since Created in 2021/2/4
 */
@Slf4j
public class HttpUtil {

    private static final OkHttpClient OK_HTTP_CLIENT;

    static {
        //第三方的日志拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .retryOnConnectionFailure(false)
                .build();
    }


    /**
     * post请求
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @return 返回结果
     */
    public static String post(String url, Object requestParams) {
        try {
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(requestParams));
            Request request = new Request.Builder().url(url).post(requestBody).build();
            return doExecuteRequest(request);
        } catch (Exception e) {
            log.error("okhttp-post-error, exception : {}", e);
        }
        return null;
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @return 返回结果
     */
    public static String get(String url) {
        try {
            Request request = new Request.Builder().url(url).get().build();
            return doExecuteRequest(request);
        } catch (Exception e) {
            log.error("okhttp-get-error : {}", e);
        }
        return null;
    }


    /**
     * 进行实际请求调用
     *
     * @param request 返回请求
     * @return 返回结果
     */
    private static String doExecuteRequest(Request request) {
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error("url:{}, request:{}, response:{}, code:{}", request.url(), request.body(), response, response.code());
                return null;
            }
            return Objects.nonNull(response.body()) ? response.body().string() : null;
        } catch (Exception e) {
            log.error("okhttp-execute-error : {}", e);
        }
        return null;
    }

}
