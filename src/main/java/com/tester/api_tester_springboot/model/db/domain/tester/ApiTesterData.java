package com.tester.api_tester_springboot.model.db.domain.tester;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * api_tester测试数据
 * @TableName api_tester_data
 */
@TableName(value ="api_tester_data")
@Data
public class ApiTesterData implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 被测服务uk,与jean上一致
     */
    private String systemUk;

    /**
     * 接口名
     */
    private String apiName;

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 接口入参
     */
    private String paramsJson;

    /**
     * antiBee: 反养蜂，prepayHotel：预付酒店，shareCodeHotel： 店外场景（分享住）,qrCodeHotel: 扫码住, domesticHotel: 信用住现付, creditHotel: 信用住, cashBack: 返现，withDraw: 提现 , unusualOrder: 羊毛单
     */
    private String product;

    /**
     * 对应风控后台的规则编号
     */
    private Integer rule;

    /**
     * 营销风控token
     */
    private String token;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}