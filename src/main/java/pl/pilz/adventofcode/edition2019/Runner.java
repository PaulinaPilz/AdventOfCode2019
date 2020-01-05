package pl.pilz.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.pilz.adventofcode.edition2019.task1.Task1;
import pl.pilz.adventofcode.edition2019.task2.Task2;
import pl.pilz.adventofcode.edition2019.task3.Task3;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class Runner {

    private Task1 task1;
    private Task2 task2;
    private Task3 task3;

    public Runner(Task1 task1, Task2 task2, Task3 task3) {
        this.task1 = task1;
        this.task2 = task2;
        this.task3 = task3;
    }

    @PostConstruct
    public void initTask1() {
        try {
            log.info("Resolve Task 1");
            URL url = ClassLoader.getSystemResource("task1.txt");
            Path resourceTask1 = Paths.get(url.toURI());

            List<String> massesList =  Files.readAllLines(resourceTask1, StandardCharsets.UTF_8);

            log.info(task1.sumMasses(massesList));
            log.info(task1.sumFuelForAllModules(massesList));
        } catch (IOException | URISyntaxException e) {
            log.error("Wrong file modules");
        }
    }

    @PostConstruct
    public void initTask2Part1() {
        log.info("Resolve Task 2 part 1");

        List<Integer> program = new ArrayList<>();
        try (Scanner s = new Scanner(new File("src/main/resources/1202_program_alarm.txt")).useDelimiter("\\s*,\\s*")) {
            while (s.hasNext()) {
                program.add(s.nextInt());
            }
            program.set(1, 12);
            program.set(2, 2);
            task2.modifyProgram(program);
            log.info(program.get(0));
        }
        catch (FileNotFoundException e) {
            log.error("File not found");
        }
        catch (InputMismatchException e) {
            log.error("Input mismatch");
        }
    }

    @PostConstruct
    public void initTask2Part2() {
        log.info("Resolve Task 2 part 2");
        List<Integer> initPogram = new ArrayList<>();
        try (Scanner s = new Scanner(new File("src/main/resources/1202_program_alarm.txt")).useDelimiter("\\s*,\\s*")) {
            while (s.hasNext()) {
                initPogram.add(s.nextInt());
            }
            for (int noun = 0; noun <= 99; noun++) {
                for (int verb = 0; verb <= 99; verb++) {
                    List<Integer> program = new ArrayList<>(initPogram);
                    program.set(1, noun);
                    program.set(2, verb);
                    task2.modifyProgram(program);
                    if (19690720 == program.get(0)) {
                        log.info(100 * noun + verb);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            log.error("File not found");
        }
        catch (InputMismatchException e) {
            log.error("Input mismatch");
        }
    }

    @PostConstruct
    public void initTask3Part1() {
        try {
            log.info("Resolve Task 3 part 1");
            URL url = ClassLoader.getSystemResource("task3.txt");
            Path resourceTask1 = Paths.get(url.toURI());

            List<String> massesList =  Files.readAllLines(resourceTask1, StandardCharsets.UTF_8);

            List<String> intput1 = Arrays.asList(massesList.get(0).split(","));
            List<String> intput2 = Arrays.asList(massesList.get(1).split(","));

            log.info(task3.calculateNearestDistance(intput1, intput2));

        } catch (IOException | URISyntaxException e) {
            log.error("Wrong file modules");
        }
    }

}
