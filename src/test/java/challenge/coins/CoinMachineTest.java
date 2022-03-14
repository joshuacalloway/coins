package challenge.coins;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinMachineTest {

    CoinMachine coinMachine = new CoinMachine();

    @Test
    public void oneDollarEqualsFourQuarters() {
        CoinResponse coinResponse = coinMachine.exchangeBills(1);

        List expectedCoins = new ArrayList();
        expectedCoins.add(new Coin(0.25));
        expectedCoins.add(new Coin(0.25));
        expectedCoins.add(new Coin(0.25));
        expectedCoins.add(new Coin(0.25));

        assertEquals(expectedCoins, coinResponse.getCoins());
        assertEquals(coinResponse.getMessage(), "");
    }

    @Test
    public void notEnoughCoinsForHundred() {
        CoinResponse coinResponse = coinMachine.exchangeBills(100);

        List expectedCoins = new ArrayList();
        expectedCoins.add(new Coin(0.25));
        expectedCoins.add(new Coin(0.25));
        expectedCoins.add(new Coin(0.25));
        expectedCoins.add(new Coin(0.25));

        assertEquals(coinResponse.getMessage(), "Not enough coins to make the amount");
    }

    @Test void canEmptyCoinMachine() {
        coinMachine.exchangeBills(25);
        assertTrue(coinMachine.anymoreCoins());

        coinMachine.exchangeBills(10);
        assertTrue(coinMachine.anymoreCoins());

        coinMachine.exchangeBills(5);
        assertTrue(coinMachine.anymoreCoins());

        coinMachine.exchangeBills(1);
        Collection<Coin> coinsLeft = coinMachine.coinsLeft();
        System.out.println("coinsLeft are " + coinsLeft);
        assertFalse(coinMachine.anymoreCoins());
    }

    @Test void emptyCoinMachineIsEmpty() {
        assertFalse(coinMachine.anymoreCoins());
    }
}