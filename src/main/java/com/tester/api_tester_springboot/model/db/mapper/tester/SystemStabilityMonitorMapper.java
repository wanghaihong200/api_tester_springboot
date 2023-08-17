package com.tester.api_tester_springboot.model.db.mapper.tester;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.tester.SystemStabilityMonitor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王海虹
* @description 针对表【system_stability_monitor(系统稳定性监控)】的数据库操作Mapper
* @createDate 2023-07-17 14:01:15
* @Entity com.tester.api_tester_springboot.model.db.domain.tester.SystemStabilityMonitor
*/
@DS("ice")
@Mapper
public interface SystemStabilityMonitorMapper extends BaseMapper<SystemStabilityMonitor> {

}




