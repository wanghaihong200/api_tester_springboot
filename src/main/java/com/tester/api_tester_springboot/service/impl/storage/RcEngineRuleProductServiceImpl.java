package com.tester.api_tester_springboot.service.impl.storage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tester.api_tester_springboot.model.db.domain.storage.RcEngineRuleProduct;
import com.tester.api_tester_springboot.service.storage.RcEngineRuleProductService;
import com.tester.api_tester_springboot.model.db.mapper.storage.RcEngineRuleProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海虹
* @description 针对表【rc_engine_rule_product(规则命中表)】的数据库操作Service实现
* @createDate 2023-05-15 20:26:36
*/
@Service
public class RcEngineRuleProductServiceImpl extends ServiceImpl<RcEngineRuleProductMapper, RcEngineRuleProduct>
    implements RcEngineRuleProductService{

}




