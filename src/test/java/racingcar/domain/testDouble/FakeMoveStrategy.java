package racingcar.domain.testDouble;

import racingcar.domain.MoveStrategy;

public class FakeMoveStrategy implements MoveStrategy {
    private final boolean[] moveConditions;
    private int index;

    public FakeMoveStrategy(boolean[] moveConditions) {
        this.moveConditions = moveConditions;
        this.index = 0;
    }

    @Override
    public boolean canMove() {
        return moveConditions[index++];
    }
}
