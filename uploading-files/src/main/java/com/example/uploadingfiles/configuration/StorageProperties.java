package com.example.uploadingfiles.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Great
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String uploadPath = "/uploads";

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
