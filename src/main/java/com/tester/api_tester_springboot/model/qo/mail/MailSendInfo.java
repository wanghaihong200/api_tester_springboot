package com.tester.api_tester_springboot.model.qo.mail;

import lombok.Data;

import java.util.List;

/**
 * @description: 邮件发送信息类， 定义发送邮件时 邮件服务器 端口 之类的信息
 * @author: 王海虹
 * @create: 2022-08-16 20:14
 */
@Data
public class MailSendInfo {
    // 服务主机
    private String serverHost;
    // 服务器端口
    private String serverPort;
    // 发送方邮箱地址
    private String fromAddress;
    // 接受放邮箱地址
    private List<String> toAddresses;
    // 邮件服务器用户名
    private String userName;
    // 邮件服务器密码(授权密码)
    private String userPwd;
    // 邮件主题
    private String subject;
    // 邮件内容
    private String content;

    // 是否需要身份认证
    private Boolean flag = true;
    // 附件文件名
    private List<String> attachFileNames;

}
