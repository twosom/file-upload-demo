package com.icloud.fileuploaddemo.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration @Getter @Setter
@Primary
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {
    private String uploadDir;
}

