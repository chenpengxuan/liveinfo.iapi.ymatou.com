/**
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/).
 *
 * All rights reserved.
 */
package com.ymatou.liveinfo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ImportResource("classpath:spring/spring-extra-beans.xml")
public class LiveInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveInfoApplication.class, args);
    }
}
