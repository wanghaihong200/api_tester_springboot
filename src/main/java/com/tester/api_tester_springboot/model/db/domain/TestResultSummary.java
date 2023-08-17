package com.tester.api_tester_springboot.model.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api_tester测试用例执行结果汇总数据
 * @TableName test_result_summary
 */
@TableName(value ="test_result_summary")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResultSummary implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 测试脚本运行批次Id
     */
    private String runId;

    /**
     * 测试用例成功总数
     */
    private String succeededAmount;

    /**
     * 测试用例失败总数
     */
    private String failedAmount;

    /**
     * 测试用例跳过总数
     */
    private String skippedAmount;

    /**
     * 用例执行状态, 1：成功，2：失败，3：跳过，4：未知，16：开始
     */
    private String status;

    /**
     * 用例执行开始时间
     */
    private Date startTime;

    /**
     * 用例执行结束时间
     */
    private Date endTime;

    /**
     * 执行人
     */
    private String operator;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}