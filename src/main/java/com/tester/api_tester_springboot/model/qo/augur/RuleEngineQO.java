package com.tester.api_tester_springboot.model.qo.augur;

import lombok.Data;

@Data
public class RuleEngineQO {
    /**
     * 决策流id
     */
    private String decisionFlowId;

    /**
     * 决策流名称
     */
    private String flowName;

    /**
     * traceId
     */
    private String traceId;

    /**
     * 参数映射id,标识当前请求参数有哪些字段
     */
    private String datasourceId;

    /**
     * 参数，业务参数
     */
    private Params param;

    /**
     * 特征参数，若传入了特征参数，则优先从特征参数获取特征值
     */
    private ParamFeatureValue featureParam;

    private String actionTime;

    /**
     * 数据id
     */
    private String uniqueId;

    /**
     * 数据源名称
     */
    private String sourceName;

    public void checkParam() {

    }
}
