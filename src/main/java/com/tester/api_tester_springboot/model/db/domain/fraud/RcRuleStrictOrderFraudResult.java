package com.tester.api_tester_springboot.model.db.domain.fraud;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则变严订单风控结果
 * @TableName rc_rule_strict_order_fraud_result
 */
@Data
public class RcRuleStrictOrderFraudResult implements Serializable {
    /**
     * 
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
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}