package com.tester.api_tester_springboot.model.db.domain.namelist;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 高质量酒店名单
 * @TableName rc_hc_hotel_list
 */
@Data
public class RcHcHotelList implements Serializable {
    /**
     * 会员号
     */
    private String mHotelId;

    /**
     * 分值
     */
    private String mLabel;

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