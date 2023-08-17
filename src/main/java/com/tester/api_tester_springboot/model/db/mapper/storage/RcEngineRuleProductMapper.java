package com.tester.api_tester_springboot.model.db.mapper.storage;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.storage.RcEngineRuleProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王海虹
* @description 针对表【rc_engine_rule_product(规则命中表)】的数据库操作Mapper
* @createDate 2023-05-15 20:26:36
* @Entity com.tester.api_tester_springboot.model.db.domain.storage.RcEngineRuleProduct
*/
@DS("tcrcStorageData")
@Mapper
public interface RcEngineRuleProductMapper extends BaseMapper<RcEngineRuleProduct> {

}




