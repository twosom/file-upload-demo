package com.icloud.fileuploaddemo.service;

import com.icloud.fileuploaddemo.exception.FileDownloadException;
import com.icloud.fileuploaddemo.exception.FileUploadException;
import com.icloud.fileuploaddemo.property.FileUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class FileUploadDownloadService {

    private final Path fileLocation;

    @Autowired
    public FileUploadDownloadService(FileUploadProperties properties) {
        this.fileLocation = Paths.get(properties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    //파일 저장하는 소스 작성
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            //파일명에 부적합 문자 있는지 여부
            if (fileName.contains(".."))
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다." + fileName);
            //기존 경로에 파일명을 붙여서 경로객체로 반환
            Path targetLocation = this.fileLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도해주세요.", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
            }
        } catch (MalformedURLException e) {
            throw new FileDownloadException(fileName + "파일을 찾을 수 없습니다.", e);
        }

    }
}
