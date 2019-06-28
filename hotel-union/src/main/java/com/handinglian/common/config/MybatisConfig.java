package com.handinglian.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/6 15:16
 */
@SpringBootConfiguration
@MapperScan("com.handinglian.*.mapper")
public class MybatisConfig {
}
