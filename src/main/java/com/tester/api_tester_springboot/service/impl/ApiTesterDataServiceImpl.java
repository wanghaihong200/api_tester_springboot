package com.tester.api_tester_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.tester.api_tester_springboot.service.ApiTesterDataService;
import com.tester.api_tester_springboot.model.db.mapper.ApiTesterDataMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海虹
* @description 针对表【api_tester_data(api_tester测试数据)】的数据库操作Service实现
* @createDate 2023-05-15 19:38:56
*/
@Service
public class ApiTesterDataServiceImpl extends ServiceImpl<ApiTesterDataMapper, ApiTesterData>
    implements ApiTesterDataService{

}




