package com.wentong.ladder.template;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wentong.ladder.entity.MetaRequestEntity;
import com.wentong.ladder.mapper.MetaRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TemplateGenerator {

    @Autowired
    private MetaRequestMapper metaRequestMapper;

    public void generateAll() {
        // step 0: 清空文件夹
        FileUtil.del(getWrittenPath());
        log.info("删除文件夹：{}", getWrittenPath());

        // Step 1: 初始化Velocity引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        // Step 2: 加载模板
        Template template = velocityEngine.getTemplate("templates/ClassTemplate.vm");

        List<MetaRequestEntity> allData = metaRequestMapper.selectList(new QueryWrapper<MetaRequestEntity>().eq("deleted", false));
        Map<String, List<MetaRequestEntity>> collect = allData.stream().collect(Collectors.groupingBy(MetaRequestEntity::getClassName));
        collect.forEach((className, v) -> {

            // Step 3: 准备数据
            VelocityContext context = new VelocityContext();
            context.put("className", className);
            context.put("package", "com.wentong.ladder.code." + v.get(0).getRefPlatform().toLowerCase());
            List<Map<String, String>> list = new ArrayList<>();
            v.forEach(metaRequestEntity -> {
                Map<String, String> map = Map.of("name", metaRequestEntity.getFieldName(), "type", metaRequestEntity.getFieldType(), "expression", metaRequestEntity.getExpression(), "desc", metaRequestEntity.getDesc(), "mappedType", metaRequestEntity.getMappedType());
                list.add(map);
            });
            context.put("list", list);

            // Step 4: 填充模板并生成Java类代码
            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            // Step 5: 获取生成的Java类代码
            String generatedClassCode = writer.toString();

            // Step 6: 输出到文件
            String filePath = getWrittenPath() + v.get(0).getRefPlatform().toLowerCase() + "/" + className + ".java";
            log.info("生成文件：{}", filePath);
            File file = FileUtil.writeUtf8String(generatedClassCode, filePath);
        });


    }

    private static String getWrittenPath() {
        String absolutePath = FileUtil.getAbsolutePath("");
        // 查找最后一个路径分隔符的索引
        int lastIndex = absolutePath.lastIndexOf("/target/classes/");

        // 截取字符串，包括第一个字符，但不包括最后一个字符
        String result = absolutePath.substring(0, lastIndex + 1);
        return result + "src/main/java/com/wentong/ladder/code/";
    }

}
