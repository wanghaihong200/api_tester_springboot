package com.tester.api_tester_springboot.model.db.domain.storage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则命中表
 * @TableName rc_engine_rule_product
 */
@TableName(value ="rc_engine_rule_product")
@Data
public class RcEngineRuleProduct implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 数据源下唯一标识
     */
    private String uniqueId;

    /**
     * 数据源名称
     */
    private String sourceName;

    /**
     * 请求唯一标识
     */
    private String traceId;

    /**
     * 请求时间
     */
    private Date actionTime;

    /**
     * 决策流名称
     */
    private String ruleFlowName;

    /**
     * 流程日志id
     */
    private Long engineLogId;

    /**
     * 环境。stage，product
     */
    private String env;

    /**
     * 决策流版本id
     */
    private Long ruleFlowVersionId;

    /**
     * 命中的规则列表
     */
    private String rules;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String operator;

    /**
     * 创建人
     */
    private String createUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}