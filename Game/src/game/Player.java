package game;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    ArrayList<Card> myHand;
    boolean dealer;
    int score;

    public Player() {
        myHand = new ArrayList<Card>();
        score = 0;
    }

    abstract Card drawCard();

    abstract void discardCard();

    abstract void bid();

    abstract void splitDeck(Deck deck);

    abstract void giveCards(Player p);

    abstract <T> Turn<T> takeTurn(List<Turn<T>> hist);

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getHand() {
        return myHand;
    }

    public boolean isDealer() {
        return dealer;
    }

    private void setDealer(boolean dealer) {
        this.dealer = dealer;
    }


}
