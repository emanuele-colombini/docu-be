package it.ecolombini.docube.controllers.files;

import java.util.UUID;

import org.springframework.stereotype.Component;

import it.ecolombini.docube.beans.FolderResp;

@Component
public class FilesDelegate {
    
    FolderResp getRootFolder() {
        UUID userRootFolder = UUID.randomUUID();
        return getFolder(userRootFolder);
    }

    FolderResp getFolder(UUID folderId) {
        return null;
    }
}
