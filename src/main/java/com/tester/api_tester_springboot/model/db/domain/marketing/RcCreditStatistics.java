package com.tester.api_tester_springboot.model.db.domain.marketing;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 信用住统计表
 * @TableName rc_credit_statistics
 */

@Data
public class RcCreditStatistics implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 产品线
     */
    private String product;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * openId
     */
    private String openId;

    /**
     * 订单创建时间
     */
    private String orderCreateTime;

    /**
     * 调微信支付前推风控时间
     */
    private String prepayTime;

    /**
     * 调微信支付后推风控时间
     */
    private String postpayTime;

    /**
     * 完结的tradeNo
     */
    private String projectSerialId;

    /**
     * 创单的tradeNo
     */
    private String preprojectSerialId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 0 未支付成功 1 支付成功  2 创单成功  3 创单失败-用户未授权  4 创单失败-腾讯风控 5 创单失败-风控规则 6 创单失败-参数错误
     */
    private Integer state;

    /**
     * 创单失败信息
     */
    private String description;

    /**
     * 时间戳
     */
    private Date timestamp;

    /**
     * 微信服务单号
     */
    private String wxOrderId;

    /**
     * 会员号
     */
    private String member;

    /**
     * 渠道 APP端 tapp eapp H5端 h5  微信小程序端 wx
     */
    private String channel;

    private static final long serialVersionUID = 1L;
}