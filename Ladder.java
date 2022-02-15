import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Ladder {
    private String[][] ladder;
    private Random random;

    public Ladder(int numberOfPeople, int ladderDepth) {
        this.ladder = new String[ladderDepth][1];
        this.random = new Random();

        IntStream.range(0, ladderDepth)
                .forEach(row -> ladder[row][0] = initLadderRow(numberOfPeople * 2 - 1));
    }

    private String initLadderRow(int ladderLine) {
        StringBuilder sb = new StringBuilder();

        // 열이 짝수라면 "|", 홀수라면 " " or "-" 입력
        IntStream.range(0, ladderLine).forEach(line -> determineLineShape(line, sb));
        return sb.toString();
    }

    private StringBuilder determineLineShape(int line, StringBuilder sb) {
        if (validateEven(line)) {
            return sb.append("|");
        }
        return sb.append(initRandomLine());
    }

    private boolean validateEven(int number) {
        return number % 2 == 0;
    }

    private String initRandomLine() {
        if (random.nextBoolean()) {
            return "-";
        }
        return " ";
    }

    public String[][] getLadder() {
        return ladder;
    }
}