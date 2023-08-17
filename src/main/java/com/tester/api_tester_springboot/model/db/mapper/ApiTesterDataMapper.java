package com.tester.api_tester_springboot.model.db.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 王海虹
* @description 针对表【api_tester_data(api_tester测试数据)】的数据库操作Mapper
* @createDate 2023-05-15 19:38:56
* @Entity com.tester.api_tester_springboot.model.db.domain.tester.ApiTesterData
*/
@DS("ice")
@Mapper
public interface ApiTesterDataMapper extends BaseMapper<ApiTesterData> {
    int deleteByPrimaryKey(Integer id);

    int insert(ApiTesterData record);

    int insertSelective(ApiTesterData record);

    ApiTesterData selectByPrimaryKey(Integer id);

    // 查询 platform 的 redis_operate接口 的测试数据
    ApiTesterData queryTesterParam(@Param("systemUk") String systemUk, @Param("apiName") String apiName);

    // 查询 poseidon 线上规则的测试数据
    ApiTesterData queryTesterDataByRule(@Param("systemUk") String systemUk, @Param("rule") String rule);

    // 查询 gallery 营销风控的测试数据
    ApiTesterData queryTesterDataByToken(@Param("systemUk") String systemUk, @Param("token") String token);

    int updateByPrimaryKeySelective(ApiTesterData record);

    int updateByPrimaryKeyWithBLOBs(ApiTesterData record);

    int updateByPrimaryKey(ApiTesterData record);
}




