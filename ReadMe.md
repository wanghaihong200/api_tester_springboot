# api_tester测试框架简介
### 一.测试用例执行
命令行执行testng

> mvn clean test -DsuiteXmlFile=D:\java_study\api_tester_springboot\testng.xml -P test -U


### 二.项目结构简介

+ com.tester.api_tester_springboot
    + core: aop、config、exception、handler
    + efficiency : 日常用的一些效率小工具
    + enums : 枚举类
    + model : 映射实体类
    + entity : mysql数据库表的实体类
    + enums : 一些枚举值定义
    + listener : 继承testNg的一些listener类（目前没用）
    + service : 风控各个系统的api类
    + efficiency : 日常用的一些效率小工具
    + util : 基础公共方法类


+ src/test:
  + BaseTest: 测试用例的基类

### 三. 项目技术栈： 
   springboot、mybatis_plus、mysql、testNg、fastJson、jedis、redission、kafaka、hutool、log4j2、jenkins(pipeline)