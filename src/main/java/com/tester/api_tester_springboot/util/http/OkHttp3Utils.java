package com.tester.api_tester_springboot.util.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tester.api_tester_springboot.util.RcLoggerUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.BufferedSink;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp3Utils
 *
 * @Author 王海虹
 *
 */
@Slf4j
public class OkHttp3Utils {

    private static final Logger logger = LogManager.getLogger(OkHttp3Utils.class);

    private static final Map<String, String> HEADERS_MAP = new HashMap<String, String>();
    private static final Map<String, Integer> DEFAULD_OUT_TIME_MAP = new HashMap<String, Integer>();
    private static final String CONNECTTIMEOUT = "connectTimeOut";
    private static final String READTIMEOUT = "readTimeOut";
    private static final String WRITETIMEOUT = "writeTimeOut";
    private final static OkHttpClient CLIENT = new OkHttpClient();
    static {
        HEADERS_MAP.put("Connection", "close");
        HEADERS_MAP.put("Content-Type", "application/json;charset=UTF-8");
        HEADERS_MAP.put("User-Agent", "okHttpClient");

        DEFAULD_OUT_TIME_MAP.put(CONNECTTIMEOUT, 1000);
        DEFAULD_OUT_TIME_MAP.put(READTIMEOUT, 1000);
        DEFAULD_OUT_TIME_MAP.put(WRITETIMEOUT, 1000);

    }
    /**
     * 自定义超时时间
     * @param connectTimeOut
     * @param readTimeOut
     * @param writeTimeOut
     * @return
     */
    public static Map<String, Integer> setTimeOutParam(int connectTimeOut, int readTimeOut, int writeTimeOut){
        Map<String, Integer> timeOutMap = new HashMap<String, Integer>();
        timeOutMap.put(CONNECTTIMEOUT, connectTimeOut);
        timeOutMap.put(READTIMEOUT, readTimeOut);
        timeOutMap.put(WRITETIMEOUT, writeTimeOut);
        return timeOutMap;
    }

    private void OkHttp3Util() {

    }

    public static Response okHttpPost(String url, String json, Map<String, Integer> timeOutMap, Map<String, String> headers) throws Exception {
        long startTime = System.currentTimeMillis();

        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        setTimeOut(clientBuilder, timeOutMap);
        //此处不做mediaType设置, 已经在请求头中做了设置
        Request.Builder builder;
        if (StringUtils.isBlank(json)) {
            builder = new Request.Builder().url(url).post(requestBody());
        } else {
            builder = new Request.Builder().url(url).post(RequestBody.create(null, json));

        }
        if(headers!=null && headers.size()>0) {
            //设置请求头
            buildHeader(builder, headers);
        } else {
            buildHeader(builder, HEADERS_MAP);
        }

        Request request = builder.build();
        Response response = clientBuilder.build().newCall(request).execute();

//            RcLoggerUtils.recordReq(logger, "okHttpPost", startTime, "请求地址:" + url + "请求参数:" + json + ";result:" + JSONObject.toJSONString(response));
        return response;
    }

    public static Response okHttpDelete(String url, String json, Map<String, Integer> timeOutMap, Map<String, String> headers) throws Exception {
        long startTime = System.currentTimeMillis();

        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        setTimeOut(clientBuilder, timeOutMap);
        //此处不做mediaType设置, 已经在请求头中做了设置
        Request.Builder builder;
        if (StringUtils.isBlank(json)) {
            builder = new Request.Builder().url(url).delete(requestBody());
        } else {
            builder = new Request.Builder().url(url).delete(RequestBody.create(null, json));

        }
        if(headers!=null && headers.size()>0) {
            //设置请求头
            buildHeader(builder, headers);
        } else {
            buildHeader(builder, HEADERS_MAP);
        }

        Request request = builder.build();
        Response response = clientBuilder.build().newCall(request).execute();

        RcLoggerUtils.recordReq(logger, "okHttpDelete", startTime, "请求地址:" + url + "请求参数:" + json + ";result:" + JSONObject.toJSONString(response));
        return response;
    }

    public static Response okHttpPut(String url, String json, Map<String, Integer> timeOutMap, Map<String, String> headers) throws Exception {
        long startTime = System.currentTimeMillis();

        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        setTimeOut(clientBuilder, timeOutMap);
        //此处不做mediaType设置, 已经在请求头中做了设置
        Request.Builder builder;
        if (StringUtils.isBlank(json)) {
            builder = new Request.Builder().url(url).put(requestBody());
        } else {
            builder = new Request.Builder().url(url).put(RequestBody.create(null, json));

        }
        if(headers!=null && headers.size()>0) {
            //设置请求头
            buildHeader(builder, headers);
        } else {
            buildHeader(builder, HEADERS_MAP);
        }

        Request request = builder.build();
        Response response = clientBuilder.build().newCall(request).execute();

        RcLoggerUtils.recordReq(logger, "okHttpPut", startTime, "请求地址:" + url + "请求参数:" + json + ";result:" + JSONObject.toJSONString(response));
        return response;
    }

    public static String formOkHttpPost(String url, Map<String,String> formDataMap, Map<String, Integer> timeOutMap, Map<String, String> headers) throws Exception {
        long startTime = System.currentTimeMillis();
        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        setTimeOut(clientBuilder, timeOutMap);

        //post方式提交的数据
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        for(Map.Entry<String,String> entry : formDataMap.entrySet()){
            formBodyBuilder.add(entry.getKey(),entry.getValue());
        }

        Request.Builder builder = new Request.Builder().url(url).post(formBodyBuilder.build());

        if(headers!=null && headers.size()>0) {
            //设置请求头
            buildHeader(builder, headers);
        }

        Request request = builder.build();
        Response response = clientBuilder.build().newCall(request).execute();

        RcLoggerUtils.recordReq(logger, "formOkHttpPost", startTime, "请求地址:" + url + "请求参数:" + JSON.toJSONString(formDataMap) + ";result:" + JSONObject.toJSONString(response));
        return resultString(response);
    }

    /**
     * form表单提交，参数encoded
     * @param url
     * @param formDataMap
     * @param timeOutMap
     * @param headers
     * @return
     * @throws Exception
     */
    public static String formParamEncodedOkHttpPost(String url, Map<String,String> formDataMap, Map<String, Integer> timeOutMap, Map<String, String> headers) throws Exception {
        long startTime = System.currentTimeMillis();
        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        setTimeOut(clientBuilder, timeOutMap);

        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        for(Map.Entry<String,String> entry : formDataMap.entrySet()){
            formBodyBuilder.addEncoded(entry.getKey(),entry.getValue());
        }

        Request.Builder builder = new Request.Builder().url(url).post(formBodyBuilder.build());

        if(headers!=null && headers.size()>0) {
            buildHeader(builder, headers);
        }

        Request request = builder.build();
        Response response = clientBuilder.build().newCall(request).execute();
        RcLoggerUtils.recordReq(logger, "formParamEncodedOkHttpPost", startTime, "请求地址:" + url + "请求参数:" + JSON.toJSONString(formDataMap) + ";result:" + JSONObject.toJSONString(response));
        return resultString(response);
    }

    public static String stringMultipartStringOkHttpPost(String url, Object object, Map<String, Integer> timeOutMap, Map<String, String> headers) throws Exception {
        Response response = multipartOkHttpPost(url, object2String(object), timeOutMap, headers);
        return resultString(response);
    }

    /**
     * multipart/form-data格式请求
     *
     * @param
     * @param json
     * @param timeOutMap
     * @return
     */
    public static Response multipartOkHttpPost(String url, String json, Map<String, Integer> timeOutMap, Map<String, String> headers) throws Exception {
        long startTime = System.currentTimeMillis();

        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        setTimeOut(clientBuilder, timeOutMap);

        //post方式提交的数据
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        JSONObject jsonObject = JSONObject.parseObject(json);
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            formBodyBuilder.add(key, String.valueOf(jsonObject.get(key)));
        }
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .header("Connection", "close").header("User-Agent", "okHttpClient")
                .url(url)
                .post(requestBody)
                .build();

        Response response = clientBuilder.build().newCall(request).execute();
        RcLoggerUtils.recordReq(logger, "multipartOkHttpPost", startTime, "请求地址:" + url + "请求参数:" + json + ";result:" + JSONObject.toJSONString(response));
        return response;
    }

    private static RequestBody requestBody() {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                // not do anything
            }
        };
    }

    private static void setTimeOut(OkHttpClient.Builder clientBuilder, Map<String, Integer> timeOutMap) {

        if (timeOutMap == null) {
            timeOutMap = DEFAULD_OUT_TIME_MAP;
        }
        Integer connectTimeOut = timeOutMap.get(CONNECTTIMEOUT);
        Integer readTimeOut = timeOutMap.get(READTIMEOUT);
        Integer writeTimeOut = timeOutMap.get(WRITETIMEOUT);

        clientBuilder.connectTimeout(connectTimeOut == null ? DEFAULD_OUT_TIME_MAP.get(CONNECTTIMEOUT) : connectTimeOut, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeOut == null ? DEFAULD_OUT_TIME_MAP.get(READTIMEOUT) : readTimeOut, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeOut == null ? DEFAULD_OUT_TIME_MAP.get(WRITETIMEOUT) : writeTimeOut, TimeUnit.MILLISECONDS);
    }

    public static String stringOkHttpPost(String url, Object object, Map<String, String> headers, Map<String, Integer> timeOutMap) throws Exception {
        Response response = okHttpPost(url, object2String(object), timeOutMap, headers);
        return resultString(response);
    }

    /**
     * http get
     * @param url
     * @param queryString
     * @param timeOutMap
     * @param headerMap
     * @return
     * @throws Exception
     */
    public static String stringOkHttpGet(String url, Object queryString, Map<String, Integer> timeOutMap, Map<String, String> headerMap) throws Exception {
        Response response = okHttpGet(url, queryString(queryString), timeOutMap, headerMap);
        return resultString(response);
    }

    /**
     * 发送delete请求
     * @param url
     * @param object
     * @param headers
     * @param timeOutMap
     * @return
     * @throws Exception
     */
    public static String stringOkHttpDelete(String url, Object object, Map<String, String> headers ,Map<String, Integer> timeOutMap) throws Exception {
        Response response = okHttpDelete(url, object2String(object), timeOutMap, headers);
        return resultString(response);
    }

    /**
     * 发送put请求
     * @param url
     * @param object
     * @param headers
     * @param timeOutMap
     * @return
     * @throws Exception
     */
    public static String stringOkHttpPut(String url, Object object, Map<String, String> headers ,Map<String, Integer> timeOutMap) throws Exception {
        Response response = okHttpPut(url, object2String(object), timeOutMap, headers);
        return resultString(response);
    }

    /**
     * http get
     * @param url
     * @param queryString
     * @param headerMap
     * @return
     * @throws Exception
     */
    public static Response okHttpGet(String url, String queryString, Map<String, Integer> timeOutMap, Map<String, String> headerMap) throws Exception {
        long startTime = System.currentTimeMillis();
        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        setTimeOut(clientBuilder, timeOutMap);

        Request.Builder builder;
        if (StringUtils.isBlank(queryString)) {
            builder = new Request.Builder().url(url).get();
        } else {
            builder = new Request.Builder().url(url + "?" + URIUtil.encodeQuery(queryString)).get();
        }
        if (!CollectionUtils.isEmpty(headerMap)) {
            builder.headers(Headers.of(headerMap));
        }
        Request request = builder.build();
        Response response = clientBuilder.build().newCall(request).execute();

        RcLoggerUtils.recordReq(logger, "okHttpGet", startTime, "请求地址:" + url + "请求参数:" + queryString + ";result:" + JSONObject.toJSONString(response));
        return response;
    }

    private static String resultString(Response response) {
        checkResponse(response);
        try {
            String result = response.body().string();
            if (StringUtils.isBlank(result)){
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void checkResponse(Response response) {
        if (response == null || !response.isSuccessful()) {
            //throw ExceptionFactory.httpClientException(BusinessErrorCodeEnum.HTTPCLIENTREQ_ERROR, "返回response不正确" + (response != null ? JSONObject.toJSONString(response) : ""));
            log.error("返回response不正确" + (response != null ? JSONObject.toJSONString(response) : ""));
        }
    }

    private static void buildHeader(Request.Builder builder, Map<String, String> headers) {
        if (null != headers && headers.size() > 0) {
            Object[] keys = headers.keySet().toArray();
            int len = keys.length;
            for (int i = 0; i < len; i++) {
                String key = keys[i].toString();
                builder.addHeader(key, headers.get(key));
            }
        }
    }

    /**
     * 返回http response status
     *
     * @param response
     * @return
     */
    public static int getStatus(Response response) {
        return response.code();
    }

    /**
     * 获取响应
     *
     * @param response
     * @return
     * @throws IOException
     */
    public static String getResult(Response response) throws IOException {
        return response.body().string();
    }


    private static String object2String(Object object) {
        if (object == null) {
            return null;
        }
        String jsonStr;
        if (object instanceof String) {
            jsonStr = String.valueOf(object);
        } else {
            jsonStr = JSONObject.toJSONString(object);
        }
        return jsonStr;
    }


    public static Response okHttpGetBasicAuth(String url, String queryString, final String userName, final String password, Map<String, Integer> timeOutMap, Map<String, String> headerMap) throws Exception {
        long startTime = System.currentTimeMillis();
        OkHttpClient.Builder clientBuilder = CLIENT.newBuilder();
        clientBuilder.addInterceptor(new BasicAuthInterceptor(userName, password));
        setTimeOut(clientBuilder, timeOutMap);
        Request.Builder builder;
        if (StringUtils.isBlank(queryString)) {
            builder = new Request.Builder().url(url).get();
        } else {
            builder = new Request.Builder().url(url + "?" + URIUtil.encodeQuery(queryString)).get();
        }
        if (null != headerMap) {
            builder.headers(Headers.of(headerMap));
        }
        Request request = builder.build();
        Response response = clientBuilder.build().newCall(request).execute();

        RcLoggerUtils.recordReq(logger, "okHttpGetBasicAuth", startTime, "请求地址:" + url + "请求参数:" + queryString + ";result:" + JSONObject.toJSONString(response));
        return response;
    }

    public static String stringOkHttpGetBasicAuth(String url, String queryString, final String userName, final String password, Map<String, Integer> timeOutMap, Map<String, String> headerMap) throws Exception {
        Response response = okHttpGetBasicAuth(url, queryString, userName, password, timeOutMap, headerMap);
        return resultString(response);
    }

    /**
     * 仅支持一层对象
     *
     * @param object
     * @return
     */
    private static String queryString(Object object) {
        if (object == null) return null;
        if (object instanceof String) {
            return String.valueOf(object);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object));
            for (JSONObject.Entry<String, Object> entry : jsonObject.entrySet()) {
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            return stringBuilder.toString();
        }
    }

    

    

   
}
