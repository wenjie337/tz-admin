package com.taozhi.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config.properties")
public class PropertiesConfig {

    @Value("${page_size}")
    private String pageSize;

    public int getPageSize() {
        return Integer.parseInt(pageSize);
    }

}
