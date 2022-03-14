package challenge.coins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinMachineController {
    @Autowired CoinMachine coinMachine;

    @GetMapping("/exchange")
    public CoinResponse exchangeAmount(@RequestParam(name = "amount") Integer amount) {
        coinMachine.shutdownIfEmpty();
        return coinMachine.exchangeBills(amount);
    }
}
