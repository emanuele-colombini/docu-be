package it.ecolombini.docube.beans;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FolderFileResp {
    private String name;
    private Long version;
    private String path;
}
