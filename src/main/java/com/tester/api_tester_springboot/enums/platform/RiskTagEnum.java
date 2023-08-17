package com.tester.api_tester_springboot.enums.platform;

import lombok.AllArgsConstructor;

/**
 * @description: 风险标签及风险类型 枚举值
 * @author: 王海虹
 * @create: 2022-08-09 20:49
 */
@AllArgsConstructor
public enum RiskTagEnum {
    // 高危用户 -- 高危用户
    HIGH_RISK_USERS("high_risk_users", "1", "HR001"),

    // 机器行为 -- 使用模拟器/非正常设备
    HIGH_RISK_MACHINE_R100("high_risk_machine", "2", "R100"),
    // 机器行为 -- 使用恶意或机房IP
    HIGH_RISK_MACHINE_R200("high_risk_machine", "2", "R200"),
    // 机器行为 -- 批量小号
    HIGH_RISK_MACHINE_R300("high_risk_machine", "2", "R300"),
    // 机器行为 -- 访问轨迹异常
    HIGH_RISK_MACHINE_R400("high_risk_machine", "2", "R400"),
    // 机器行为 -- 设备批量注册
    HIGH_RISK_MACHINE_R301("high_risk_machine", "2", "R301"),
    // 机器行为 -- 接口实时访问
    HIGH_RISK_MACHINE_R401("high_risk_machine", "2", "R401"),

    // 聚集账号 -- 设备聚集
    HIGH_RISK_AGG_ACCOUNT_G301("high_risk_agg_account", "3","G301"),
    // 聚集账号 -- 支付账户聚集
    HIGH_RISK_AGG_ACCOUNT_G401("high_risk_agg_account", "3","G401"),
    // 聚集账号 -- IP聚集
    HIGH_RISK_AGG_ACCOUNT_G201("high_risk_agg_account", "3","G201"),
    // 聚集账号 -- 收货地址聚集
    HIGH_RISK_AGG_ACCOUNT_G501("high_risk_agg_account", "3","G501"),
    // 聚集账号 -- 准实时设备聚集
    HIGH_RISK_AGG_ACCOUNT_G302("high_risk_agg_account", "3","G302"),

    // 风险账号 -- 设备指纹风险
    HIGH_RISK_RISK_ACCOUNT_DS100("high_risk_risk_account", "4", "DS100"),
    // 风险账号 -- 腾讯风险账户
    HIGH_RISK_RISK_ACCOUNT_TY100("high_risk_risk_account", "4", "TY100"),
    // 风险账号 -- 腾讯小号
    HIGH_RISK_RISK_ACCOUNT_TIANYU("high_risk_risk_account", "4", "tianyu"),
    // 风险账号 -- 阿里小号
    HIGH_RISK_RISK_ACCOUNT_MAD("high_risk_risk_account", "4", "mad"),
    // 风险账号 -- 版本异常
    HIGH_RISK_RISK_ACCOUNT_RISK_VERSION("high_risk_risk_account", "4", "risk_version"),
    // 风险账号 -- 鲲鹏黑名单
    HIGH_RISK_RISK_ACCOUNT_KUNPENG_BLACKLIST("high_risk_risk_account", "4", "kengpeng_blacklist"),
    // 风险账号 -- 签到低质量
    HIGH_RISK_RISK_ACCOUNT_PAGEVIEW_MILEAGE("high_risk_risk_account", "4", "pageview-mileage"),
    // 风险账号 -- 恶意下单
    HIGH_RISK_RISK_ACCOUNT_MALEVOLENCE_ORDER("high_risk_risk_account", "4", "malevolence_order"),
    // 风险账号 -- 恶意赔付
    HIGH_RISK_RISK_ACCOUNT_MALICIOUS_CLAIM("high_risk_risk_account", "4", "malicious_claim"),
    // 风险账号 -- 注销异常
    HIGH_RISK_RISK_ACCOUNT_REGISTER_CANCEL("high_risk_risk_account", "4", "register_cancel"),
    // 风险账号 -- 非正常手机号(实时)
    HIGH_RISK_RISK_ACCOUNT_ABNORMAL_PHONE("high_risk_risk_account", "4", "abnormal_phone"),
    // 风险账号 -- 虚拟手机号
    HIGH_RISK_RISK_ACCOUNT_VIRTUAL_PHONE("high_risk_risk_account", "4", "virtual_phone"),
    // 风险账号 -- 景区诈骗用户行为规则识别
    HIGH_RISK_RISK_ACCOUNT_SWINDLE_RULE("high_risk_risk_account", "4", "swindle_rule"),
    // 风险账号 -- 景区诈骗关联名单
    HIGH_RISK_RISK_ACCOUNT_SWINDLE_LINK("high_risk_risk_account", "4", "swindle_link"),
    // 风险账号 -- 景区诈骗badcase名单
    HIGH_RISK_RISK_ACCOUNT_SWINDLE_BLACK("high_risk_risk_account", "4", "swindle_black"),

    // 黄牛用户 -- 市场秒杀黄牛
    HIGH_RISK_SCALPER_USER_S101("high_risk_scalper_user", "5", "S101"),
    // 黄牛用户 -- 设备聚集黄牛
    HIGH_RISK_SCALPER_USER_S202("high_risk_scalper_user", "5", "S202"),
    // 黄牛用户 -- 火车票黄牛手机号聚集黄牛
    HIGH_RISK_SCALPER_USER_S301("high_risk_scalper_user", "5", "S301"),
    // 黄牛用户 -- 火车票黄牛设备聚集黄牛
    HIGH_RISK_SCALPER_USER_S302("high_risk_scalper_user", "5", "S302"),
    // 黄牛用户 -- 酒店黄牛手机号聚集黄牛
    HIGH_RISK_SCALPER_USER_S401("high_risk_scalper_user", "5", "S401"),
    // 黄牛用户 -- 市场中心签到聚集黄牛
    HIGH_RISK_SCALPER_USER_S501("high_risk_scalper_user", "5", "S501"),
    // 黄牛用户 -- 手机号聚集黄牛
    HIGH_RISK_SCALPER_USER_S201("high_risk_scalper_user", "5", "S201"),

    // 关联账号 -- 设备连坐
    HIGH_RISK_ASS_ACCOUNT_I101("high_risk_ass_account", "6","I101"),
    // 关联账号 -- 支付账户连坐
    HIGH_RISK_ASS_ACCOUNT_I201("high_risk_ass_account", "6", "I201"),
    // 关联账号 -- 助力活动连坐
    HIGH_RISK_ASS_ACCOUNT_I301("high_risk_ass_account", "6", "I301"),

    // 负营收 -- 负营收账户
    HIGH_RISK_TOTAL_TAKE_OFF_T101("high_risk_total_takeoff", "7","T101"),

    // 综合分数 -- 风险标签模型分数
    HIGH_RISK_TOTAL_SCORE_SCORE_CARD("hig_risk_total_score", "8", "score_card");

    private final String riskTag;
    private final String service;

    private final String riskType;

    public String getRiskType() {
        return riskType;
    }

    public String getRiskTag() {
        return riskTag;
    }

    public String getService() {
        return service;
    }


}
