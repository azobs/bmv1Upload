package com.c2psi.bmv1.upload.services;

import com.c2psi.bmv1.BMGlobalArguments;
import com.c2psi.bmv1.upload.exceptions.UploadFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service(value = "FilerenameServiceV1")
@Transactional
@Slf4j
public class FilerenameServiceImpl implements FilerenameService{
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
                    //boolean result = Files.deleteIfExists(Paths.get(personRoot+fileSeparator+id));
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
}
