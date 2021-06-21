package it.ecolombini.docube.controllers.markdown;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ecolombini.docube.controllers.markdown.beans.MarkdownFile;
import lombok.Cleanup;

@RestController
@RequestMapping("api/markdown")
public class MarkdownController {

	@GetMapping()
    public MarkdownFile getMarkdownFile() throws Throwable {
        
        @Cleanup InputStream inputStream = getClass().getResourceAsStream("/test.md"); 
        @Cleanup BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        
        return MarkdownFile.builder()
            .name("Test File")
            .version(1l)
            .content(content)
            .build();
    }
}
