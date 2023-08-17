package com.tester.api_tester_springboot.model.db.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.TestResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王海虹
* @description 针对表【test_result(api_tester测试用例执行结果)】的数据库操作Mapper
* @createDate 2023-04-28 17:21:26
* @Entity com.tester.api_tester_springboot.model.domain.db.TestResult
*/
@DS("ice")
@Mapper
public interface TestResultMapper extends BaseMapper<TestResult> {

}




