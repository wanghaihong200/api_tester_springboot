package com.tester.api_tester_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tester.api_tester_springboot.model.db.domain.TestResult;
import com.tester.api_tester_springboot.service.TestResultService;
import com.tester.api_tester_springboot.model.db.mapper.TestResultMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海虹
* @description 针对表【test_result(api_tester测试用例执行结果)】的数据库操作Service实现
* @createDate 2023-04-28 17:21:26
*/
@Service
public class TestResultServiceImpl extends ServiceImpl<TestResultMapper, TestResult>
    implements TestResultService{

}




