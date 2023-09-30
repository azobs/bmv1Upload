package com.c2psi.bmv1.upload.services;

import com.c2psi.bmv1.BMGlobalArguments;
import com.c2psi.bmv1.bmapp.exceptions.UploadFileException;
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
    private Path articleRoot;
    private Path invoiceRoot;
    private Path logoRoot;
    private Path pfRoot;

    @Autowired
    public UploadServiceImpl() {
        log.warn("Initialization of directories in which file will be uploaded{}", BMGlobalArguments.photosPersonsDir);
        this.root = Paths.get(BMGlobalArguments.photosPersonsDir);
        this.articleRoot = Paths.get(BMGlobalArguments.photosArticlesDir);
        this.personRoot = Paths.get(BMGlobalArguments.photosPersonsDir);
        this.invoiceRoot = Paths.get(BMGlobalArguments.photosInvoicesDir);
        this.logoRoot = Paths.get(BMGlobalArguments.photosLogosDir);
        this.pfRoot = Paths.get(BMGlobalArguments.photosPfDir);
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

    @Override
    public String uploadArticleImage(MultipartFile fileToUpload) {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        try {
            fileToUpload.transferTo(Paths.get(articleRoot+fileSeparator+fileToUpload.getOriginalFilename()));
            return fileToUpload.getOriginalFilename();
        } catch (IOException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
    public String uploadInvoiceImage(MultipartFile fileToUpload) {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        try {
            fileToUpload.transferTo(Paths.get(invoiceRoot+fileSeparator+fileToUpload.getOriginalFilename()));
            return fileToUpload.getOriginalFilename();
        } catch (IOException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
    public String uploadLogoImage(MultipartFile fileToUpload) {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        try {
            fileToUpload.transferTo(Paths.get(logoRoot+fileSeparator+fileToUpload.getOriginalFilename()));
            return fileToUpload.getOriginalFilename();
        } catch (IOException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
    public String uploadPfImage(MultipartFile fileToUpload) {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        try {
            fileToUpload.transferTo(Paths.get(pfRoot+fileSeparator+fileToUpload.getOriginalFilename()));
            return fileToUpload.getOriginalFilename();
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

    public Resource loadArticleImage(String filename) {
        try {
            Path file = articleRoot.resolve(filename);
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

    public Resource loadInvoiceImage(String filename) {
        try {
            Path file = invoiceRoot.resolve(filename);
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

    public Resource loadLogoImage(String filename) {
        try {
            Path file = logoRoot.resolve(filename);
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

    public Resource loadPfImage(String filename) {
        try {

            Path file = logoRoot.resolve(filename);
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
        Resource resource = loadArticleImage(filename);
        if(resource == null){
            resource = loadPfImage(filename);
        }
        if(resource == null){
            resource = loadLogoImage(filename);
        }
        if(resource == null){
            resource = loadPersonImage(filename);
        }
        if(resource == null){
            resource = loadInvoiceImage(filename);
        }
        return resource;
    }

    @Override
    public void deleteAllPerson() {
        FileSystemUtils.deleteRecursively(personRoot.toFile());
    }

    @Override
    public void deleteAllArticle() {
        FileSystemUtils.deleteRecursively(articleRoot.toFile());
    }

    @Override
    public void deleteAllInvoice() {
        FileSystemUtils.deleteRecursively(invoiceRoot.toFile());
    }

    @Override
    public void deleteAllLogo() {
        FileSystemUtils.deleteRecursively(logoRoot.toFile());
    }
}
