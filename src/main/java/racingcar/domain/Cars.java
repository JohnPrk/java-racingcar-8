package racingcar.domain;

import racingcar.domain.error.ErrorMessage;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;
    private int maxPosition;

    public Cars(String carNameInput, Supplier<MoveStrategy> moveStrategySupplier) {
        validateInput(carNameInput);
        List<String> carNames = splitAndParse(carNameInput);
        validateAnybodyEmpty(carNames);
        validateDuplicates(carNames);
        this.cars = createCarList(carNames, moveStrategySupplier);
        this.maxPosition = 0;
    }

    public Cars(String carNameInput) {
        this(carNameInput, RandomMoveStrategy::new);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public int getMaxPosition() {
        return maxPosition;
    }

    public void moveAll() {
        for (Car car : cars) {
            car.tryToMove();
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }
    }

    public List<Car> findWinners() {
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private List<Car> createCarList(List<String> carNames, Supplier<MoveStrategy> moveStrategySupplier) {
        return carNames.stream()
                .map(name -> new Car(name, moveStrategySupplier.get()))
                .collect(Collectors.toList());
    }

    private List<String> splitAndParse(String carNameInput) {
        return Arrays.stream(carNameInput.split(",", -1)).toList();
    }

    private void validateInput(String carNameInput) {
        if (carNameInput == null) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NULL_ERROR.getMessage());
        }
    }

    private void validateAnybodyEmpty(List<String> carNames) {
        if (carNames.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NULL_ERROR.getMessage());
        }
    }

    private void validateDuplicates(List<String> carNames) {
        Set<String> uniqueNames = new HashSet<>();
        for (String name : carNames) {
            if (!uniqueNames.add(name)) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_DUPLICATE_ERROR.getMessage());
            }
        }
    }
}
