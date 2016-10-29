
public class Competition extends Swap {
	public Competition(Pokemon pokemon1, Pokemon pokemon2) {
		super(pokemon1, pokemon2);

		Pokemon loser = this.fight();
		Pokemon winner;
		if (loser == pokemon1) {
			winner = pokemon2;
		}
		else {
			winner = pokemon1;
		}
		loser.setTrainer(winner.getTrainer());
		this.id = this.time + ": " + winner + " beat " + loser;
	}

	/*
	 * Returns the Pokemon that lost
	 */
	private Pokemon fight() {
		// Coefficient that the first Pokemon wins
		float w = (float) java.lang.Math.random();
		// Advantage counts 1/3, Random counts 2/3
		w += Type.getAdvantage(this.pokemon1.getType(), this.pokemon2.getType()) / 3;
		if (w > 0.5) {
			// Pokemon2 lost
			return pokemon2;
		}
		return pokemon1;
	}

	public static Competition execute(Pokemon pokemon1, Pokemon pokemon2) {
		if (pokemon1 == null || pokemon2 == null) {
			System.err.println("No heap-address.");
		}
		if (pokemon1.getTrainer() == pokemon2.getTrainer()) {
			System.err.println("No Competition: Can't let Pokemons of the same trainer compete.");
			return null;
		}

		Competition cmt = new Competition(pokemon1, pokemon2);
		pokemon1.addCompetition(cmt);
		pokemon2.addCompetition(cmt);
		return cmt;
	}
}
