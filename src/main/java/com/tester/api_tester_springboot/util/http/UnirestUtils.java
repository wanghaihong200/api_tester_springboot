package com.tester.api_tester_springboot.util.http;

/**
 * @description:
 * @author: 王海虹
 * @create: 2022-07-13 17:26
 */

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tester.api_tester_springboot.util.RcLoggerUtils;
import kong.unirest.*;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * UnirestUtils工具类，封装常用的get和post请求，并支持代理设置
 */
@Data
public class UnirestUtils {
    private final static Logger logger = LogManager.getLogger(UnirestUtils.class);

    /**
     * 默认编码格式
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 代理是否开启
     */
    private static boolean openProxy = false;

    /**
     * 代理主机
     */
    private static String proxyHost;

    /**
     * 代理端口
     */
    private static int proxyPort;

    /**
     * URL前缀
     */
    private static String urlPrefix;

    /**
     * 默认请求头
     */
    private static Map<String, String> defaultHeader = new HashMap<>();

    /**
     * json请求头，Content-Type: application/json
     */
    public static Map<String, String> headerJson;

    /**
     * form请求头，Content-Type: application/x-www-form-urlencoded
     */
    public static Map<String, String> headerForm;

    static {
        headerJson = new HashMap<String, String>() {
        };
        headerJson.put("Content-Type", "application/json");
        headerJson.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.49");

        headerForm = new HashMap<String, String>() {
        };
        headerForm.put("Content-Type", "application/x-www-form-urlencoded");

        Unirest.config().verifySsl(false);
        Unirest.config().connectTimeout(30000);
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @return
     */
    public static JSONObject get(String url) {
        return get(url, null, defaultHeader);
    }

    /**
     * get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static JSONObject get(String url, Map<String, Object> params) {
        return get(url, params, defaultHeader);
    }

    /**
     * get请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public static JSONObject get(String url, Map<String, Object> params, Map<String, String> headers) {
        long startTime = System.currentTimeMillis();
        url = processUrl(url);
        GetRequest getRequest = Unirest.get(url);
        if (openProxy) {
            getRequest.proxy(proxyHost, proxyPort);
        }
        if (params != null && params.size() > 0) {
            for (String s : params.keySet()) {
                getRequest.queryString(s, params.get(s));
            }
        }
        if (headers != null && headers.size() > 0) {
            for (String s : headers.keySet()) {
                getRequest.header(s, headers.get(s));
            }
        }
        String response = getRequest.asString().getBody();

        RcLoggerUtils.recordReq(logger, "get", startTime, url, headers, params, jsonFormat(response));

        return JSONObject.parseObject(response);
    }

    /**
     * basicAuth请求
     *
     * @param:
     * @return:
     * @date: 2023/1/13
     */
    public static String getBasicAuth(String url, Map<String, Object> params,
                                      Map<String, String> headers,
                                      Map<String, String> basicAuth) {
        long startTime = System.currentTimeMillis();
        url = processUrl(url);
        GetRequest getRequest = Unirest.get(url);
        if (openProxy) {
            getRequest.proxy(proxyHost, proxyPort);
        }
        if (!basicAuth.isEmpty()) {
            getRequest.basicAuth(basicAuth.get("username"), basicAuth.get("password"));
        }

        if (params != null && params.size() > 0) {
            for (String s : params.keySet()) {
                getRequest.queryString(s, params.get(s));
            }
        }
        if (headers != null && headers.size() > 0) {
            for (String s : headers.keySet()) {
                getRequest.header(s, headers.get(s));
            }
        }
        String response = getRequest.asString().getBody();
        RcLoggerUtils.recordReq(logger, "get", startTime, url, headers, params, response);

        return response;
    }

    public static String getBasicAuth(String url,Map<String, String> basicAuth){
        return getBasicAuth(url,null,null,basicAuth);
    }

    /**
     * post请求
     *
     * @param url 请求地址
     * @return
     */
    public static JSONObject post(String url) {
        return post(url, null, headerJson);
    }

    /**
     * post请求
     *
     * @param url     请求地址
     * @param payload 请求body
     * @return
     */
    public static JSONObject post(String url, String payload) {
        return post(url, payload, headerJson);
    }

    /**
     * post请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @return
     */
    public static JSONObject post(String url, Map<String, String> headers) {
        return post(url, null, headers);
    }

    /**
     * post请求
     *
     * @param url     请求地址
     * @param payload 请求body
     * @param headers 请求头
     * @return
     */
    public static JSONObject post(String url, String payload, Map<String, String> headers) {
        String res;
        url = processUrl(url);
        long startTime = System.currentTimeMillis();
        HttpRequestWithBody httpRequestWithBody = Unirest.post(url);
        if (openProxy) {
            httpRequestWithBody.proxy(proxyHost, proxyPort);
        }
        if (headers != null && headers.size() > 0) {
            for (String s : headers.keySet()) {
                httpRequestWithBody.header(s, headers.get(s));
            }
        }

        if (payload == null) {
            res = httpRequestWithBody.asString().getBody();
        } else {
            res = httpRequestWithBody.body(payload).asString().getBody();
        }
        RcLoggerUtils.recordReq(logger, "post", startTime, url, headers, jsonFormat(payload), jsonFormat(res));
        return JSONObject.parseObject(res);
    }

    /**
     * Form表单请求，Content-Type：application/x-www-form-urlencoded
     * InputStream file = new FileInputStream(new File("/MyFile.zip"));
     * Unirest.post("http://localhost")
     * .field("upload", file, "MyFile.zip")
     * .field("key1", "value1")
     * .asEmpty();
     *
     * @param:
     * @return:
     * @date: 2023/1/13
     */

    public static JSONObject postForm(String url, Map<String, Object> params) {
        HttpResponse jsonResponse = null;
        long startTime = System.currentTimeMillis();
        try {
            jsonResponse = Unirest.post(url)
                    .fields(params)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            RcLoggerUtils.recordReq(logger, "postForm", startTime, url, null, params, jsonResponse.getBody().toString());
            return JSONObject.parseObject(jsonResponse.getBody().toString());
        }
        return JSONObject.parseObject("");
    }

    public static JSONObject postForm(String url, Map<String, Object> params, Map<String, String> headers) {
        HttpResponse jsonResponse = null;
        long startTime = System.currentTimeMillis();
        try {
            jsonResponse = Unirest.post(url)
                    .headers(headers)
                    .fields(params)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            RcLoggerUtils.recordReq(logger, "postForm", startTime, url, headers, params, jsonResponse.getBody().toString());
            return JSONObject.parseObject(jsonResponse.getBody().toString());
        }
        return JSONObject.parseObject("");
    }

    /**
     * 处理url
     *
     * @param url 原始url，如urlPrefix不为空，则会在url前补上urlPrefix
     * @return
     */
    private static String processUrl(String url) {
        if (urlPrefix != null) {
            url = urlPrefix + url;
        }
        return url;
    }

    /**
     * uri编码，默认采用UTF-8编码
     *
     * @param text 编码前的文本
     * @return
     */
    public static String encodeURIComponent(String text) {
        return encodeURIComponent(text, DEFAULT_ENCODING);
    }

    /**
     * uri编码
     *
     * @param text     编码前的文本
     * @param encoding 编码格式
     * @return
     */
    public static String encodeURIComponent(String text, String encoding) {
        try {
            return URLEncoder.encode(text, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return text;
        }
    }

    /**
     * uri解码，默认采用UTF-8解码
     *
     * @param text 解码前的文本
     * @return
     */
    public static String decodeURIComponent(String text) {
        return decodeURIComponent(text, DEFAULT_ENCODING);
    }

    /**
     * uri解码
     *
     * @param text     解码前的文本
     * @param encoding 编码格式
     * @return
     */
    public static String decodeURIComponent(String text, String encoding) {
        try {
            return URLDecoder.decode(text, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return text;
        }
    }

    static HttpUtil instance;

    public static HttpUtil getInstance() {
        if (instance == null) {
            instance = new HttpUtil();
        }
        return instance;
    }

    public static JSONObject doGet(String url) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.get(url)
                    .header("content-type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doGet(String url, Map<String, Object> map) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.get(url)
                    .header("content-type", "application/json")
                    .queryString(map)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPut(String url) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.put(url)
                    .header("content-type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPut(String url, String json) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.put(url)
                    .body(json)
                    .header("content-type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPutByForm(String url, Map<String, Object> map) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.put(url)
                    .fields(map)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doDelete(String url) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.delete(url)
                    .header("content-type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doDelete(String url, String json) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.delete(url)
                    .body(json)
                    .header("content-type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public static JSONObject doPost(String url, String json) {
        HttpResponse jsonResponse = null;
        try {
            JsonNode jsonNode = new JsonNode(json);
            while (jsonResponse == null) {
                jsonResponse = Unirest.post(url)
                        .body(jsonNode)
                        .header("content-type", "application/json")
                        .asJson();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public void doPostParallel(String url, String json) {
        JsonNode jsonNode = new JsonNode(json);
        try {
            Unirest.post(url)
                    .body(jsonNode)
                    .header("content-type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public JSONObject doPostBytxt(String url, String txt) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.post(url)
                    .body(txt)
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPostByForm(String url, Map<String, Object> map) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.post(url)
                    .fields(map)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public static String jsonFormat(String jsonString) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject = JSONObject.parseObject(jsonString);
        }
        catch(Exception e){
            JSONArray JSONArray = JSON.parseArray(jsonString);
            jsonObject = JSONArray.getJSONObject(0);
        }

        String jsonFormatString = JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        return jsonFormatString;
    }

    /**
     * 测试main函数
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        //Map<String, Object> params = new HashMap<>();
        //params.put("file", new File("D:/GallerySafeTest/文档/test01.docx"));
        //params.put("fileName", "1.docx");
        //params.put("traceId", UUID.randomUUID().toString());
        //
        //UnirestUtils.postForm("http://10.160.92.69:8080/security/check/storage", params);
        //
        //HttpResponse<String> response = Unirest.post("http://10.160.92.69:8080/security/check/storage")
        //        .field("file", Files.newInputStream(new File("D:/GallerySafeTest/文档/test01.docx").toPath()), "1.docx")
        //        .field("fileName", "1.docx")
        //        .field("traceId", UUID.randomUUID().toString())
        //        .asString();
        //
        //System.out.println(response.getBody());
        String requestUrl = "http://10.160.93.5:8080/marketing/tag/id";
        JSONObject res = UnirestUtils.get(requestUrl);
    }
}