package com.tester.api_tester_springboot.model.db.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.TestResultSummary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王海虹
* @description 针对表【test_result_summary(api_tester测试用例执行结果汇总数据)】的数据库操作Mapper
* @createDate 2023-05-05 11:35:43
* @Entity com.tester.api_tester_springboot.model.db.domain.TestResultSummary
*/
@Mapper
@DS("ice")
public interface TestResultSummaryMapper extends BaseMapper<TestResultSummary> {

}




