package racingcar.domain;

import racingcar.domain.error.ErrorMessage;

public class Car {
    private final String name;
    private int position;
    private final MoveStrategy moveStrategy;


    public Car(String name, MoveStrategy moveStrategy) {
        validateName(name);
        this.name = name;
        this.position = 0;
        this.moveStrategy = moveStrategy;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void tryToMove() {
        if (moveStrategy.canMove()) {
            position++;
        }
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_LENGTH_ERROR.getMessage());
        }
    }
}
