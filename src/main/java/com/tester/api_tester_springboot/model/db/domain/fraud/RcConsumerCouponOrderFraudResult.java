package com.tester.api_tester_springboot.model.db.domain.fraud;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消费券订单风控结果
 * @TableName rc_consumer_coupon_order_fraud_result
 */
@Data
public class RcConsumerCouponOrderFraudResult implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * mhotel_id
     */
    private String mhotelId;

    /**
     * mhotel_name
     */
    private String mhotelName;

    /**
     * 触发规则
     */
    private String rules;

    /**
     * 是否回调[0-没有回调 1-有回调]
     */
    private Integer hascall;

    /**
     * 
     */
    private Date timestamp;

    /**
     * 消费券批次号
     */
    private String promotionids;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}