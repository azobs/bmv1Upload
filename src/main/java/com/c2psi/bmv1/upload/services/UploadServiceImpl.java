package com.c2psi.bmv1.upload.services;

import com.c2psi.bmv1.BMGlobalArguments;
import com.c2psi.bmv1.upload.exceptions.UploadFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service(value = "UploadServiceV1")
@Transactional
@Slf4j
public class UploadServiceImpl implements UploadService{

    private Path root;
    private Path personRoot;

    @Autowired
    public UploadServiceImpl() {
        log.warn("Initialization of directories in which file will be uploaded{}", BMGlobalArguments.photosPersonsDir);
        this.root = Paths.get(BMGlobalArguments.photosPersonsDir);
        this.personRoot = Paths.get(BMGlobalArguments.photosPersonsDir);
    }

    @Override
    public String uploadPersonImage(MultipartFile fileToUpload) {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        try {
            fileToUpload.transferTo(Paths.get(personRoot+fileSeparator+fileToUpload.getOriginalFilename()));
            return fileToUpload.getOriginalFilename();
            //Files.copy(fileToUpload.getInputStream(), this.personRoot.resolve(fileToUpload.getOriginalFilename()));
        } catch (IOException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    public Resource loadPersonImage(String filename) {
        try {
            Path file = personRoot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new UploadFileException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new UploadFileException("Error: " + e.getMessage());
        }
    }

    @Override
    public Resource loadImage(String filename) {
        Resource resource = loadPersonImage(filename);
        return resource;
    }

    @Override
    public void deleteAllPerson() {
        FileSystemUtils.deleteRecursively(personRoot.toFile());
    }

}
