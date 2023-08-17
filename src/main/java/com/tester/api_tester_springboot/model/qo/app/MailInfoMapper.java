package com.tester.api_tester_springboot.model.qo.app;

import lombok.Data;

/**
 * @description:
 * @author: 王海虹
 * @create: 2022-08-30 16:52
 */
@Data
public class MailInfoMapper {
    private String serverHost;
    private String serverPort;
    private String userName;
    private String userPwd;
}
