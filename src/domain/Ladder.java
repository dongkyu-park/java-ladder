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
        return "";
    }
}
