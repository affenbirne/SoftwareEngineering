package de.unihd.isw.pokemon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author David Kirk & Tornike Tsereteli
 *
 */
public class PokemonManager {
	/**
	 * Set of all Trainers.
	 */
	private HashSet<Trainer> trainers;
	
	/**
	 * Constructor.
	 */
	PokemonManager() {
		trainers = new HashSet<Trainer>();
	}
	
	/**
	 * 
	 * @param pokemon Which Pokemon to give to the Trainer.
	 * @param trainer Which Trainer to give the Pokemon to.
	 */
	public final void givePokemonToTrainer(final Pokemon pokemon,
			final Trainer trainer) {
		trainer.givePokemon(pokemon);
	}

	/**
	 * 
	 * @param trainer Whos Pokemon to get
	 * @return Returns all Pokemon of the Trainer
	 */
	public final ArrayList<Pokemon> getAllPokemonsOfTrainer(
			final Trainer trainer) {
		return trainer.getPokemons();
	}
	
	/**
	 * 
	 * @param type Which Type to filter
	 * @return All Pokemon of the Type type
	 */
	public final HashSet<Pokemon> getAllPokemonByType(final Type type) {
		HashSet<Pokemon> ret = new HashSet<Pokemon>();
		Iterator<Trainer> it = trainers.iterator();
		while (it.hasNext()) {
			ArrayList<Pokemon> pokemons = it.next().getPokemons();
			for (int i = 0; i < pokemons.size(); i++) {
				if (pokemons.get(i).getType() == type) {
					ret.add(pokemons.get(i));
				}
			}
		}
		return ret;
	}
	
	/**
	 * 
	 * @return All Poison Pokemons
	 */
	public final HashSet<Pokemon> getAllPoisonPokemon() {
		return this.getAllPokemonByType(Type.Poison);
	}
	
	/**
	 * 
	 * @return All Trainers
	 */
	public final HashSet<Trainer> getTrainers() {
		return this.trainers;
	}
	
	/**
	 * main.
	 * @param args Argumente
	 */
	public static void main(final String[] args) {
		Trainer ash, gary, morty;
		ash = new Trainer("Ash", "Ketchum");
		gary = new Trainer("Gary", "Oak");
		morty = new Trainer("Morty", "Smith");
		
		PokemonManager pokeMgr = new PokemonManager();
		pokeMgr.getTrainers().add(ash);
		pokeMgr.getTrainers().add(gary);
		pokeMgr.getTrainers().add(morty);
		
		Pokemon kof = new Pokemon("Koffing", Type.Poison, gary);
		Pokemon muk = new Pokemon("Muk", Type.Poison, morty);
		Pokemon miew = new Pokemon("Miewtwo", Type.Water, ash);
		Pokemon pika = new Pokemon("Pikachu", Type.Fire, gary);

		System.out.println("Competitions:");
		Competition cmt1 = Competition.execute(miew, pika);
		if (cmt1 != null) {
			System.out.println(cmt1);
		}
		Competition cmt2 = Competition.execute(kof, muk);
		if (cmt2 != null) {
			System.out.println(cmt2);
		}
	}
}
