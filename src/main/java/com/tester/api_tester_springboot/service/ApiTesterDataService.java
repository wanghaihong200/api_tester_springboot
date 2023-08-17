package com.tester.api_tester_springboot.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 王海虹
* @description 针对表【api_tester_data(api_tester测试数据)】的数据库操作Service
* @createDate 2023-05-15 19:38:56
*/
@DS("ice")
public interface ApiTesterDataService extends IService<ApiTesterData> {

}
