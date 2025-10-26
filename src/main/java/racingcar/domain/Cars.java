package racingcar.domain;

import racingcar.domain.error.ErrorMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(String carNameInput, Supplier<MoveStrategy> moveStrategySupplier) {
        validateInput(carNameInput);
        List<String> carNames = splitAndParse(carNameInput);
        validateSize(carNames);
        validateDuplicates(carNames);
        this.cars = createCarList(carNames, moveStrategySupplier);
    }

    public Cars(String carNameInput) {
        this(carNameInput, RandomMoveStrategy::new);
    }

    public List<Car> getCars() {
        return cars;
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

    private void validateSize(List<String> carNames) {
        if (carNames.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.CARS_EMPTY_ERROR.getMessage());
        }
        if (carNames.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(ErrorMessage.CARS_EMPTY_ERROR.getMessage());
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
