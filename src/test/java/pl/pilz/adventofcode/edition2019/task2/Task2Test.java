package pl.pilz.adventofcode.edition2019.task2;

import org.junit.Test;
import pl.pilz.adventofcode.edition2019.task2.Task2;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Task2Test {

    Task2 tested = new Task2();

    @Test
    public void testModifyProgram_checkAddition() {

        //given
        List<Integer> programWithAddition = Arrays.asList(1,0,0,0,99);
        List<Integer> expectedProgramWithAddition = Arrays.asList(2,0,0,0,99);

        //when
        List<Integer> modifiedProgramWithAddition = tested.modifyProgram(programWithAddition);

        //then
        assertEquals(expectedProgramWithAddition, modifiedProgramWithAddition);
    }

    @Test
    public void testModifyProgram_checkMultiply() {
        //given
        List<Integer> programWithMultiply = Arrays.asList(2,3,0,3,99);
        List<Integer> expectedProgramWithMuliply = Arrays.asList(2,3,0,6,99);

        //when
        List<Integer> modifiedProgramWithMuliply = tested.modifyProgram(programWithMultiply);

        //then
        assertEquals(expectedProgramWithMuliply, modifiedProgramWithMuliply);
    }

    @Test
    public void testModifyProgram_checkEnding() {
        //given
        List<Integer> programWithEnding1 = Arrays.asList(2,4,4,5,99,0);
        List<Integer> expectedProgramWithEnding1  = Arrays.asList(2,4,4,5,99,9801);

        List<Integer> programWithEnding2  = Arrays.asList(1,1,1,4,99,5,6,0,99);
        List<Integer> expectedProgramWithEnding2  = Arrays.asList(30,1,1,4,2,5,6,0,99);

        //when
        List<Integer> modifiedPrograWithEnding1 = tested.modifyProgram(programWithEnding1);
        List<Integer> modifiedPrograWithEnding2 = tested.modifyProgram(programWithEnding2);

        //then
        assertEquals(expectedProgramWithEnding1, modifiedPrograWithEnding1);
        assertEquals(expectedProgramWithEnding2, modifiedPrograWithEnding2);
    }
}
