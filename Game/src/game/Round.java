package game;

import java.util.ArrayList;
import java.util.Collections;
abstract class Round {

    private ArrayList<Turn> record;

    // Each of these functions should be expected to be called exactly once
    // per player turn
    abstract private bool isFinished();
    abstract private Player nextPlayer();
    abstract private int numViewable();

    public Round() {
        record = new ArrayList();
    }

    private List<Turn> getRecord() {
        int viewable = numViewable();
        if (record.size() > viewable) {
            return Collections(unmodifiableList(record.subList(
                        record.size() - viewable
                        , record.size()
            ));
        } else {
            return Collections.unmodifiableList(record.sublist(0, record.size()));
        }
    }

    public List<Turn> playRound() {
        while (!isFinished()) {
            record.add(nextPlayer().takeTurn(getRecord()));
        }

        return record.subList(0, record.size());
    }

}
