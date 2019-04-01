package game;

import java.util.ArrayList;
//game uses handler function which handles button presses etc.
public abstract class Game extends EventHandler {
    Deck starter;
    Deck draw;
    Deck discard;
    ArrayList<View> views;
    ArrayList<Player> players;
    ArrayList<Integer> bidList;
    String rule;
    
    public Game(){
        players = new ArrayList<View>();
        bidList = new ArrayList<View>();
        starter = new Deck();
        draw = new Deck();
        discard = new Deck();
        rule = "";
    }
    
    abstract int calcScore(Player p)
    abstract int evaluateScore()
    
    private ArrayList<View> getViews(){
        return views;
    }
}
