package racingcar.domain.error;

public enum ErrorMessage {

    CAR_NAME_LENGTH_ERROR("자동차 이름은 1~5자 사이여야 합니다"),
    INPUT_NULL_ERROR("자동차 이름을 입력해주세요"),
    CARS_EMPTY_ERROR("자동차는 최소 1대 이상이어야 합니다"),
    CAR_NAME_DUPLICATE_ERROR("자동차 이름은 중복될 수 없습니다: #{name}"),
    ROUND_ZERO_OR_NEGATIVE_ERROR("라운드 수는 1 이상이어야 합니다");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
