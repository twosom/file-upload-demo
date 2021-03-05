package com.icloud.fileuploaddemo.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class FileUploadResponse {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
