package com.wentong.ladder.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/form")
public class FormController {

    @PostMapping("/submit")
    public String submit(@RequestBody Map map) {
        // TODO
        System.out.println(map);
        return "success";
    }

}
