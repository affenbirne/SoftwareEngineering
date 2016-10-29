import java.util.Date;

public class Swap {
	private String id;
	private Trainer trainer1, trainer2;
	private Pokemon pokemon1, pokemon2;
	private Date time;

	public Swap(Pokemon pokemon1, Pokemon pokemon2) {
		this.time = new Date();
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
		this.trainer1 = pokemon1.getTrainer();
		this.trainer2 = pokemon2.getTrainer();
		this.pokemon1.setTrainer(trainer2);
		this.pokemon2.setTrainer(trainer1);
		this.id = this.time + ": " + pokemon1 + " <-> " + pokemon2;
	}

	public String toString() {
		return this.id;
	}

	public static Swap execute(Pokemon pokemon1, Pokemon pokemon2) {
		if (pokemon1 == null || pokemon2 == null) {
			System.err.println("No heap-address.");
		}
		if (!pokemon1.isSwapAllow()) {
			System.err.println("Swap impossible: " + pokemon1 + " is not swapable.");
			return null;
		}
		if (!pokemon2.isSwapAllow()) {
			System.err.println("Swap impossible: " + pokemon2 + " is not swapable.");
			return null;
		}
		if (pokemon1.getType() == pokemon2.getType()) {
			System.err.println("Swap impossible: " + pokemon1 + " and " + pokemon2 + " are of the same type.");
			return null;
		}
		if (pokemon1.getTrainer() == pokemon2.getTrainer()) {
			System.err.println("Swap impossible: " + pokemon1 + " and " + pokemon2 + " have the same trainer.");
			return null;
		}

		Swap swap = new Swap(pokemon1, pokemon2);
		pokemon1.addSwap(swap);
		pokemon2.addSwap(swap);
		return swap;
	}
}