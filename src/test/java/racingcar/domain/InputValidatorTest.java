package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.error.ErrorMessage;
import racingcar.domain.view.InputValidator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"123", "0", "999", "1"})
    void 라운드_입력값으로_숫자가_들어가면_예외가_발생하지_않는다(String validInput) {

        // when & then
        Assertions.assertThatCode(() -> InputValidator.validate(validInput)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12a", "1.5", "3.14", "123abc", "!@#", "1 2"})
    void 라운드_입력값으로_숫자가_아닌_문자열이_들어가면_예외가_발생한다(String invalidInput) {

        // when & then
        assertThatThrownBy(() -> InputValidator.validate(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ROUND_INPUT_NOT_NUMBER_ERROR.getMessage());
    }

    @ParameterizedTest
    @NullSource
    void 라운드_입력값으로_null이_들어가면_예외가_발생한다(String nullInput) {

        // when & then
        assertThatThrownBy(() -> InputValidator.validate(nullInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ROUND_INPUT_NULL_ERROR.getMessage());
    }
}
