package game;

public class Turn<T> {
    private game.Player p;
    private Pile playArea;
    private T val;

    public Turn(Player p, Pile playArea, T val) {
            this.p = p;
            this.playArea = playArea;
            this.val = val;
    }

    public Pile getPlayArea() { return playArea; }
    public T getVal() { return val; }
    public Player getPlayer() { return p; }
}
