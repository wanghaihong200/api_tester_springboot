CREATE TABLE `test_result` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `run_id` varchar(50) NOT NULL DEFAULT '' COMMENT '测试脚本运行Id',
                               `test_class` varchar(50) NOT NULL DEFAULT '0' COMMENT '测试脚本Class',
                               `method` varchar(50) NOT NULL DEFAULT '' COMMENT '测试方法',
                               `case_no` varchar(50) NOT NULL COMMENT '用例number',
                               `test_name` varchar(50) NOT NULL COMMENT '用例名称',
                               `status` varchar(50) NOT NULL COMMENT '用例执行状态',
                               `throwable` text NOT NULL DEFAULT '' COMMENT '用例执行报错',
                               `operator` varchar(20) DEFAULT NULL COMMENT '执行人',
                               `startTime` datetime NOT NULL COMMENT '用例执行开始时间',
                               `endTime` datetime NOT NULL COMMENT '用例执行结束时间',
                               PRIMARY KEY (`id`),
                               KEY `run_id` (`run_id`) USING BTREE,
                               KEY `operator` (`operator`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='api_tester测试用例执行结果';