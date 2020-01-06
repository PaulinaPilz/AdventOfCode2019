package pl.pilz.adventofcode.edition2019.task3;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Task3Test {

    @Test
    public void testCalculateNearestDistance() {
        //given
        Task3 tested = new Task3();
        int expectedDistance = 6;
        int expectedDistance1 = 159;
        int expectedDistance2 = 135;
        List<String> input = Arrays.asList("R8","U5","L5","D3");
        List<String> input1 = Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72");
        List<String> input11 = Arrays.asList("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51");
        List<String> input222 = Arrays.asList("U7","R6","D4","L4");
        List<String> input2 = Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83");
        List<String> input22 = Arrays.asList("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7");

        //when
        int distance = tested.calculateNearestDistance(input, input222);
        int distance1 = tested.calculateNearestDistance(input1, input2);
        int distance2 = tested.calculateNearestDistance(input11, input22);

        //then
        assertEquals(expectedDistance, distance);
        assertEquals(expectedDistance1, distance1);
        assertEquals(expectedDistance2, distance2);
    }
}
