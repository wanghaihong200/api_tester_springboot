package com.tester.api_tester_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tester.api_tester_springboot.model.db.domain.TestResultSummary;
import com.tester.api_tester_springboot.service.TestResultSummaryService;
import com.tester.api_tester_springboot.model.db.mapper.TestResultSummaryMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海虹
* @description 针对表【test_result_summary(api_tester测试用例执行结果汇总数据)】的数据库操作Service实现
* @createDate 2023-05-05 11:35:43
*/
@Service
public class TestResultSummaryServiceImpl extends ServiceImpl<TestResultSummaryMapper, TestResultSummary>
    implements TestResultSummaryService{

}




