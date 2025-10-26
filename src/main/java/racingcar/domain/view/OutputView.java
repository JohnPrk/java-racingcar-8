package racingcar.domain.view;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String RESULT_HEADER = "실행 결과";
    private static final String FINAL_WINNER_PREFIX = "최종 우승자 : ";

    public static void printResultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public static void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getPosition()));
        }
        System.out.println();
    }

    public static void printFinalWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
        System.out.println(FINAL_WINNER_PREFIX + winnerNames);
    }
}