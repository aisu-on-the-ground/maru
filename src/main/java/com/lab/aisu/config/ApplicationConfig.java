package com.lab.aisu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DBConfig.class })
@ComponentScan(basePackages = { "com.lab.aisu.dao", "com.lab.aisu.serviceimpl" })
public class ApplicationConfig {

}
