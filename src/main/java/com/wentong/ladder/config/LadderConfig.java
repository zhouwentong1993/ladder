package com.wentong.ladder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ladder")
public class LadderConfig {

    public String ladderScanBasePackage;

    public String ladderFunctionScanBasePackage;

}
