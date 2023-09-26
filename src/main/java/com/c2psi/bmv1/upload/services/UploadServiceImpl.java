package com.c2psi.bmv1.upload.services;

import com.c2psi.bmv1.BMGlobalArguments;
import com.c2psi.bmv1.arrival.supplyinvoice.dao.SupplyinvoiceDao;
import com.c2psi.bmv1.arrival.supplyinvoice.models.Supplyinvoice;
import com.c2psi.bmv1.bmapp.exceptions.ModelNotFoundException;
import com.c2psi.bmv1.bmapp.exceptions.UploadFileException;
import com.c2psi.bmv1.pos.enterprise.dao.EnterpriseDao;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import com.c2psi.bmv1.product.article.dao.ArticleDao;
import com.c2psi.bmv1.product.article.models.Article;
import com.c2psi.bmv1.product.pf.dao.ProductformatedDao;
import com.c2psi.bmv1.product.pf.models.Productformated;
import com.c2psi.bmv1.userbm.dao.UserbmDao;
import com.c2psi.bmv1.userbm.models.Userbm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Optional;

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
    private UserbmDao userbmDao;
    private ArticleDao articleDao;
    private ProductformatedDao pfDao;
    private SupplyinvoiceDao siDao;
    private EnterpriseDao entDao;

    @Autowired
    public UploadServiceImpl(UserbmDao userbmDao1, ArticleDao articleDao1, ProductformatedDao pfDao1,
                             SupplyinvoiceDao siDao1, EnterpriseDao entDao1) {
        log.warn("Initialization of directories in which file will be uploaded{}", BMGlobalArguments.photosPersonsDir);
        this.root = Paths.get(BMGlobalArguments.photosPersonsDir);
        this.articleRoot = Paths.get(BMGlobalArguments.photosArticlesDir);
        this.personRoot = Paths.get(BMGlobalArguments.photosPersonsDir);
        this.invoiceRoot = Paths.get(BMGlobalArguments.photosInvoicesDir);
        this.logoRoot = Paths.get(BMGlobalArguments.photosLogosDir);
        this.pfRoot = Paths.get(BMGlobalArguments.photosPfDir);

        this.userbmDao = userbmDao1;
        this.articleDao = articleDao1;
        this.pfDao = pfDao1;
        this.siDao = siDao1;
        this.entDao = entDao1;
    }

    @Override
    public String uploadPersonImage(Long id, MultipartFile fileToUpload) {
        log.info("method uploadImageofPerson in execution");
        try {
            log.info("File upload method saveImageofPerson in try section");
            /****************************************************************************************
             * We must first check if the id correspond exactly to a user
             */
            Optional<Userbm> optionalUserbm = userbmDao.findUserbmById(id);
            if(!optionalUserbm.isPresent()){
                throw new ModelNotFoundException("Aucun userbm ne porte cet id dans le system ");
            }
            //Userbm userbmToUpdate = optionalUserbm.get();

            /****************************************************************************************
             * First of all we must delete an image with the same name in the directory if it exist
             */
            String fileSeparator = FileSystems.getDefault().getSeparator();
            boolean result = Files.deleteIfExists(Paths.get(personRoot+fileSeparator+id));

            /*******************************************************************************************************
             * Now we can upload the file in the folder
             */
            Files.copy(fileToUpload.getInputStream(), this.personRoot.resolve(fileToUpload.getOriginalFilename()));
            /************************************************************************************
             * Run the gabage collector to avoid any TomCat exception like UncheckedIOException
             * cannot delete ...
             */
            System.gc();
            /**************************************************************************************
             * After uploading the file we must rename the uploaded file with the id of the object
             * associated
             */
            this.renameFile(BMGlobalArguments.photosPersonsDir, fileToUpload.getOriginalFilename(), String.valueOf(id));

            /////////////////////Juste pour tester la methode rename
            //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1");
            ////////////////////
            return fileToUpload.getOriginalFilename();
        }
        catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                log.info("A file of that name {} already exists.", fileToUpload.getOriginalFilename());
                //throw new RuntimeException("A file of that name already exists.");
                //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1.png");

                return fileToUpload.getOriginalFilename();
            }
            log.error("Un autre type d'exception est lance {}", e.getMessage());
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
    public String uploadArticleImage(Long id, MultipartFile fileToUpload) {
        log.info("method uploadImageofArticle in execution");
        try {
            log.info("File upload method saveImageofArticle in try section");
            /****************************************************************************************
             * We must first check if the id correspond exactly to a user
             */
            Optional<Article> optionalArticle = articleDao.findArticleById(id);
            if(!optionalArticle.isPresent()){
                throw new ModelNotFoundException("Aucun article ne porte cet id dans le system ");
            }

            /****************************************************************************************
             * First of all we must delete an image with the same name in the directory if it exist
             */
            String fileSeparator = FileSystems.getDefault().getSeparator();
            boolean result = Files.deleteIfExists(Paths.get(articleRoot+fileSeparator+id));

            /*******************************************************************************************************
             * Now we can upload the file in the folder
             */

            Files.copy(fileToUpload.getInputStream(), this.articleRoot.resolve(fileToUpload.getOriginalFilename()));
            /************************************************************************************
             * Run the gabage collector to avoid any TomCat exception like UncheckedIOException
             * cannot delete ...
             */
            System.gc();
            /**************************************************************************************
             * After uploading the file we must rename the uploaded file with the id of the object
             * associated
             */
            this.renameFile(BMGlobalArguments.photosArticlesDir, fileToUpload.getOriginalFilename(), String.valueOf(id));
            /////////////////////Juste pour tester la methode rename
            //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1");
            ////////////////////
            return fileToUpload.getOriginalFilename();
        }
        catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                log.info("A file of that name {} already exists.", fileToUpload.getOriginalFilename());
                //throw new RuntimeException("A file of that name already exists.");
                //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1.png");
                return fileToUpload.getOriginalFilename();
            }
            log.error("Un autre type d'exception est lance {}", e.getMessage());
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
    public String uploadInvoiceImage(Long id, MultipartFile fileToUpload) {
        log.info("method uploadImageofInvoice in execution");
        try {
            log.info("File upload method saveImageofInvoice in try section");
            /****************************************************************************************
             * We must first check if the id correspond exactly to a user
             */
            Optional<Supplyinvoice> optionalSupplyinvoice = siDao.findSupplyinvoiceById(id);
            if(!optionalSupplyinvoice.isPresent()){
                throw new ModelNotFoundException("Aucune supplyinvoice ne porte cet id dans le system ");
            }
            /****************************************************************************************
             * First of all we must delete an image with the same name in the directory if it exist
             */
            String fileSeparator = FileSystems.getDefault().getSeparator();
            boolean result = Files.deleteIfExists(Paths.get(invoiceRoot+fileSeparator+id));

            /*******************************************************************************************************
             * Now we can upload the file in the folder
             */
            Files.copy(fileToUpload.getInputStream(), this.invoiceRoot.resolve(fileToUpload.getOriginalFilename()));
            /************************************************************************************
             * Run the gabage collector to avoid any TomCat exception like UncheckedIOException
             * cannot delete ...
             */
            System.gc();
            /**************************************************************************************
             * After uploading the file we must rename the uploaded file with the id of the object
             * associated
             */
            this.renameFile(BMGlobalArguments.photosInvoicesDir, fileToUpload.getOriginalFilename(), String.valueOf(id));
            /////////////////////Juste pour tester la methode rename
            //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1");
            ////////////////////
            return fileToUpload.getOriginalFilename();
        }
        catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                log.info("A file of that name {} already exists.", fileToUpload.getOriginalFilename());
                //throw new RuntimeException("A file of that name already exists.");
                //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1.png");
                return fileToUpload.getOriginalFilename();
            }
            log.error("Un autre type d'exception est lance {}", e.getMessage());
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
    public String uploadLogoImage(Long id, MultipartFile fileToUpload) {
        log.info("method uploadImageofLogo in execution");
        try {
            log.info("File upload method saveImageofLogo in try section");
            /****************************************************************************************
             * We must first check if the id correspond exactly to a user
             */
            Optional<Enterprise> optionalEnterprise = entDao.findEnterpriseById(id);
            if(!optionalEnterprise.isPresent()){
                throw new ModelNotFoundException("Aucune entreprise ne porte cet id dans le system ");
            }
            /****************************************************************************************
             * First of all we must delete an image with the same name in the directory if it exist
             */
            String fileSeparator = FileSystems.getDefault().getSeparator();
            boolean result = Files.deleteIfExists(Paths.get(logoRoot+fileSeparator+id));

            /*******************************************************************************************************
             * Now we can upload the file in the folder
             */
            Files.copy(fileToUpload.getInputStream(), this.logoRoot.resolve(fileToUpload.getOriginalFilename()));
            /************************************************************************************
             * Run the gabage collector to avoid any TomCat exception like UncheckedIOException
             * cannot delete ...
             */
            System.gc();
            /**************************************************************************************
             * After uploading the file we must rename the uploaded file with the id of the object
             * associated
             */
            this.renameFile(BMGlobalArguments.photosLogosDir, fileToUpload.getOriginalFilename(), String.valueOf(id));
            /////////////////////Juste pour tester la methode rename//////////////////////////////////
            //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1"); //
            //////////////////////////////////////////////////////////////////////////////////////////
            return fileToUpload.getOriginalFilename();
        }
        catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                log.info("A file of that name {} already exists.", fileToUpload.getOriginalFilename());
                //throw new RuntimeException("A file of that name already exists.");
                //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1.png");
                return fileToUpload.getOriginalFilename();
            }
            log.error("Un autre type d'exception est lance {}", e.getMessage());
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
    public String uploadPfImage(Long id, MultipartFile fileToUpload) {
        log.info("method uploadImageofProductformated in execution");
        try {
            log.info("File upload method saveImageofProductformated in try section");
            /****************************************************************************************
             * We must first check if the id correspond exactly to a user
             */
            Optional<Productformated> optionalPf = pfDao.findProductformatedById(id);
            if(!optionalPf.isPresent()){
                throw new ModelNotFoundException("Aucun productformated ne porte cet id dans le system ");
            }
            /****************************************************************************************
             * First of all we must delete an image with the same name in the directory if it exist
             */
            String fileSeparator = FileSystems.getDefault().getSeparator();
            boolean result = Files.deleteIfExists(Paths.get(pfRoot+fileSeparator+id));

            /*******************************************************************************************************
             * Now we can upload the file in the folder
             */
            Files.copy(fileToUpload.getInputStream(), this.pfRoot.resolve(fileToUpload.getOriginalFilename()));
            /************************************************************************************
             * Run the gabage collector to avoid any TomCat exception like UncheckedIOException
             * cannot delete ...
             */
            System.gc();
            /**************************************************************************************
             * After uploading the file we must rename the uploaded file with the id of the object
             * associated
             */
            this.renameFile(BMGlobalArguments.photosPfDir, fileToUpload.getOriginalFilename(), String.valueOf(id));
            /////////////////////Juste pour tester la methode rename//////////////////////////////////
            //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1"); //
            //////////////////////////////////////////////////////////////////////////////////////////
            return fileToUpload.getOriginalFilename();
        }
        catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                log.info("A file of that name {} already exists.", fileToUpload.getOriginalFilename());
                //throw new RuntimeException("A file of that name already exists.");
                //this.renameFile(BMGlobalArguments.photosPersonsDir, file.getOriginalFilename(), "1.png");
                return fileToUpload.getOriginalFilename();
            }
            log.error("Un autre type d'exception est lance {}", e.getMessage());
            throw new UploadFileException(e.getMessage());
        }
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
    public Boolean renameFile(String folderName, String oldName, String newName) {
        log.info("Execution de renameFile");
        try {
            File directory = new File(folderName);
            String[] fileList = directory.list();
            Path oldFile = null;

            /*************************************************************
             * On fait une recherche du fichier a renommer
             */
            for (String fileName : fileList) {
                if (fileName.equalsIgnoreCase(oldName)) {
                    /*****************************************
                     * On recupere donc le fichier a renommer
                     */
                    oldFile = Paths.get(BMGlobalArguments.photosPersonsDir + "/" + oldName);
                }
            }

            if(oldFile != null){
                try {
                    Files.move(oldFile, oldFile.resolveSibling(newName), StandardCopyOption.REPLACE_EXISTING);
                    log.info("File Successfully Rename");
                } catch (IOException e) {
                    log.info("Renaming Operation failed !!!" + e.getMessage());
                    throw new UploadFileException("Renaming Operation failed !!! "+e.getMessage());
                }
            }
        }
        catch (Exception e){
            log.info("No folder for folderName {} precised during the renaming process ", folderName);
        }
        return true;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
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
