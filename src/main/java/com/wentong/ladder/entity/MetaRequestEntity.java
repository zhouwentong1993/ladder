package com.wentong.ladder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class MetaRequestEntity {

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
