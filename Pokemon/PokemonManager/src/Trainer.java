import java.util.ArrayList;

public class Trainer {
	private String firstname;
	private String lastname;
	private ArrayList<Pokemon> pokemons;
	
	Trainer(String firstname, String lastname) {
		this.pokemons = new ArrayList<Pokemon>();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	public void givePokemon(Pokemon pokemon) {
		if (!pokemons.contains(pokemon)) {
			pokemons.add(pokemon);
		}
	}

	public void takePokemon(Pokemon pokemon) {
		if (pokemons.contains(pokemon)) {
			pokemons.remove(pokemon);
			System.out.println(pokemon + " verlaesst " + this);
		}
	}

	/*
	 * Abfrage von einzelnen Pokemons über den Trainer ANSATZ 1
	 */
	public Pokemon getPokemon(int index) {
		return pokemons.get(index);
	}

	/*
	 * Abfrage von einzelnen Pokemons über den Trainer ANSATZ 2
	 */
	public boolean hasPokemon(Pokemon pokemon) {
		return pokemons.contains(pokemon);
	}

	public String toString() {
		return "<" + this.firstname + " " + this.lastname + " (" + this.pokemons.size() + " Pokemons)>";
	}
}
