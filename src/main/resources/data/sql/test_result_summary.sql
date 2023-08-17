CREATE TABLE `test_result_summary` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `run_id` varchar(50) NOT NULL DEFAULT '' COMMENT '测试脚本运行批次Id',
                                       `succeeded_amount` varchar(50) NOT NULL DEFAULT '0' COMMENT '测试用例成功总数',
                                       `failed_amount` varchar(50) NOT NULL DEFAULT '' COMMENT '测试用例失败总数',
                                       `skipped_amount` varchar(50) NOT NULL COMMENT '测试用例跳过总数',
                                       `status` varchar(50) NOT NULL COMMENT '用例执行状态, 1：成功，2：失败',
                                       `start_time` datetime NOT NULL COMMENT '用例执行开始时间',
                                       `end_time` datetime NOT NULL COMMENT '用例执行结束时间',
                                       `operator` varchar(20) DEFAULT NULL COMMENT '执行人',
                                       PRIMARY KEY (`id`),
                                       KEY `run_id` (`run_id`) USING BTREE,
                                       KEY `operator` (`operator`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='api_tester测试用例执行结果汇总数据';