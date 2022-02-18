package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Ladder {
    private List<LadderRow> ladderRows;
    private List<NameTag> nameTags;
    private List<ResultTag> resultTags;
    private int numberOfPlayer;

    public Ladder(String[] names, String[] results, int ladderDepth) {
        this.ladderRows = new ArrayList<>();
        this.nameTags = new ArrayList<>();
        this.resultTags = new ArrayList<>();
        this.numberOfPlayer = names.length;

        Arrays.stream(names)
                .forEach(name -> nameTags.add(new NameTag(name)));

        Arrays.stream(results)
                .forEach(result -> resultTags.add(new ResultTag(result)));

        IntStream.range(0, ladderDepth)
                .forEach(row -> ladderRows.add(new LadderRow(numberOfPlayer)));
    }

    public ArrayList<NameTag> getNameTags() {
        return (ArrayList<NameTag>) nameTags;
    }

    public ArrayList<ResultTag> getResultTags() {
        return (ArrayList<ResultTag>) resultTags;
    }

    public ArrayList<LadderRow> getLadderRows() {
        return (ArrayList<LadderRow>) ladderRows;
    }

    public String getPlayerResult(String name) {
        int nameTagIndex = getNameTagIndex(name);
        if (nameTagIndex == -1) {
            return "존재하지 않는 사용자 입니다.";
        }

        int numberOfRowStartWithEmpty = ladderRows.get(0).getNumberOfRowStartWithEmpty(); //
        int lastRowLineIndex = calculateLastRowLineIndex(nameTagIndex, numberOfRowStartWithEmpty);
        int resultTagIndex = calculateResultTagIndex(lastRowLineIndex, numberOfRowStartWithEmpty);

        return resultTags.get(resultTagIndex).getResultTag();
    }

    private int calculateLastRowLineIndex(int nameTagIndex, int numberOfRowStartWithEmpty) {
        int lineIndex = numberOfRowStartWithEmpty + NameTag.NAME_TAG_SIZE * nameTagIndex; // 전달받은 nameTag가 다음에 이동할 row에서 line("|")이 위치한 index값
        for (int i = 0; i < ladderRows.size(); i++) {
            lineIndex = ladderRows.get(i).getIndexOfNextDestination(lineIndex);
        }
        return lineIndex;
    }

    private int calculateResultTagIndex(int lastRowLineIndex, int numberOfRowStartWithEmpty) {
        return (lastRowLineIndex - numberOfRowStartWithEmpty) / NameTag.NAME_TAG_SIZE;
    }

    private int getNameTagIndex(String name) {
        for (int i = 0; i < nameTags.size(); i++) {
            if (nameTags.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
