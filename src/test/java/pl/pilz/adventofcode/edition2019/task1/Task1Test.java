package pl.pilz.adventofcode.edition2019.task1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Task1Test {

    Task1 tested = new Task1();

    @Test
    public void testSumMesses() {
        // given
        List<String> masses = List.of("12", "14", "1969", "100756");
        long expectedSumOfMass = 34241;

        //when
        long sumOfMass = tested.sumMasses(masses);

        assertEquals(expectedSumOfMass, sumOfMass);
    }

    @Test
    public void testAddFuelForModule() {
        //given
        long modul = 100756L;
        long expectedSumFuel = 50346;

        //when
        long sumFuel = tested.addFuelForModule(modul);

        //then
        assertEquals(expectedSumFuel, sumFuel);
    }
}
