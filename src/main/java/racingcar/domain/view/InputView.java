package racingcar.domain.view;

import camp.nextstep.edu.missionutils.Console;

import static racingcar.domain.view.InputValidator.validate;

public class InputView {
    private static final String INPUT_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_ATTEMPT_ROUND_MESSAGE = "시도할 회수는 몇회인가요?";


    public static String readCarNames() {
        System.out.println(INPUT_CAR_NAMES_MESSAGE);
        return Console.readLine();
    }

    public static int readAttemptRound() {
        System.out.println(INPUT_ATTEMPT_ROUND_MESSAGE);
        String input = Console.readLine();
        validate(input);
        return Integer.parseInt(input);
    }


}
