package challenge.coins;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoinMachine {
    public CoinMachine() {
        initialize();
    }

    Map<Double, Integer> coins = new TreeMap<>(Collections.reverseOrder());

    private synchronized void initialize() {
        coins.put(0.01, 99);
        coins.put(0.05, 100);
        coins.put(0.10, 100);
        coins.put(0.25, 100);
    }

    synchronized boolean coinsExist(double coin) {
        return coins.getOrDefault(coin, 0) > 0;
    }

    public synchronized boolean anymoreCoins() {
        if (coins.isEmpty()) return false;
        return coins.values().stream().filter(c -> c != 0).count() > 0;
    }

    public synchronized Collection<Coin> coinsLeft() {
        List<Coin> left = new ArrayList<>();
        for (double coinAmount: coins.keySet()) {
            int quantity = coins.get(coinAmount);
            for (int i = 0; i < quantity; i++) {
                left.add(new Coin(coinAmount));
            }
        }
        return left;
    }

    public synchronized void shutdownIfEmpty() {
        if (!anymoreCoins()) {
            System.out.println("No more coins, exiting");
            System.exit(0);
        }
    }

    public synchronized CoinResponse exchangeBills(double amount) {
        CoinResponse coinResponse = new CoinResponse();

        List<Coin> coins = new ArrayList<>();
        for (double coin : this.coins.keySet()) {
            while (coinsExist(coin) && amount - coin >= 0) {
                this.coins.replace(coin, this.coins.get(coin) - 1);
                //System.out.println("Adding coin " + coin);
                coins.add(new Coin(coin));
                amount = amount - coin;
            }
        }
        coinResponse.setCoins(coins);
        if (amount > 0) {
            coinResponse.setMessage("Not enough coins to make the amount");
        }
        return coinResponse;
    }

}
