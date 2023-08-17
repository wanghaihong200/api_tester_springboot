package com.tester.api_tester_springboot.model.db.domain.fraud;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 酒店线上异常订单（羊毛单）结果
 * @TableName rc_unusual_order_fraud_result
 */

@Data
public class RcUnusualOrderFraudResult implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单号
     */
    private String relationValue;

    /**
     * 触发规则
     */
    private String rules;

    /**
     * 
     */
    private Date scheduledTime;

    /**
     * 是否触发规则 0 为不触发 1 触发
     */
    private Integer isTriggered;

    /**
     * 
     */
    private Date timestamp;

    /**
     * sku_id
     */
    private String sku;

    private static final long serialVersionUID = 1L;
}