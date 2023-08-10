package com.wentong.ladder.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * http 请求元数据表
 */
@Data
public class MetaHttpRequestEntity implements Serializable {

    private Long id;
    private String url;
    private String contentType;
    /**
     * 请求协议
     */
    private String protocol;
    /**
     * 请求类型，POST GET
     */
    private String requestType;
    /**
     * 字符编码
     */
    private String characterEncoding;
    /**
     * 所属业务，比如某个配送平台
     */
    private String refPlatform;
    /**
     * 所属业务，比如查询价格
     */
    private String refBusiness;

    /**
     * 所属的请求类
     */
    private String refClass;

    // --------------上面的是请求的字段----------------

    /**
     * 响应映射的类
     */
    private String responseClass;

    /**
     * 响应成功的判断，只有当响应成功后才会进行映射
     */
    private String responseSuccessJudgement;

    /**
     * 响应的 message 字段，通常用于错误提示
     */
    private String responseMessage;

    /**
     * 响应映射字段逻辑
     */
    private String responseMapping;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}