package it.ecolombini.docube;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("api/markdown")
public class MarkdownController {

	@GetMapping()
    public MarkdownFile getMarkdownFile() throws Throwable {
        String content = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/test.md");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } 

        return MarkdownFile.builder()
            .name("Test File")
            .version(1l)
            .content(content)
            .build();
    }
}
