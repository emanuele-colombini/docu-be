package it.ecolombini.docube.controllers.files;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.ecolombini.docube.beans.FolderResp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/folder")
public class FilesController {

    @Autowired FilesDelegate delegate;
    
    @Operation(summary = "Return user's root folder", description = "Retrieve logged user's root folder")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Root folder content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FolderResp.class))),
        @ApiResponse(responseCode = "401", description = "User not logged")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FolderResp root() {
        log.info("retrieving person's root folder");
        return delegate.getRootFolder();
    }

    @Operation(summary = "Return folder", description = "Retrieve folder specified by path variable, unable to accomplish if user does not have access to the resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Root folder content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FolderResp.class))),
        @ApiResponse(responseCode = "401", description = "User not logged"),
        @ApiResponse(responseCode = "403", description = "User cannot list folder")
    })
    @GetMapping(path = "{folderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FolderResp list(@PathVariable("folderId") UUID folderId) {
        log.info("Reading folder {}", folderId);
        return delegate.getFolder(folderId);
    }
}
