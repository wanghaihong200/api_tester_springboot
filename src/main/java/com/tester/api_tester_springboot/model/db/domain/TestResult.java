package com.tester.api_tester_springboot.model.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * api_tester测试用例执行结果
 * @TableName test_result
 */
@TableName(value ="test_result")
@Data
public class TestResult implements Serializable {
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
     * 测试脚本Class
     */
    private String testClass;

    /**
     * 测试方法
     */
    private String method;

    /**
     * 用例number
     */
    private String caseNo;

    /**
     * 用例名称
     */
    private String testName;

    /**
     * 用例执行状态
     */
    private String status;

    /**
     * 用例执行报错
     */
    private String throwable;

    /**
     * 执行人
     */
    private String operator;

    /**
     * 用例执行开始时间
     */
    private Date startTime;

    /**
     * 用例执行结束时间
     */
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}