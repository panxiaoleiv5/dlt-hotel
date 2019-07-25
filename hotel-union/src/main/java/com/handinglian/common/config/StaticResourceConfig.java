package com.handinglian.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticResourceConfig extends WebMvcConfigurerAdapter {
    @Value("${file.location}")
    private String fileLocation;
    @Value("${download.url}")
    private String downloadUrl;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(downloadUrl+"**").addResourceLocations("file:"+fileLocation);
        super.addResourceHandlers(registry);
    }
}
