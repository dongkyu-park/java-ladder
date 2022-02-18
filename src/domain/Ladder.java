package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Ladder {
    private List<LadderRow> ladderRows;
    private List<NameTag> nametags;
    private List<ResultTag> resultTags;
    private int numberOfPlayer;

    public Ladder(String[] names, String[] results, int ladderDepth) {
        this.ladderRows = new ArrayList<>();
        this.nametags = new ArrayList<>();
        this.resultTags = new ArrayList<>();
        this.numberOfPlayer = names.length;

        Arrays.stream(names)
                .forEach(name -> nametags.add(new NameTag(name)));

        IntStream.range(0, ladderDepth)
                .forEach(row -> ladderRows.add(new LadderRow(numberOfPlayer)));
    }

    public ArrayList<NameTag> getNameTags() {
        return (ArrayList<NameTag>) nametags;
    }

    public ArrayList<LadderRow> getLadderRows() {
        return (ArrayList<LadderRow>) ladderRows;
    }
}
