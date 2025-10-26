package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.error.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"A", "민욱", "max", "john", "peter", "제갈아무개"})
    void 한_글자에서_다섯_글자_이내의_이름으로_자동차를_생성할_수_있다(String name) {

        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo(name);
        assertThat(car.getPosition()).isEqualTo(0);

    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void 자동차의_이름에_null이나_공백이_들어가면_IllegalArgumentException_예외가_발생한다(String name) {

        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CAR_NAME_LENGTH_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Richard", "박하늘별님구름햇님보다사랑스러우리", "프라이인드로스테쭈젠댄마리소피아수인레나테엘리자벳피아루이제"})
    void 자동차의_이름에_여섯_글자_이상이면_IllegalArgumentException_예외가_발생한다(String name) {

        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.CAR_NAME_LENGTH_ERROR.getMessage());
    }

    @Test
    void 자동차가_전진하면_위치가_1_증가한다() {

        // given
        Car car = new Car("pobi");

        // when
        car.move();

        // then
        Assertions.assertThat(car.getPosition()).isEqualTo(1);
    }
}
