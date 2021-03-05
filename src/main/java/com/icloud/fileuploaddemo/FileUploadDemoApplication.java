package com.icloud.fileuploaddemo;

import com.icloud.fileuploaddemo.property.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
        FileUploadProperties.class
})
@SpringBootApplication
public class FileUploadDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadDemoApplication.class, args);
    }

}
