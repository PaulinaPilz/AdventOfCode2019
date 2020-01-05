package pl.pilz.adventofcode.edition2019.task3;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Task3 {


    public int calculateNearestDistance(List<String> input1, List<String> input2) {
        int nearestDistance = 0;
        List<List> commonPointesList = new ArrayList<>();
        List<List<Integer>> coordinates1 = determineCoordinates(input1);
        List<List<Integer>> coordinates2 = determineCoordinates(input2);

        Map<String, List<List<List<Integer>>>> mapRoadCoordinates1 = groupRoadByDirection(coordinates1);
        Map<String, List<List<List<Integer>>>> mapRoadCoordinates2 = groupRoadByDirection(coordinates2);

        List<List<List<Integer>>> verticalCoordinatesList1 = mapRoadCoordinates1.get("vertical");
        List<List<List<Integer>>> horizontalCoordinatesList1 = mapRoadCoordinates1.get("horizontal");
        List<List<List<Integer>>> verticalCoordinatesList2 = mapRoadCoordinates2.get("vertical");
        List<List<List<Integer>>> horizontalCoordinatesList2 = mapRoadCoordinates2.get("horizontal");

        for (List<List<Integer>> horizontalCoordinates1 : horizontalCoordinatesList1) {
            for (List<List<Integer>> verticalCoordinates2 : verticalCoordinatesList2) {
                if ((verticalCoordinates2.get(0).get(0) > horizontalCoordinates1.get(0).get(0) && verticalCoordinates2.get(1).get(0) < horizontalCoordinates1.get(0).get(0))
                        || (verticalCoordinates2.get(0).get(0) > horizontalCoordinates1.get(0).get(0) && verticalCoordinates2.get(1).get(0) < horizontalCoordinates1.get(0).get(0))) {
                    if ((horizontalCoordinates1.get(0).get(1) > verticalCoordinates2.get(0).get(1) && horizontalCoordinates1.get(1).get(1) < verticalCoordinates2.get(0).get(1))
                            || horizontalCoordinates1.get(0).get(1) < verticalCoordinates2.get(0).get(1) && horizontalCoordinates1.get(1).get(1) > verticalCoordinates2.get(0).get(1)) {
                        commonPointesList.add(Arrays.asList(verticalCoordinates2.get(0).get(1), horizontalCoordinates1.get(0).get(0)));
                    }
                }
            }
        }
        for (List<List<Integer>> horizontalCoordinates2 : horizontalCoordinatesList2) {
            for (List<List<Integer>> verticalCoordinates1 : verticalCoordinatesList1) {
                if ((verticalCoordinates1.get(0).get(0) > horizontalCoordinates2.get(0).get(0) && verticalCoordinates1.get(1).get(0) < horizontalCoordinates2.get(0).get(0))
                        || (verticalCoordinates1.get(0).get(0) > horizontalCoordinates2.get(0).get(0) && verticalCoordinates1.get(1).get(0) < horizontalCoordinates2.get(0).get(0))) {
                    if ((horizontalCoordinates2.get(0).get(1) > verticalCoordinates1.get(0).get(1) && horizontalCoordinates2.get(1).get(1) < verticalCoordinates1.get(0).get(1))
                            || horizontalCoordinates2.get(0).get(1) < verticalCoordinates1.get(0).get(1) && horizontalCoordinates2.get(1).get(1) > verticalCoordinates1.get(0).get(1)) {
                        commonPointesList.add(Arrays.asList(verticalCoordinates1.get(0).get(1), horizontalCoordinates2.get(0).get(0)));
                    }
                }
            }
        }
        List<Integer> sumPointers = new ArrayList<>();
        for (List<Integer> pointers : commonPointesList) {
            int sum = pointers.get(0) + pointers.get(1);
            sumPointers.add(sum);
        }

        return Collections.min(sumPointers);
    }

    private Map<String, List<List<List<Integer>>>> groupRoadByDirection(List<List<Integer>> coordinates) {
        Map<String, List<List<List<Integer>>>> coordinatesByDirection = new HashMap<>();
        List<List<Integer>> verticalList = new ArrayList<>();
        List<List<List<Integer>>> verticalRoad = new ArrayList<>();
        List<List<Integer>>  horizontalList = new ArrayList<>();
        List<List<List<Integer>>> horizontalRoad = new ArrayList<>();

        for (int i = 0; i < coordinates.size(); i++) {
            if (i == 0) {
                continue;
            }
            if (coordinates.get(i).get(0) == coordinates.get(i - 1).get(0)) {
                horizontalList.add(Arrays.asList(coordinates.get(i - 1).get(0), coordinates.get(i -1).get(1)));
                horizontalList.add(Arrays.asList(coordinates.get(i).get(0), coordinates.get(i).get(1)));
                horizontalRoad.add(horizontalList);
                horizontalList = new ArrayList<>();
            } else {
                verticalList.add(Arrays.asList(coordinates.get(i - 1).get(0), coordinates.get(i -1).get(1)));
                verticalList.add(Arrays.asList(coordinates.get(i).get(0), coordinates.get(i).get(1)));
                verticalRoad.add(verticalList);
                verticalList = new ArrayList<>();
            }
        }

        coordinatesByDirection.put("vertical", verticalRoad);
        coordinatesByDirection.put("horizontal", horizontalRoad);

        return coordinatesByDirection;
    }

    private List<List<Integer>> determineCoordinates(List<String> input) {
        List<List<Integer>> coordinates = new ArrayList<>();
        int startPoint = 0;
        int vertical = startPoint;
        int horizontal = startPoint;
        int value = 0;
        List<Integer> coordinate = new ArrayList<>();

        coordinates.add(Arrays.asList(startPoint, startPoint));

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).startsWith("D") || input.get(i).startsWith("L")) {
                value = Integer.parseInt(input.get(i).substring(1)) * (-1);
            } else {
                value = Integer.parseInt(input.get(i).substring(1));
            }
            if (input.get(i).startsWith("R") || input.get(i).startsWith("L")) {
                horizontal = horizontal + value;
            }
            if (input.get(i).startsWith("U") || input.get(i).startsWith("D")) {
                vertical = vertical + value;
            }
            coordinate.add(0, vertical);
            coordinate.add(1, horizontal);
            coordinates.add(coordinate);
            coordinate = new ArrayList<>();
        }
        return coordinates;
    }
}
