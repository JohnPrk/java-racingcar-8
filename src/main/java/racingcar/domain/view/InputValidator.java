package racingcar.domain.view;

import racingcar.domain.error.ErrorMessage;

public class InputValidator {
    private static final String NUMERIC_PATTERN = "^[0-9]+$";

    public static void validate(String input) {
        validateNull(input);
        validateDigit(input);
    }

    private static void validateDigit(String input) {

        if (!input.matches(NUMERIC_PATTERN)) {
            throw new IllegalArgumentException(ErrorMessage.ROUND_INPUT_NOT_NUMBER_ERROR.getMessage());
        }
    }

    private static void validateNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.ROUND_INPUT_NULL_ERROR.getMessage());
        }
    }
}
