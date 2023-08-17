package com.tester.api_tester_springboot.model.qo.augur;

import java.util.Map;

public class ParamFeatureValue {

    /**
     * 特征的键值对
     */
    private Map<String,String> values;

    public String getValue(String featureId,String dimensionName) {
        return values.get(featureId);
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
