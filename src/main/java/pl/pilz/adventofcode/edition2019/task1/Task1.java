package pl.pilz.adventofcode.edition2019.task1;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.floor;

@Component
public class Task1 {

    private long fuelCounterUpper(long mass){
        return (long) floor(mass/3.0) - 2;
    }

    public long sumMasses(List<String> masses){
        long sumOfMasses = 0L;
        for (String mass: masses) {
            sumOfMasses = sumOfMasses + fuelCounterUpper(Long.parseLong(mass));
        }
        return sumOfMasses;
    }
}
