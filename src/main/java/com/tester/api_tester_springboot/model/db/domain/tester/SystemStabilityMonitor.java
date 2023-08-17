package com.tester.api_tester_springboot.model.db.domain.tester;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统稳定性监控
 * @TableName system_stability_monitor
 */
@TableName(value ="system_stability_monitor")
@Data
public class SystemStabilityMonitor implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 服务名
     */
    private String system;

    /**
     * 正常流量
     */
    private Long normalFlow;

    /**
     * 异常流量
     */
    private Long abnormalFlow;

    /**
     * 超时流量
     */
    private Long timeoutFlow;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}