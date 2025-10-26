package racingcar.domain;

import racingcar.domain.error.ErrorMessage;

import static racingcar.domain.view.OutputView.*;

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
        printResultHeader();
        while (hasNextRound()) {
            playRound();
            printRoundResult(cars.getCars());
        }
        printFinalWinners(cars.findWinners());
    }

    private boolean hasNextRound() {
        return currentRound < totalRounds;
    }

    private void playRound() {
        cars.moveAll();
        currentRound++;
    }
}
