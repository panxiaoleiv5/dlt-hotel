package com.handinglian;

import com.handinglian.common.config.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class} )
@ServletComponentScan
@EnableScheduling
public class ApplicationHotel {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationHotel.class, args);
    }
}
