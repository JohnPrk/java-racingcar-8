package racingcar.domain;

import org.junit.jupiter.api.Test;
import racingcar.domain.error.ErrorMessage;
import racingcar.domain.testDouble.FakeMoveStrategy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RaceTest {

    @Test
    void 레이스는_총_라운드_수와_Cars를_받아_생성된다() {

        // given
        Cars cars = new Cars("pobi,woni");
        int totalRounds = 5;

        // when
        Race race = new Race(cars, totalRounds);

        // then
        assertThat(race).isNotNull();
    }

    @Test
    void 레이스_생성시_라운드_수가_0이하이면_예외가_발생한다() {

        // given
        Cars cars = new Cars("pobi,woni");

        // when & then
        assertThatThrownBy(() -> new Race(cars, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.ROUND_ZERO_OR_NEGATIVE_ERROR.getMessage());

        assertThatThrownBy(() -> new Race(cars, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.ROUND_ZERO_OR_NEGATIVE_ERROR.getMessage());
    }

    @Test
    void 레이스_시작시_정해진_라운드_수만큼_게임이_진행된다() {

        // given
        FakeMoveStrategy strategy = new FakeMoveStrategy(new boolean[]{
                true, false,
                true, false}
        );
        Cars cars = new Cars("pobi,woni", () -> strategy);
        Race race = new Race(cars, 2);

        // when
        race.start();

        // then
        List<Car> carList = cars.getCars();
        assertThat(carList.get(0).getPosition()).isEqualTo(2);
        assertThat(carList.get(1).getPosition()).isEqualTo(0);
    }

    @Test
    void 레이스_종료시_우승자를_판별한다() {

        // given
        FakeMoveStrategy strategy = new FakeMoveStrategy(new boolean[]{
                true, false,
                false, true,
                false, true}
        );
        Cars cars = new Cars("pobi,woni", () -> strategy);
        Race race = new Race(cars, 3);

        // when
        race.start();

        // then
        List<Car> winners = cars.findWinners();
        assertThat(winners).hasSize(1);
        assertThat(winners)
                .extracting(Car::getName)
                .containsOnly("woni");
    }

    @Test
    void 레이스_종료시_동점자가_있으면_복수의_우승자를_판별한다() {
        FakeMoveStrategy strategy = new FakeMoveStrategy(new boolean[]{
                true, true, false,
                true, false, true,
                false, false, true
        });
        Cars cars = new Cars("pobi,woni,jun", () -> strategy);
        Race race = new Race(cars, 3);

        // when
        race.start();

        // then
        List<Car> winners = cars.findWinners();
        assertThat(winners).hasSize(2);
        assertThat(winners)
                .extracting(Car::getName)
                .containsExactlyInAnyOrder("pobi", "jun");
    }
}
