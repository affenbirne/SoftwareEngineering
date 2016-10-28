import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class PokemonManager {
	/*
	 * "Fuer welche Collection habe ich mich entschieden?" 
	 * Ich habe mich fuer ArrayList als Liste von Pokemon eines Trainers 
	 * und HashSet fuer alle Trainer im PokemonManager entschieden.
	 * 
	 * Collection		Vorteile					Nachteile
	 * ------------------------------------------------------------------------
	 * ArrayList		Elemente haben Index,	
	 * 					Doppelte Elemente moeglich
	 * 					direkter Zugriff moeglich
	 * 
	 * HashMap			schneller Zugriff			keine doppelten Elemente
	 * 					geordnet
	 * 
	 * HashSet			schneller Zugriff			keine doppelten Elemente
	 * 												ungeordnet
	 * 
	 * Die HashSet ermoeglicht die Verwaltung der Trainer die nicht geordnet erfolgen muss,
	 * die ArrayList listet deren Pokemon auf, wobei es keine Doppelzuweisung geben darf.
	 */
	private HashSet<Trainer> trainers;
	
	PokemonManager() {
		trainers = new HashSet<Trainer>();
	}
	
	public void givePokemonToTrainer(Pokemon pokemon, Trainer trainer) {
		trainer.givePokemon(pokemon);
	}

	/*
	 * Abfrage aller Pokemons eines Trainers
	 */
	public ArrayList<Pokemon> getAllPokemonsOfTrainer(Trainer trainer) {
		return trainer.getPokemons();
	}
	
	public HashSet<Pokemon> getAllPokemonByType(Type type) {
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
	
	/*
	 * Abfrage aller Pokemons vom Type Poison
	 */
	public HashSet<Pokemon> getAllPoisonPokemon() {
		return this.getAllPokemonByType(Type.Poison);
	}
	
	public HashSet<Trainer> getTrainers() {
		return this.trainers;
	}
	
	public static void main(String[] args) {
		Trainer ash, gary, morty;
		ash = new Trainer("Ash", "Ketchum");
		gary = new Trainer("Gary", "Oak");
		morty = new Trainer("Morty", "Smith");
		
		PokemonManager PokeMgr = new PokemonManager();
		PokeMgr.getTrainers().add(ash);
		PokeMgr.getTrainers().add(gary);
		PokeMgr.getTrainers().add(morty);
		
		new Pokemon("Shiggy", Type.Water, ash);
		new Pokemon("ekans", Type.Poison, gary);
		new Pokemon("Koffing", Type.Poison, gary);
		new Pokemon("Muk", Type.Poison, morty);
		Pokemon miew = new Pokemon("Miewtwo", Type.Water, ash);
		Pokemon pika = new Pokemon("Pikachu", Type.Fire, gary);

		/*System.out.println("Trainer: " + PokeMgr.getTrainers());
		System.out.println(ash + " trainiert " + ash.getPokemons());
		System.out.println("Poison Pokemon: " + PokeMgr.getAllPoisonPokemon()); */
		
		System.out.println(Swap.execute(miew, pika));
		
	}
}
