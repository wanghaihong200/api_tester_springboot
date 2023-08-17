package com.tester.api_tester_springboot.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.TestResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 王海虹
* @description 针对表【test_result(api_tester测试用例执行结果)】的数据库操作Service
* @createDate 2023-04-28 17:21:26
*/
@DS("ice")
public interface TestResultService extends IService<TestResult> {

}
