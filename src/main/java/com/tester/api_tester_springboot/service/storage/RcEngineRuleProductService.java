package com.tester.api_tester_springboot.service.storage;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.storage.RcEngineRuleProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 王海虹
* @description 针对表【rc_engine_rule_product(规则命中表)】的数据库操作Service
* @createDate 2023-05-15 20:26:36
*/
@DS("tcrcStorageData")
public interface RcEngineRuleProductService extends IService<RcEngineRuleProduct> {

}
