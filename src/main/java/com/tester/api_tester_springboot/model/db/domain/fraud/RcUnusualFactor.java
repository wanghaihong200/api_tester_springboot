package com.tester.api_tester_springboot.model.db.domain.fraud;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 风控羊毛因子计算表
 * @TableName rc_unusual_factor
 */

@Data
public class RcUnusualFactor implements Serializable {
    /**
     * 主键
     */

    private Long id;

    /**
     * sku
     */
    private String sku;

    /**
     * m酒店id
     */
    private String mHotelId;

    /**
     * 间夜数
     */
    private String quantity;

    /**
     * 订单状态
     */
    private String orderState;

    /**
     * 预定数量
     */
    private String day;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 下单时间
     */
    private Date scheduledTime;

    /**
     * 间夜平均价
     */
    private BigDecimal priceDay;

    /**
     * skuNew
     */
    private String skuNew;

    /**
     * 预定时间到入住日时间天数
     */
    private Integer dayCountBookScheduled;

    /**
     * 商圈ID
     */
    private Integer businessAreaId;

    /**
     * 插入时间
     */
    private Date createTime;


    private static final long serialVersionUID = 1L;
}