import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import models.*;

class RoundTest {
    static int countLimit = 10;
    static int viewableLimit = 10;
    Round round;
    int viewableCalls = 0;
    int playerCalls = 0;
        void bid() {}
        void SplitDeck(Deck deck) {}
        void giveCards(Player p) {}
        Turn<int> takeTurn(List<Turn<Object>> hist) {
            assertTrue(hist.size() <= viewableLimit);
            return Turn<int>(this, this, 0);
        }
    }

    @BeforeEach
    void setup() {
        round = new class extends Round<int> {
            @Override
            Player nextPlayer() {
                playerCalls++;
                if (playerCalls < countLimit) {
                    return new PlayerStub();
                } else {
                    return null;
                }
            }

            @Override
            int numViewable() {
                viewableCalls++;
                return viewableLimit;
            }
        }
    }

    @Test
    void testHitLimit() {
        countLimit = 10;
        viewableLimit = 20;
        round.playRound();
        assertEquals(countLimit, viewableCalls);
        assertEquals(countLimit, playerCalls);
    }

    @Test
    void testViewableLimit() {
        countLimit = 20;
        viewableLimit = 10;
        round.playRound();
    }

}
