package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

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
                .hasMessageContaining("자동차 이름을 입력해주세요");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "pobi,,jun", ",pobi,woni", "woni,jun,"})
    void 빈_이름이_포함된_입력시_예외가_발생한다(String inputWithEmptyName) {

        // when & then
        assertThatThrownBy(() -> new Cars(inputWithEmptyName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차는 최소 1대 이상이어야 합니다");
    }

    @Test
    void new_String_빈_문자열_입력시_예외가_발생한다() {

        // when & then
        assertThatThrownBy(() -> new Cars(new String()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차는 최소 1대 이상이어야 합니다");
    }

    @Test
    void 자동차_이름_중복시_예외가_발생한다() {

        // when & then
        assertThatThrownBy(() -> new Cars("pobi,pobi,jun"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 중복될 수 없습니다");
    }
}
