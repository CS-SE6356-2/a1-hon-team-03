package game;
import java.util.*;

public abstract class Player{
    Hand myHand;
    boolean dealer;
    int score;
    
    public Player(){
        myHand = new ArrayList<Hand>();
        score = 0;
    }
    
    abstract Card drawCard();
    abstract void discardCard();
    abstract void bid();
    abstract void splitDeck(Deck deck);
    abstract void giveCards(Player p);
    abstract Turn takeTurn(List<Turn> hist);

    public int getScore(){
        return score;
    }

    public Hand getHand(){
        return myHand;
    }

    private void setDealer(boolean dealer){
        this.dealer = dealer;
    }

    public boolean isDealer(){
        return dealer;
    }
    
    
}
