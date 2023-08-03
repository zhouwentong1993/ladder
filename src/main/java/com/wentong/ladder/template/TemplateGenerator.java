package com.wentong.ladder.template;

import cn.hutool.core.io.FileUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateGenerator {

    public static void main(String[] args) {
        // Step 1: 初始化Velocity引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        // Step 2: 加载模板
        Template template = velocityEngine.getTemplate("templates/MyClassTemplate.vm");

        // Step 3: 准备数据
        VelocityContext context = new VelocityContext();
        context.put("className", "Person");
        context.put("package", "com.wentong.ladder.code");
        List<Map<String,String>> list = new ArrayList<>();
        list.add(Map.of("name", "name", "type", "String", "expression","name", "desc", "姓名", "mappedType", "EXPRESSION"));
        list.add(Map.of("name", "age", "type", "int", "expression","1", "desc", "年龄", "mappedType", "CONSTANT"));
        context.put("list", list);


        // Step 4: 填充模板并生成Java类代码
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // Step 5: 获取生成的Java类代码
        String generatedClassCode = writer.toString();

        String absolutePath = FileUtil.getAbsolutePath("");
        // 查找最后一个路径分隔符的索引
        int lastIndex = absolutePath.lastIndexOf("/target/classes/");

        // 截取字符串，包括第一个字符，但不包括最后一个字符
        String result = absolutePath.substring(0, lastIndex + 1);

        // Step 6: 输出到文件
        String filePath = result + "src/main/java/com/wentong/ladder/code/Person.java";
        File file = FileUtil.writeUtf8String(generatedClassCode, filePath);

        System.out.println("生成的文件路径：" + file.getAbsolutePath());
    }

}
