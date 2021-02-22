package it.ecolombini.docube.controllers.tree;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import it.ecolombini.docube.beans.TreeResp;

@RestController
@RequestMapping("api/tree")
public class TreeController {
    
    @Autowired TreeDelegate delegate;
   
    @GetMapping(path = "root/**", produces = MediaType.APPLICATION_JSON_VALUE)
    public TreeResp listRootFolder(HttpServletRequest request, @RequestParam("deph") Optional<Integer> depth) {
        String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String subPath = StringUtils.defaultIfEmpty(StringUtils.removeStart(fullPath, "/api/tree/root"), "/");
        return delegate.listRootFolderContent(subPath, depth.orElse(TreeDelegate.NO_DEPTH));
    }

    @GetMapping(path = "{project}/**", produces = MediaType.APPLICATION_JSON_VALUE)
    public TreeResp listPrjFolder(HttpServletRequest request, @PathVariable("project") String project, @RequestParam("deph") Optional<Integer> depth) {
        String fullPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String subPath = StringUtils.defaultIfEmpty(StringUtils.removeStart(fullPath, "/api/tree/" + project), "/");
        return delegate.listPrjFolderContent(project, subPath, depth.orElse(TreeDelegate.NO_DEPTH));
    }

    @PostMapping(path = "root/**", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createFolder(HttpServletRequest request, @RequestBody() Object folder) {
        // create a folder entity
    }

    @PatchMapping(path = "root/**", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void patchFolder(HttpServletRequest request, @RequestBody() Object folder) {
        // rename or change permissions to a folder (also files?)
    }
}
