package com.wentong.ladder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 映射对象元描述
 */
@Data
public class MetaRequestEntity implements Serializable {

    private Long id;
    private String fieldName;
    private String fieldType;
    private String expression;
    @TableField("`desc`")
    private String desc;
    private String mappedType;
    private String className;
    private String refBusiness;
    private Date createTime;
    private Date updateTime;

}
