package it.ecolombini.docube.controllers.markdown.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MarkdownFile {
    private String name;
    private Long version;
    private String content;
}
