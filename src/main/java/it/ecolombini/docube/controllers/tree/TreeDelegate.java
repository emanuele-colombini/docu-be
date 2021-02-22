package it.ecolombini.docube.controllers.tree;

import org.springframework.stereotype.Component;

import it.ecolombini.docube.beans.TreeResp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TreeDelegate {
    public static final int NO_DEPTH = 0;

    TreeResp listRootFolderContent(String path, Integer depth) {
        log.info("Listing root content folder for path {}, with depth {}", path, depth);
        return null;
    }    

    TreeResp listPrjFolderContent(String project, String path, Integer depth) {
        log.info("Listing project {} content folder for path {}, with depth {}", project, path, depth);
        return null;
    }    
}
