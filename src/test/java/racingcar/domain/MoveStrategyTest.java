package racingcar.domain;

import org.junit.jupiter.api.Test;
import racingcar.domain.testDouble.FakeMoveStrategy;

import static org.assertj.core.api.Assertions.assertThat;

class MoveStrategyTest {

    @Test
    void RandomMoveStrategy의_canMove_메서드는_랜덤_값에_의해_움직이거나_움직이지_않는_결과인_boolean값을_반환한다() {

        // given
        MoveStrategy strategy = new RandomMoveStrategy();

        //when & then
        for (int i = 0; i < 100; i++) {
            boolean move = strategy.canMove();
            assertThat(move).isInstanceOf(Boolean.class);
        }
    }

    @Test
    void FakeMoveStrategy는_테스트_더블의_Fake_방식을_사용한_MoveStrategy_구현체인데_움직이는_조건을_미리_정해두고_이를_테스트할_수_있다() {

        // given
        MoveStrategy strategy = new FakeMoveStrategy(new boolean[]{true, false, true, true, false});

        // when & then
        assertThat(strategy.canMove()).isTrue();
        assertThat(strategy.canMove()).isFalse();
        assertThat(strategy.canMove()).isTrue();
        assertThat(strategy.canMove()).isTrue();
        assertThat(strategy.canMove()).isFalse();
    }


    @Test
    void 여러_MoveStrategy_구현체가_정상적으로_동작한다() {

        // given
        MoveStrategy randomStrategy = new RandomMoveStrategy();
        MoveStrategy fixedStrategy = new FakeMoveStrategy(new boolean[]{true, false});

        // when & then
        assertThat(randomStrategy.canMove()).isInstanceOf(Boolean.class);
        assertThat(fixedStrategy.canMove()).isTrue();
        assertThat(fixedStrategy.canMove()).isFalse();
    }
}
