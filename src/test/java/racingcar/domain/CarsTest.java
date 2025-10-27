package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.error.ErrorMessage;
import racingcar.domain.testDouble.FakeMoveStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarsTest {

    @ParameterizedTest
    @ValueSource(strings = {"pobi,woni,jun", "pobi,jun", "pobi"})
    void 사용자의_이름_입력_값을_받아서_일급_컬렉션인_Cars_객체를_생성할_수_있다(String carNameInput) {

        //given
        Cars cars = new Cars(carNameInput);

        // when & then
        String[] splittedCarName = carNameInput.split(",");
        assertThat(cars.getCars()).hasSize(splittedCarName.length);
        assertThat(cars.getCars())
                .extracting(Car::getName)
                .containsExactly(splittedCarName);
    }

    @ParameterizedTest
    @NullSource
    void null_입력시_예외가_발생한다(String nullInput) {

        // when & then
        assertThatThrownBy(() -> new Cars(nullInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INPUT_NULL_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "pobi,,jun", ",pobi,woni", "woni,jun,"})
    void 빈_이름이_포함된_입력시_예외가_발생한다(String inputWithEmptyName) {

        // when & then
        assertThatThrownBy(() -> new Cars(inputWithEmptyName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INPUT_NULL_ERROR.getMessage());
    }

    @Test
    void new_String_빈_문자열_입력시_예외가_발생한다() {

        // when & then
        assertThatThrownBy(() -> new Cars(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INPUT_NULL_ERROR.getMessage());
    }

    @Test
    void 자동차_이름_중복시_예외가_발생한다() {

        // when & then
        assertThatThrownBy(() -> new Cars("pobi,pobi,jun"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.CAR_NAME_DUPLICATE_ERROR.getMessage());
    }

    @Test
    void 자동차_이동시_최대_위치가_실시간으로_업데이트된다() {
        // given
        FakeMoveStrategy strategy = new FakeMoveStrategy(new boolean[]{
                true, false,
                false, true,
                false, true,
                true, true});
        Cars cars = new Cars("pobi,woni", () -> strategy);

        // when & then
        assertThat(cars.getMaxPosition()).isEqualTo(0);

        cars.moveAll();
        assertThat(cars.getMaxPosition()).isEqualTo(1);

        cars.moveAll();
        assertThat(cars.getMaxPosition()).isEqualTo(1);

        cars.moveAll();
        assertThat(cars.getMaxPosition()).isEqualTo(2);

        cars.moveAll();
        assertThat(cars.getMaxPosition()).isEqualTo(3);
    }
}
