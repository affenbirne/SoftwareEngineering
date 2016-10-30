package de.unihd.isw.pokemon;
import java.util.ArrayList;

/**
 * 
 * @author David Kirk & Tornike Tsereteli
 *
 */
public class Trainer {
	/**
	 * Trainers first and last Name.
	 */
	private String firstname, lastname;
	/**
	 * Trainers Pokemons.
	 */
	private ArrayList<Pokemon> pokemons;
	
	/**
	 * Constructor.
	 * @param fname First Name
	 * @param lname Last Name
	 */
	Trainer(final String fname, final String lname) {
		this.pokemons = new ArrayList<Pokemon>();
		this.firstname = fname;
		this.lastname = lname;
	}

	/**
	 * 
	 * @return Returns firstname.
	 */
	public final String getFirstname() {
		return firstname;
	}

	/**
	 * 
	 * @param fname First name
	 */
	public final void setFirstname(final String fname) {
		this.firstname = fname;
	}

	/**
	 * 
	 * @return Last name
	 */
	public final String getLastname() {
		return lastname;
	}

	/**
	 * 
	 * @param lname Last name
	 */
	public final void setLastname(final String lname) {
		this.lastname = lname;
	}

	/**
	 * 
	 * @return The list of all the Trainers Pokemons
	 */
	public final ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	/**
	 * Gives the Trainer a Pokemon.
	 * @param pokemon The new Pokemon
	 */
	public final void givePokemon(final Pokemon pokemon) {
		if (!pokemons.contains(pokemon)) {
			pokemons.add(pokemon);
		}
	}

	/**
	 * Takes a Pokemon from the Trainer away.
	 * @param pokemon Which Pokemon to take away
	 */
	public final void takePokemon(final Pokemon pokemon) {
		if (pokemons.contains(pokemon)) {
			pokemons.remove(pokemon);
			System.out.println(pokemon + " verlaesst " + this);
		}
	}

	/**
	 * Abfrage von einzelnen Pokemons über den Trainer ANSATZ 1.
	 * @param index Index of Pokemon in List
	 * @return Returns the Pokemon at the indexed entry.
	 */
	public final Pokemon getPokemon(final int index) {
		return pokemons.get(index);
	}

	/**
	 * Abfrage von einzelnen Pokemons über den Trainer ANSATZ 2.
	 * @param pokemon Pokemon to look for.
	 * @return Returns true if the Trainer has the Pokemon.
	 */
	public final boolean hasPokemon(final Pokemon pokemon) {
		return pokemons.contains(pokemon);
	}

	/**
	 * Conversion to String.
	 * @return String of the Trainer.
	 */
	@Override
	public final String toString() {
		return "{" + this.firstname + " " + this.lastname
				+ " (" + this.pokemons.size() + " Pokemons)}";
	}
}
