package it.ecolombini.docube.beans;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FolderResp {
    private UUID id;
    private String name;
    private String path;
    private List<FolderFileResp> files;
}
