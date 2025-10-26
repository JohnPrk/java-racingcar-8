package racingcar.domain;

import racingcar.domain.error.ErrorMessage;

import java.util.List;
import java.util.stream.Collectors;

public class Race {
    private final Cars cars;
    private final int totalRounds;
    private int currentRound;

    public Race(Cars cars, int totalRounds) {
        validateRounds(totalRounds);
        this.cars = cars;
        this.totalRounds = totalRounds;
        this.currentRound = 0;
    }

    private void validateRounds(int rounds) {
        if (rounds <= 0) {
            throw new IllegalArgumentException(ErrorMessage.ROUND_ZERO_OR_NEGATIVE_ERROR.getMessage());
        }
    }

    public void start() {
        System.out.println("실행 결과");
        while (hasNextRound()) {
            playRound();
            printRoundResult();
        }
        printFinalResult();
    }

    private boolean hasNextRound() {
        return currentRound < totalRounds;
    }

    private void playRound() {
        cars.moveAll();
        currentRound++;
    }

    private void printRoundResult() {
        for (Car car : cars.getCars()) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getPosition()));
        }
        System.out.println();
    }

    private void printFinalResult() {
        List<Car> winners = cars.findWinners();
        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
        System.out.println("최종 우승자 : " + winnerNames);
    }
}
