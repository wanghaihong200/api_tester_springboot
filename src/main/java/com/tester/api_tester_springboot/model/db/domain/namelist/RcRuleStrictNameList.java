package com.tester.api_tester_springboot.model.db.domain.namelist;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则变严流程名单表
 * @TableName rc_rule_strict_name_list
 */

@Data
public class RcRuleStrictNameList implements Serializable {
    /**
     * id
     */

    private Long id;

    /**
     * 名单维度
     */
    private String stockType;

    /**
     * 维度值
     */
    private String stockTypeValue;

    /**
     * 过期时间
     */
    private Date expTime;

    /**
     * ktype
     */
    private String ktype;

    /**
     * 预定时间
     */
    private Date scheduleTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建Ip
     */
    private String creatorIp;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作ip
     */
    private String operatorIp;

    /**
     * 操作时间
     */
    private Date operatorTime;

    /**
     * 是否生效 0-生效 1-无效
     */
    private Integer status;

    /**
     * 名单类型 0-黑名单 1-白名单
     */
    private Integer listType;

    /**
     * 名单来源 0-离线 1-实时策略 2-界面添加
     */
    private Integer fromType;


    private static final long serialVersionUID = 1L;
}