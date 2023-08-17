package com.tester.api_tester_springboot.model.db.domain.namelist;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * m酒店黑名单
 * @TableName rc_lc_hotel_list
 */
@Data
public class RcLcHotelList implements Serializable {
    /**
     * m酒店id
     */
    private String mHotelId;

    /**
     * 名单来源
     */
    private String source;

    /**
     * 使用业务线
     */
    private String productName;

    /**
     * 是否有效，1 有效， 0 无效
     */
    private String state;

    /**
     * 创建日期
     */
    private String createDay;

    /**
     * 更新时间
     */
    private Date timestamp;

    private static final long serialVersionUID = 1L;
}