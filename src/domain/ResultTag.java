package domain;

public class ResultTag extends ShapeInitializer{
    public static final int RESULT_TAG_SIZE = NameTag.NAME_TAG_SIZE;

    private String resultTag;

    public ResultTag(String result) {
        this.resultTag = wrapResult(result);
    }

    private String wrapResult(String result) {
        return init(result, RESULT_TAG_SIZE - result.length()); // 지정 된 RESULT_TAG_SIZE에서 입력받은 result의 길이만큼 빼서 추가해야 할 padding의 갯수를 구한다.
    }

    private String init(String result, int numberOfPadding) {
        StringBuilder sb = new StringBuilder();
        if (isEven(numberOfPadding)) {
            sb.append(initEmpty(numberOfPadding / 2) + result + initEmpty(numberOfPadding / 2));
            return sb.toString();
        }
        sb.append(initEmpty(numberOfPadding / 2) + result + initEmpty(numberOfPadding / 2 + 1));
        return sb.toString();
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    public String getResultTag() {
        return resultTag;
    }
}
