package com.wentong.ladder.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("get")
    public String hello() {
        return "hello";
    }

    @PostMapping("post")
    public String post(@RequestBody Map map) {
        System.out.println(map);
        return "post";
    }

}
