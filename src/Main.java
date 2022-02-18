import domain.Ladder;
import domain.LadderRow;
import view.InputManager;
import view.OutputManager;

public class Main {
    private static InputManager in = new InputManager();
    private static OutputManager out = new OutputManager();

    public static void main(String[] args) {
        Ladder ladder = new Ladder(enterNames(), enterResult(), enterLadderDepth());
        out.println("");
        out.printLadderModel(ladder);
        out.println("");

        while (true) {
            enterNameWantShowResult(ladder);
        }
    }

    public static String[] enterNames() {
        out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
        String[] names = in.separateCommasFromString(in.inputString());
        try {
            in.validateNameLength(names);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
            enterNames();
        }
        return names;
    }

    public static int enterLadderDepth() {
        out.println("최대 사다리 높이는 몇 개인가요?");
        return in.inputNumber();
    }

    public static String[] enterResult() {
        out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
        String[] results = in.separateCommasFromString(in.inputString());
        try {
            in.validateResultLength(results);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
            enterResult();
        }
        return results;
    }

    public static void enterNameWantShowResult(Ladder ladder) {
        out.println("결과를 보고 싶은 사람은?");
        String name = in.inputString();

        if (name.equals("all")) {
            showAll(ladder);
            return;
        }
        out.printPlayerResult(ladder, validateGameEnd(name));
    }

    public static void showAll(Ladder ladder) {
        ladder.getNameTags().stream()
                .forEach(nameTag -> out.printPlayerResult(ladder, validateGameEnd(nameTag.getName())));
    }

    private static String validateGameEnd(String name) {
        if (name.equals("춘식이")) {
            out.println("\r\n게임을 종료합니다.");
            System.exit(0);
        }
        return name;
    }
}
