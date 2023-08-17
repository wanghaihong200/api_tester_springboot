package com.tester.api_tester_springboot.core.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @description:
 * @author: 王海虹
 * @create: 2023-05-06 11:22
 */
@Configuration
@MapperScan("com.tester.api_tester_springboot.model.db.mapper")
public class MybatisPlusConfig {
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    //@Bean
    //public PaginationInnerInterceptor paginationInterceptor() {
    //    PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
    //    //设置最大单页数限制数量，默认500，-1不受限制
    //    //paginationInterceptor.setMaxLimit(-1L);
    //    return paginationInterceptor;
    //}
}
