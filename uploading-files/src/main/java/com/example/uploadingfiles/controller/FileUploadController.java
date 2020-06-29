package com.example.uploadingfiles.controller;

import com.example.uploadingfiles.exception.StorageFileNotFoundException;
import com.example.uploadingfiles.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

/**
 * @author Great
 */
@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {
        model.addAttribute(
                "files",
                storageService.loadAll()
                        .map(path -> MvcUriComponentsBuilder.fromMethodName(
                                FileUploadController.class,
                                "serveFile",
                                path.getFileName()
                                        .toString()
                                )
                                        .build()
                                        .toUri()
                                        .toString()
                        )
                        .collect(Collectors.toList())
        );
        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute(
                "message",
                "You successfully uploaded " + file.getOriginalFilename() + "!"
        );
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(
            StorageFileNotFoundException exception
    ) {
        return ResponseEntity.notFound().build();
    }
}
