package pl.pilz.adventofcode.edition2019.task2;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Task2 {

    public List<Integer> modifyProgram(List<Integer> program) {
        int value;

        for (int index = 0; index <= program.size(); index += 4) {
            switch (program.get(index)) {
                case 1:
                    value = program.get(program.get(index + 1)) + program.get(program.get(index + 2));
                    break;
                case 2:
                    value = program.get(program.get(index + 1)) * program.get(program.get(index + 2));
                    break;
                case 99:
                    return program;
                default:
                    throw new IllegalStateException("Unexpected value: " + program.get(index));
            }
            program.set(program.get(index + 3), value);
        }
        return program;
    }
}
