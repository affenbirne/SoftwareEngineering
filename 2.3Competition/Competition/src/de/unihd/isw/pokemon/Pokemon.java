package de.unihd.isw.pokemon;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author David Kirk & Tornike Tsereteli
 *
 */
public class Pokemon {
	/**
	 * Name of Pokemon.
	 */
	private String name;
	/**
	 * Type of Pokemon.
	 */
	private Type type;
	/**
	 * Pokemons Trainer.
	 */
	private Trainer trainer;
	/**
	 * Pokemon ID number.
	 */
	private int number;
	/**
	 * static counter.
	 */
	private static int nextNumber = 1;
	/**
	 * List of swaps the Pokemon has been involved in.
	 */
	private List<Swap> swaps;
	/**
	 * List of competitions the Pokemon has been involved in.
	 */
	private List<Competition> competitions;
	/**
	 * Is allowed to be swapped.
	 */
	private boolean swapAllow = false;

	/**
	 * 
	 * @return List of Swaps the Pokemon has been involed in.
	 */
	public final List<Swap> getSwaps() {
		return swaps;
	}

	/**
	 * 
	 * @param swap Add a Swap that happened concerning this Pokemon.
	 */
	public final void addSwap(final Swap swap) {
		this.swaps.add(swap);
	}

	/**
	 * 
	 * @return Is the Pokemon allowed to be swapped.
	 */
	public final boolean isSwapAllow() {
		return swapAllow;
	}

	/**
	 * 
	 * @param allow Set the Pokemons swappability.
	 */
	public final void setSwapAllow(final boolean allow) {
		this.swapAllow = allow;
	}

	/**
	 * 
	 * @return Get list of Competitions the Pokemon has been involed in.
	 */
	public final List<Competition> getCompetitions() {
		return competitions;
	}

	/**
	 * 
	 * @param cmt Add a Competition the Pokemon has been involed in.
	 */
	public final void addCompetition(final Competition cmt) {
		this.competitions.add(cmt);
	}
	
	/**
	 * Constructor.
	 * @param pname The name
	 * @param ptype the Type
	 */
	public Pokemon(final String pname, final Type ptype) {
		this.number = Pokemon.nextNumber;
		Pokemon.nextNumber++;
		this.name = pname;
		this.type = ptype;
		//List is only an interface, thats why ArrayList is needed
		this.swaps = new ArrayList<Swap>();
		this.competitions = new ArrayList<Competition>();
	}
	
	/**
	 * 
	 * @param pname The Name
	 * @param ptype the Type
	 * @param ptrainer the Trainer
	 */
	public Pokemon(final String pname, final Type ptype,
			final Trainer ptrainer) {
		// call other Constructor
		this(pname, ptype);
		this.setTrainer(ptrainer);
	}

	/**
	 * 
	 * @return Pokemons Name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * 
	 * @param pname New Name
	 */
	public final void setName(final String pname) {
		this.name = pname;
	}

	/**
	 * 
	 * @return Pokemons Type
	 */
	public final Type getType() {
		return type;
	}

	/**
	 * 
	 * @return ID Number
	 */
	public final int getNumber() {
		return number;
	}

	/**
	 * @return String of Pokemon
	 */
	public final String toString() {
		return "{" + this.number + " " + name + " (" + this.type + ")}";
	}

	/**
	 * 
	 * @param ptype new Type
	 */
	public final void setType(final Type ptype) {
		this.type = ptype;
	}

	/**
	 * 
	 * @return Pokemons Trainer
	 */
	public final Trainer getTrainer() {
		return trainer;
	}

	/**
	 * 
	 * @param ptrainer New Trainer
	 */
	public final void setTrainer(final Trainer ptrainer) {
		if (this.trainer != null && this.trainer != ptrainer) {
			this.trainer.takePokemon(this);
		}
		this.trainer = ptrainer;
		if (!this.trainer.hasPokemon(this)) {
			this.trainer.givePokemon(this);
		}
		swapAllow = true;
		System.out.println(this + " gehoert jetzt zu " + trainer);
	}
}
