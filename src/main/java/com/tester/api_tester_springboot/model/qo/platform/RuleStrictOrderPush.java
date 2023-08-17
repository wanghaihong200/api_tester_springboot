package com.tester.api_tester_springboot.model.qo.platform;

import lombok.Data;

/**
 * @description: 订单推送恶意下单黑名单到风控
 * @author: 王海虹
 * @create: 2023-03-09 17:28
 */
@Data
public class RuleStrictOrderPush {
    // 该值传E侧卡号
    String card;
    // 名单场景, 0-离线推送 1-实时策略 2-界面添加 3-订单同步
    Integer scene;
    // 是否生效, 0-生效 1-无效
    Integer status;
    // 操作IP
    String operateIp;

}
