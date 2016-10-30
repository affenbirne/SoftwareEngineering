package de.unihd.isw.pokemon;
import java.util.Date;

/**
 * 
 * @author David Kirk & Tornike Tsereteli
 *
 */
public class Swap {
	/**
	 * Id of the Swap.
	 */
	private String id;
	/**
	 * The Trainers involved.
	 */
	private Trainer trainer1, trainer2;
	/**
	 * The swapped Pokemon (order bevor swap).
	 */
	private Pokemon pokemon1, pokemon2;
	/**
	 * the Date of the Swap.
	 */
	private Date time;

	/**
	 * 
	 * @return Time of Swap.
	 */
	protected final Date getTime() {
		return time;
	}

	/**
	 * 
	 * @return Trainer 1
	 */
	public final Trainer getTrainer1() {
		return trainer1;
	}

	/**
	 * 
	 * @return Trainer 2
	 */
	public final Trainer getTrainer2() {
		return trainer2;
	}

	/**
	 * 
	 * @return Pokemon 1
	 */
	public final Pokemon getPokemon1() {
		return pokemon1;
	}

	/**
	 * 
	 * @return Pokemon 2
	 */
	public final Pokemon getPokemon2() {
		return pokemon2;
	}

	/**
	 * Constructor.
	 * @param p1 Pokemon 1 of Swap
	 * @param p2 Pokemon 2 of Swap
	 */
	public Swap(final Pokemon p1, final Pokemon p2) {
		this.time = new Date();
		this.pokemon1 = p1;
		this.pokemon2 = p2;
		this.trainer1 = p1.getTrainer();
		this.trainer2 = p2.getTrainer();
		this.id = this.time + ": " + p1 + " <-> " + p2;
	}

	/**
	 * @return The Id of the Swap
	 */
	public final String toString() {
		return this.id;
	}

	/**
	 * 
	 * @param pokemon1 Pokemon 1 of swap
	 * @param pokemon2 Pokemon 2 of swap
	 * @return The Swap instance.
	 */
	public static Swap execute(final Pokemon pokemon1,
			final Pokemon pokemon2) {
		if (pokemon1 == null || pokemon2 == null) {
			System.err.println("No heap-address.");
			return null;
		}
		if (!pokemon1.isSwapAllow()) {
			System.err.println("Swap impossible: " + pokemon1
					+ " is not swapable.");
			return null;
		}
		if (!pokemon2.isSwapAllow()) {
			System.err.println("Swap impossible: " + pokemon2
					+ " is not swapable.");
			return null;
		}
		if (pokemon1.getType() == pokemon2.getType()) {
			System.err.println("Swap impossible: " + pokemon1
					+ " and " + pokemon2 + " are of the same type.");
			return null;
		}
		if (pokemon1.getTrainer() == pokemon2.getTrainer()) {
			System.err.println("Swap impossible: " + pokemon1
					+ " and " + pokemon2 + " have the same trainer.");
			return null;
		}

		Swap swap = new Swap(pokemon1, pokemon2);
		// Do the Swap:
		Trainer t1 = pokemon1.getTrainer();
		pokemon1.setTrainer(pokemon2.getTrainer());
		pokemon2.setTrainer(t1);
		pokemon1.addSwap(swap);
		pokemon2.addSwap(swap);
		return swap;
	}
	
	/**
	 * 
	 * @param newID New ID
	 */
	protected final void setID(final String newID) {
		this.id = newID;
	}
}