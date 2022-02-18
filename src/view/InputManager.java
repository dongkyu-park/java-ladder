package view;

import domain.NameTag;

import java.util.Arrays;
import java.util.Scanner;

public class InputManager {
    public static final int LEFT_PADDING_SIZE = 3;
    public static final int RIGHT_PADDING_SIZE = 3;
    public static final int LIMIT_NAME_LENGTH = 5;
    public static final int LIMIT_RESULT_LENGTH = NameTag.NAME_TAG_SIZE - 2;

    Scanner sc;

    public InputManager() {
        this.sc = new Scanner(System.in);
    }

    public int inputNumber() {
        return sc.nextInt();
    }

    public String inputString() {
        return sc.next();
    }

    public String[] separateCommasFromString(String words) {
        return words.split(",");
    }

    public void validateNameLength(String[] names) {
        boolean tooLongName = Arrays.stream(names)
                .anyMatch(name -> name.length() > LIMIT_NAME_LENGTH);
        if (tooLongName) {
            throw new IllegalArgumentException("각 플레이어의 이름은 " + LIMIT_NAME_LENGTH + "글자 이하로 설정해야 합니다.");
        }
        return;
    }

    public void validateResultLength(String[] results) {
        boolean tooLongResult = Arrays.stream(results)
                .anyMatch(result -> result.length() > LIMIT_RESULT_LENGTH);
        if (tooLongResult) {
            throw new IllegalArgumentException("각 결과값은 " + LIMIT_RESULT_LENGTH + "글자 이하로 설정해야 합니다.");
        }
        return;
    }
}
