package com.tester.api_tester_springboot.model.db.mapper.fraud;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.fraud.DeconstructionResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王海虹
* @description 针对表【deconstruction_result(认证解限结果)】的数据库操作Mapper
* @createDate 2023-05-22 15:34:07
* @Entity com.tester.api_tester_springboot.model.db.domain.fraud.DeconstructionResult
*/
@DS("fraud")
@Mapper
public interface DeconstructionResultMapper extends BaseMapper<DeconstructionResult> {

}




