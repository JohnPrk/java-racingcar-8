package racingcar.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(String carNameInput) {
        validateInput(carNameInput);
        List<String> carNames = splitAndParse(carNameInput);
        validateSize(carNames);
        validateDuplicates(carNames);
        this.cars = createCarList(carNames);
    }

    public List<Car> getCars() {
        return cars;
    }

    private List<Car> createCarList(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private List<String> splitAndParse(String carNameInput) {
        return Arrays.stream(carNameInput.split(",", -1)).toList();
    }

    private void validateInput(String carNameInput) {
        if (carNameInput == null) {
            throw new IllegalArgumentException("자동차 이름을 입력해주세요");
        }
    }

    private void validateSize(List<String> carNames) {
        if (carNames.isEmpty()) {
            throw new IllegalArgumentException("자동차는 최소 1대 이상이어야 합니다");
        }
        if (carNames.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("자동차는 최소 1대 이상이어야 합니다");
        }
    }

    private void validateDuplicates(List<String> carNames) {
        Set<String> uniqueNames = new HashSet<>();
        for (String name : carNames) {
            if (!uniqueNames.add(name)) {
                throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다: " + name);
            }
        }
    }
}
