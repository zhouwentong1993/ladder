package com.wentong.ladder.code.pt365;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.wentong.ladder.annotations.MappedClass;
import com.wentong.ladder.annotations.MappedField;
import com.wentong.ladder.enums.MappedType;

/**
 * Auto generated class.
 * DO NOT MODIFY!!!
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedClass
public class PT365Token {

    @MappedField(expression = "temporaryModel.storeChannelBean.special_key", desc = "开放平台注册用户ID", type = MappedType.EXPRESSION)
    private String userOpenId;
    @MappedField(expression = "temporaryModel.storeChannelBean.secret", desc = "密码", type = MappedType.EXPRESSION)
    private String password;
    @MappedField(expression = "temporaryModel.storeChannelBean.appKey", desc = "服务密钥（去首页->现在开始->开发服务中查看）", type = MappedType.EXPRESSION)
    private String serviceKey;

}
