package com.wentong.ladder.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wentong.ladder.entity.MetaRequestEntity;
import com.wentong.ladder.mapper.MetaRequestMapper;
import com.wentong.ladder.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/form")
public class FormController {

    @Autowired
    private MetaRequestMapper metaRequestMapper;

    @PostMapping("/submit")
    public String submit(@RequestBody Map map) {
        String platform = MapUtil.getStr(map, "platformValue");
        String business = MapUtil.getStr(map, "businessValue");
        List rules = MapUtil.get(map, "rules", List.class);
        List<MetaRequestEntity> metaRequestEntities = BeanUtil.copyToList(rules, MetaRequestEntity.class);
        String className = getClassName(platform, business);
        metaRequestEntities.forEach(e -> {
            e.setRefPlatform(platform);
            e.setRefBusiness(business);
            e.setClassName(className);
            e.setCreateTime(new Date());
            e.setUpdateTime(new Date());
        });

        // 把和 platform 和 business 相等的数据 deleted 置为 true
        MetaRequestEntity metaRequestEntity = new MetaRequestEntity();
        metaRequestEntity.setDeleted(true);
        metaRequestMapper.update(metaRequestEntity, new UpdateWrapper<MetaRequestEntity>()
                .eq("ref_platform", platform)
                .eq("ref_business", business));

        metaRequestEntities.forEach(e -> metaRequestMapper.insert(e));

        return "success";
    }

    private static String getClassName(String platform, String business) {
        String[] split = business.split("_");
        StringBuilder real = new StringBuilder();
        for (String s : split) {
            real.append(CommonUtil.makeFirstLetterUpperCase(s.toLowerCase()));
        }
        return platform + real;
    }

}
