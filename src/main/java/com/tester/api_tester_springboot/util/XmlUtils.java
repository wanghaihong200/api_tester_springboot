package com.tester.api_tester_springboot.util;

import org.json.JSONObject;
import org.json.XML;



/**
 * @description: xml utils
 * @author: 王海虹
 * @create: 2023-01-12 16:28
 */
public class XmlUtils {

    /**
    * xml转为json
    * @param:
    * @return:
    * @date: 2023/1/12
    */
    public static String xmlToJson(String xml){
        JSONObject jsonObject = XML.toJSONObject(xml);
        //数据前标签

        return jsonObject.toString();
    }

    public static void main(String[] args) {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<root>"
                + " <mdcardno>查询卡号</mdcardno>"
                + " <count>返回明细条数</count>"
                + " <rd>"
                + "     <trxzone>交易地区号1</trxzone>"
                + "     <trxcurr>交易币种1</trxcurr>"
                + " </rd>"
                + " <rd>"
                + "     <trxzone>交易地区号3</trxzone>"
                + "     <trxcurr>交易币种3</trxcurr>"
                + " </rd>"
                + " <rd>"
                + "     <trxzone>交易地区号2</trxzone>"
                + "     <trxcurr>交易币种2</trxcurr>"
                + "</rd>"
                + "</root>";

        System.out.println(XmlUtils.xmlToJson(xmlString));
    }
}
