package pl.pilz.adventofcode.edition2019.task3;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Task3 {

    private static final Point STARTING_POINT = new Point(0, 0);
    private static final String VERTICAL = "vertical";
    private static final String HORIZONTAL = "horizontal";
    private List<Point> commonPoints;

    public int calculateNearestDistance(List<String> wire1, List<String> wire2) {
        commonPoints = new ArrayList<>();
        List<Section> verticalSectionsWire1 = groupWireByDirection(determineCoordinates(wire1)).get(VERTICAL);
        List<Section> horizontalSectionsWire1 = groupWireByDirection(determineCoordinates(wire1)).get(HORIZONTAL);
        List<Section> verticalSectionsWire2 = groupWireByDirection(determineCoordinates(wire2)).get(VERTICAL);
        List<Section> horizontalSectionsWire2 = groupWireByDirection(determineCoordinates(wire2)).get(HORIZONTAL);

        findCommonPoints(horizontalSectionsWire1, verticalSectionsWire2);
        findCommonPoints(horizontalSectionsWire2, verticalSectionsWire1);
        return commonPoints.stream().mapToInt(point -> point.getVertical() + point.getHorizontal()).min().orElseThrow();
    }

    private Map<String, List<Section>> groupWireByDirection(List<Point> points) {
        Map<String, List<Section>> coordinatesByDirection = new HashMap<>();
        List<Section> verticalWireSection = new ArrayList<>();
        List<Section> horizontalWireSection = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            if (i == 0) {
                continue;
            }
            if (points.get(i).getVertical() == points.get(i - 1).getVertical()) {
                horizontalWireSection.add(new Section(new Point(points.get(i - 1).getVertical(), points.get(i - 1).getHorizontal()), new Point(points.get(i).getVertical(), points.get(i).getHorizontal())));
            } else {
                verticalWireSection.add(new Section(new Point(points.get(i - 1).getVertical(), points.get(i - 1).getHorizontal()), new Point(points.get(i).getVertical(), points.get(i).getHorizontal())));
            }
        }

        coordinatesByDirection.put(VERTICAL, verticalWireSection);
        coordinatesByDirection.put(HORIZONTAL, horizontalWireSection);

        return coordinatesByDirection;
    }

    private List<Point> determineCoordinates(List<String> input) {
        List<Point> coordinates = new ArrayList<>();
        int vertical = 0;
        int horizontal = 0;
        int value;

        coordinates.add(STARTING_POINT);

        for (String direction : input) {
            value = Integer.parseInt(direction.substring(1));
            if (direction.startsWith("D")) {
                vertical -= value;
            }
            if (direction.startsWith("L")) {
                horizontal -= value;
            }
            if (direction.startsWith("R")) {
                horizontal += value;
            }
            if (direction.startsWith("U")) {
                vertical += value;
            }
            coordinates.add(new Point(vertical, horizontal));
        }
        return coordinates;
    }

    private void findCommonPoints(List<Section> horizontalSectionsWire1, List<Section> verticalSectionsWire2) {
        for (Section horizontal : horizontalSectionsWire1) {
            for (Section vertical : verticalSectionsWire2) {
                if (vertical.getEnd1().getVertical() > horizontal.getEnd1().getVertical() && vertical.getEnd2().getVertical() < horizontal.getEnd1().getVertical() &&
                        (horizontal.getEnd1().getHorizontal() > vertical.getEnd1().getHorizontal() && horizontal.getEnd2().getHorizontal() < vertical.getEnd1().getHorizontal()
                                || horizontal.getEnd1().getHorizontal() < vertical.getEnd1().getHorizontal() && horizontal.getEnd2().getHorizontal() > vertical.getEnd1().getHorizontal())) {
                    commonPoints.add(new Point(vertical.getEnd1().getHorizontal(), horizontal.getEnd1().getVertical()));
                }
            }
        }
    }

    @Data
    private static class Section {

        private Point end1;
        private Point end2;

        public Section(Point end1, Point end2) {
            this.end1 = end1;
            this.end2 = end2;
        }
    }

    @Data
    private static class Point {

        private int vertical;
        private int horizontal;

        public Point(int vertical, int horizontal) {
            this.vertical = vertical;
            this.horizontal = horizontal;
        }
    }
}
