package racingcar;

import racingcar.domain.Cars;
import racingcar.domain.Race;
import racingcar.domain.view.InputView;

public class Application {
    public static void main(String[] args) {
        String carNames = InputView.readCarNames();
        int attemptCount = InputView.readAttemptRound();
        Cars cars = new Cars(carNames);
        Race race = new Race(cars, attemptCount);
        race.start();
    }
}
