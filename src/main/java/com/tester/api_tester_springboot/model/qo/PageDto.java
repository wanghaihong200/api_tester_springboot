package com.tester.api_tester_springboot.model.qo;

import lombok.Data;

/**
 * @description:
 * @author: 王海虹
 * @create: 2023-03-09 19:44
 */
@Data
public class PageDto {
    Integer pageSize = 10;

    Integer page = 1;
}
