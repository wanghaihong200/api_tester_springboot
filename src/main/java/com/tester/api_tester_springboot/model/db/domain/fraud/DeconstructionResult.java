package com.tester.api_tester_springboot.model.db.domain.fraud;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 认证解限结果
 * @TableName deconstruction_result
 */
@TableName(value ="deconstruction_result")
@Data
public class DeconstructionResult implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * unionId
     */
    private String unionid;

    /**
     * 是否解限成功
     */
    private Integer deconstruction;

    /**
     * 0-默认 1-次数被限制 2-绑定的会员卡超过数量 3-三要素认证不通过 4-姓名长度超过限制 5-手机号要求格式正确 6-身份证号格式不正确 7-验证码输入错误 8-没有查询到用户信息
     */
    private Integer reason;

    /**
     * 解限维度
     */
    private String deconstrucyDimension;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date operateTime;

    /**
     * 
     */
    private String creator;

    /**
     * 
     */
    private String operator;

    /**
     * 
     */
    private Date timestamp;

    /**
     * 换绑次数
     */
    private Integer count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}