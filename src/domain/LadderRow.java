package domain;

import java.util.Random;
import java.util.stream.IntStream;

public class LadderRow extends ShapeInitializer{
    public static final Random RANDOM = new Random();

    private int section;
    private int numberOfRowStartWithEmpty;
    private String row;
    private boolean previousSectionIsBridge;

    public LadderRow(int numberOfLine) {
        this.section = numberOfLine * 2 - 1;
        this.numberOfRowStartWithEmpty = calculateNumberOfRowStartWithEmpty();
        this.row = init();
        this.previousSectionIsBridge = false;
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    private String init() {
        StringBuilder sb = new StringBuilder();

        sb.append(initEmpty(numberOfRowStartWithEmpty));
        IntStream.range(0, section).forEach(sectionIndex -> sb.append(determineSectionShape(sectionIndex)));
        return sb.toString();
    }

    private int calculateNumberOfRowStartWithEmpty() {
        if (isEven(NameTag.NAME_TAG_SIZE)) {
            return NameTag.NAME_TAG_SIZE / 2 - 1;
        }
        return NameTag.NAME_TAG_SIZE / 2;
    }

    private String determineSectionShape(int sectionIndex) {
        if (isEven(sectionIndex)) {
            return "|";
        }
        return initRandomShape();
    }

    private String initRandomShape() {
        if (previousSectionIsBridge) {
            switchPreviousSectionIsBridge();
            return initEmpty(NameTag.NAME_TAG_SIZE - 1);
        }

        // 랜덤 모양(empty or bridge) 생성 로직
        if (RANDOM.nextBoolean()) {
            switchPreviousSectionIsBridge();
            return initBridge(NameTag.NAME_TAG_SIZE - 1);
        }
        return initEmpty(NameTag.NAME_TAG_SIZE - 1);
    }

    private void switchPreviousSectionIsBridge() {
        this.previousSectionIsBridge = !previousSectionIsBridge;
    }

    public String getRow() {
        return row;
    }

    public int getNumberOfRowStartWithEmpty() {
        return numberOfRowStartWithEmpty;
    }

    public int getIndexOfNextDestination(int lineIndex) {
        if (row.charAt(lineIndex - 1) == '-') {
            return lineIndex - NameTag.NAME_TAG_SIZE;
        }
        if (row.length() - 1 > lineIndex && row.charAt(lineIndex + 1) == '-') {
            return lineIndex + NameTag.NAME_TAG_SIZE;
        }
        return lineIndex;
    }
}
