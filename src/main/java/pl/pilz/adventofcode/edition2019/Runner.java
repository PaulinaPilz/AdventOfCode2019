package pl.pilz.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.pilz.adventofcode.edition2019.task1.Task1;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@Log4j2
public class Runner {

    private Task1 task1;

    public Runner(Task1 task1) {
        this.task1 = task1;
    }

    @PostConstruct
    public void init() {
        try {
            URL url = ClassLoader.getSystemResource("task1.txt");
            Path resourceTask1 = Paths.get(url.toURI());

            List<String> massesList =  Files.readAllLines(resourceTask1, StandardCharsets.UTF_8);

            log.info(task1.sumMasses(massesList));
        } catch (IOException | URISyntaxException e) {
            log.error("Wrong file modules");
        }

    }
}
