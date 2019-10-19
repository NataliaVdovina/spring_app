package com.spring.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.spring.project.controller", "com.spring.project.service", "com.spring.project.dao"})
public class ApplicationConfig {

}
