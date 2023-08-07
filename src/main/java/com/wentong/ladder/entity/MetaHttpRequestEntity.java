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
    private String refClass;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}