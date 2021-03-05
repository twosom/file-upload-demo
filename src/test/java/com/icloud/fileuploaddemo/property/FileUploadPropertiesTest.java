package com.icloud.fileuploaddemo.property;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadPropertiesTest {

    @Autowired
    FileUploadProperties properties;

    @Test
    public void fileLocationTest() throws Exception {
        String uploadDir = properties.getUploadDir();
        System.out.println("uploadDir = " + uploadDir);

        Path fileLocation = Paths.get(properties.getUploadDir())
                .toAbsolutePath()
                .normalize();
        System.out.println("fileLocation = " + fileLocation);
    }

}