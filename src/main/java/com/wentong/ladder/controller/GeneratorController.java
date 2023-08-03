package com.wentong.ladder.controller;

import com.wentong.ladder.template.TemplateGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    private TemplateGenerator templateGenerator;

    @GetMapping("all")
    public void generateAll() {
        templateGenerator.generateAll();
    }


}
