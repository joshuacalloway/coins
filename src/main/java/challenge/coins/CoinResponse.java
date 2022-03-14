package challenge.coins;

import java.util.ArrayList;
import java.util.List;

public class CoinResponse {
    String message = "";
    List<Coin> coins = new ArrayList<>();

    @Override
    public String toString() {
        return "CoinResponse{" +
                "message='" + message + '\'' +
                ", coins=" + coins +
                '}';
    }

    public CoinResponse() {
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }
}
