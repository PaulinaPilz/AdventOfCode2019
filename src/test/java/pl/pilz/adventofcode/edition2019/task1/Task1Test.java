package pl.pilz.adventofcode.edition2019.task1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Task1Test {

    @Test
    public void testSumMesses() {
        // given
        Task1 tested = new Task1();
        List<String> masses = List.of("12", "14", "1969", "100756");

        //when
        long sumOfMass = tested.sumMasses(masses);

        assertEquals(34241, sumOfMass);
    }
}
