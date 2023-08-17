package com.tester.api_tester_springboot.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: 王海虹
 * @create: 2022-07-14 14:39
 */
public class JsonUtils {
    public static JSONObject fileToJson(String fileName) {
        JSONObject json = null;
        try{
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (inputStream!=null){
                json = JSONObject.parseObject(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
            }
        } catch (Exception e){
            System.out.println("文件读取异常:"+fileName+"---------"+e);
        }
        return json;
    }

    public static void main(String[] args) {
        //String fileName = "data/gallery/marketing_risk.json";
        String fileName = "./1.json";
        JSONObject json = JsonUtils.fileToJson(fileName);
        System.out.println(json);
        System.out.println(json.get("Value"));

    }
}
