package com.tester.api_tester_springboot.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.TestResultSummary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 王海虹
* @description 针对表【test_result_summary(api_tester测试用例执行结果汇总数据)】的数据库操作Service
* @createDate 2023-05-05 11:35:43
*/
@DS("ice")
public interface TestResultSummaryService extends IService<TestResultSummary> {

}
