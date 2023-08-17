package com.tester.api_tester_springboot.service.impl.fraud;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tester.api_tester_springboot.model.db.domain.fraud.DeconstructionResult;
import com.tester.api_tester_springboot.service.fraud.DeconstructionResultService;
import com.tester.api_tester_springboot.model.db.mapper.fraud.DeconstructionResultMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海虹
* @description 针对表【deconstruction_result(认证解限结果)】的数据库操作Service实现
* @createDate 2023-05-22 15:34:07
*/
@Service
public class DeconstructionResultServiceImpl extends ServiceImpl<DeconstructionResultMapper, DeconstructionResult>
    implements DeconstructionResultService{

}




