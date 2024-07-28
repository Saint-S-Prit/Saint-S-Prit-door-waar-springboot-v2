package com.espritech.workerExpress221.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "conf")
@Data
public class ConfigProperties {
        private String upload;
        private Long fileSize;
}
