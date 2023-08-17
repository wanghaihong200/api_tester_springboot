package com.tester.api_tester_springboot.model.db.domain.namelist;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 高价值用户名单表
 * @TableName rc_hc_account
 */

@Data
public class RcHcAccount implements Serializable {
    /**
     * 账号
     */

    private String uid;

    /**
     * 该账号对应的相关评估结果json格式
     */
    private String value;

    /**
     * 账号类型
     */
    private String type;

    /**
     * 评估日期
     */
    private String day;

    /**
     * 时间戳
     */
    private Date timestamp;

    private static final long serialVersionUID = 1L;
}