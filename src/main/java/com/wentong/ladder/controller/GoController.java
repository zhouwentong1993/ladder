package com.wentong.ladder.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wentong.ladder.code.Address;
import com.wentong.ladder.code.Door;
import com.wentong.ladder.entity.MetaHttpRequestEntity;
import com.wentong.ladder.handler.MappingHandler;
import com.wentong.ladder.handler.impl.ClassMappingHandler;
import com.wentong.ladder.mapper.MetaHttpRequestMapper;
import com.wentong.ladder.utils.ReflectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/go")
@RestController
public class GoController {

    @Autowired
    private MetaHttpRequestMapper metaHttpRequestMapper;

    @GetMapping("test/{refPlatform}/{business}")
    public String test(@PathVariable String refPlatform, @PathVariable String business) throws Exception {
        MetaHttpRequestEntity metaHttpRequestEntity = metaHttpRequestMapper.selectOne(Wrappers.<MetaHttpRequestEntity>lambdaQuery().eq(MetaHttpRequestEntity::getRefBusiness, business).eq(MetaHttpRequestEntity::getRefPlatform, refPlatform));
        if (metaHttpRequestEntity == null) {
            return "not found";
        }
        String refClass = metaHttpRequestEntity.getRefClass();
        RawObj rawObj = getRawObj();
        MappingHandler<RawObj, Object> mappingHandler = new ClassMappingHandler<>();
        Object o = ReflectUtil.getNoArgsConstructor(Class.forName(refClass)).newInstance();
        Object mapResult = mappingHandler.mapping(rawObj, o);

        return "test";
    }

    private RawObj getRawObj() {
        return new RawObj("wentong", "value", 18, new Address("china", "beijing", new Door("front door", "back door")));
    }

}

@Data
@AllArgsConstructor
class RawObj {
    String name;
    String value;
    int age;
    Address address;
}