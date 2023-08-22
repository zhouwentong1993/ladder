package com.wentong.ladder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 映射对象元描述
 */
@Data
public class MetaRequestEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String fieldName;
    private String fieldType;
    private String expression;
    @TableField("`desc`")
    private String desc;
    private String mappedType;
    private String className;
    private String refBusiness;
    private String refPlatform;
    private boolean deleted;
    private Date createTime;
    private Date updateTime;

}
