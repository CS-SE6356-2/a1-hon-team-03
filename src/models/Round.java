package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Round<T> {

    private ArrayList<Turn<T>> record;

    public Round() {
        record = new ArrayList();
    }

    // Each of these functions should be expected to be called exactly once
    // per player turn
    abstract boolean isFinished();

    abstract Player nextPlayer();

    abstract int numViewable();

    private List<Turn<T>> getRecord() {
        int viewable = numViewable();
        if (record.size() > viewable) {
            return Collections.unmodifiableList(record.subList(record.size() - viewable, record.size()));
        } else {
            return Collections.unmodifiableList(record.subList(0, record.size()));
        }
    }

    public List<Turn<T>> playRound() {
        while (isFinished() == false) {
            record.add(nextPlayer().takeTurn(getRecord()));
        }

        return record.subList(0, record.size());
    }

}
