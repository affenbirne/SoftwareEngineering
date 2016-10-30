package de.unihd.isw.pokemon;

/**
 * A Competition is a happened fight between two Pokemon.
 * The Pokemon losing a fight switches to the other's Trainer.
 */
public class Competition extends Swap {
	/**
	 * Handles the Competition.
	 * @param pokemon1	First Pokemon
	 * @param pokemon2	Second Pokemon
	 */
	public Competition(final Pokemon pokemon1, final Pokemon pokemon2) {
		super(pokemon1, pokemon2);

		Pokemon loser = this.fight();
		Pokemon winner;
		if (loser == pokemon1) {
			winner = pokemon2;
		} else {
			winner = pokemon1;
		}
		loser.setTrainer(winner.getTrainer());
		this.setID(this.getTime() + ": " + winner + " beat " + loser);
	}

	/**
	 * @return Returns the loser of the Fight
	 */
	private Pokemon fight() {
		// Coefficient that the first Pokemon wins
		float w = (float) java.lang.Math.random();
		// Advantage counts 1/3, Random counts 2/3
		w += Type.getAdvantage(this.getPokemon1().getType(),
				this.getPokemon2().getType());
		final float mid = 0.5f;
		if (w > mid) {
			// Pokemon2 lost
			return this.getPokemon2();
		}
		return this.getPokemon1();
	}

	/**
	 * 
	 * @param pokemon1 first Pokemon to compete
	 * @param pokemon2 second Pokemon to compete
	 * @return The resulting Competition instance
	 */
	public static Competition execute(final Pokemon pokemon1,
			final Pokemon pokemon2) {
		if (pokemon1 == null || pokemon2 == null) {
			System.err.println("No heap-address.");
			return null;
		}
		if (pokemon1.getTrainer() == pokemon2.getTrainer()) {
			System.err.println("No Competition: "
					+ "Can't let Pokemons of the same trainer compete.");
			return null;
		}

		Competition cmt = new Competition(pokemon1, pokemon2);
		pokemon1.addCompetition(cmt);
		pokemon2.addCompetition(cmt);
		return cmt;
	}
}
