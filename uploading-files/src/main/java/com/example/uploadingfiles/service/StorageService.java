package com.example.uploadingfiles.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Great
 */
public interface StorageService {

    /**
     * init
     *
     */
    void init();

    /**
     * store file
     *
     * @param file file
     */
    void store(MultipartFile file);

    /**
     * loadAll
     *
     * @return Stream
     */
    Stream<Path> loadAll();

    /**
     * load
     *
     * @param filename filename
     * @return path
     */
    Path load(String filename);

    /**
     * load as resource
     *
     * @param filename filename
     *
     * @return Resource
     */
    Resource loadAsResource(String filename);

    /**
     * delete all file
     *
     */
    void deleteAll();
}
