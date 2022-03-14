package challenge.coins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Scanner;

//@SpringBootApplication
@Profile("console")
public class CoinsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CoinsApplication.class, args);
	}

	class CoinsApplicationConsoleApp implements CommandLineRunner {
		@Autowired CoinMachine coinMachine;

		@Override
		public void run(String... args) {
			if (args.length > 0) {
				System.out.println("received " + args[0]);
				String amount = args[0];
				int asInt = Integer.parseInt(amount);
				CoinResponse response = coinMachine.exchangeBills(asInt);
				System.out.printf("response is " + response);
			}
		}
	}
	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter amount to be exchanged?");
		String amount = scanner.next();

		// Closing Scanner after the use
		scanner.close();
		consoleApp.run(amount);
	}

	@Autowired CoinsApplicationConsoleApp consoleApp;

	@Bean
	public CoinsApplicationConsoleApp consoleApp() {
		return new CoinsApplicationConsoleApp();
	}
}
