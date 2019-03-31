package gui.components.card.model;

public class CardNumber {
    private int value;

    public CardNumber(int value) {
        this.value = value;
    }

    public static String NumberToString(CardNumber card) {
        int value = card.value;
        if (value == 1)
            return "ace";
        else if (value >= 2 && value <= 9) return Integer.toString(value);
        else if (value == 10) return "jack";
        else if (value == 11) return "queen";
        else if (value == 12) return "king";
        else return null;
    }
}
