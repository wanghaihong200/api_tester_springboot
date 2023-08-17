CREATE TABLE `api_tester_data` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `system_uk` varchar(50) NOT NULL DEFAULT '' COMMENT '被测服务uk,与jean上一致',
                                   `api_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '接口名',
                                   `request_url` varchar(50) NOT NULL DEFAULT '' COMMENT '请求url',
                                   `params_json` text NOT NULL COMMENT '接口入参',
                                   `product` varchar(50) NOT NULL COMMENT 'antiBee: 反养蜂，prepayHotel：预付酒店，shareCodeHotel： 店外场景（分享住）,qrCodeHotel: 扫码住, domesticHotel: 信用住现付, creditHotel: 信用住, cashBack: 返现，withDraw: 提现 , unusualOrder: 羊毛单',
                                   `rule` int(11) NOT NULL DEFAULT '0' COMMENT '对应风控后台的规则编号',
                                   `token` varchar(20) DEFAULT NULL COMMENT '营销风控token',
                                   PRIMARY KEY (`id`),
                                   KEY `system_uk` (`system_uk`),
                                   KEY `api_name` (`api_name`) USING BTREE,
                                   KEY `rule` (`rule`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='api_tester测试数据';
