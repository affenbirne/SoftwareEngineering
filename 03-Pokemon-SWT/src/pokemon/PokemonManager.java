package pokemon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import pokemon.data.Pokemon;
import pokemon.data.Trainer;
import pokemon.data.Type;
import pokemon.ui.PokemonUI;

/**
 * The PokemonManager Class
 */
public class PokemonManager {
	/***/
	private static ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

	public static ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	public static void addPokemon(Pokemon pokemon) {
		PokemonManager.pokemons.add(pokemon);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a SWT window
		Display display = new Display();
		Shell shell = new Shell(display);
		PokemonUI pui;
		
		// load Everything
		loadPokemon();
		
		// initialize and open the PokemonUI right here !
		pui = new PokemonUI(shell, pokemons);
		pui.open();
	}
	
	public static void storePokemons() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("pokemon.dat"));
			oos.writeObject(pokemons);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadPokemon() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("pokemon.dat"));
			pokemons = (ArrayList<Pokemon>) ois.readObject();
			ois.close();
			for (Pokemon poke : pokemons) {
				if (!Trainer.getTrainers().contains(poke.getTrainer())) {
					Trainer.getTrainers().add(poke.getTrainer());
				}
			}
		} catch (IOException e) {
			makePokemonData();
			storePokemons();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void makePokemonData() {
		Trainer pete = new Trainer("Peter", "Lustig");
		Trainer ali = new Trainer("Alisa", "Traurig");
		
		Pokemon pika = new Pokemon("Pikachu", Type.Poison);
		pete.addPokemon(pika);
		pokemons.add(pika);
		Pokemon cara = new Pokemon("Carapuce", Type.Water);
		ali.addPokemon(cara);
		cara.setSwapAllow(false);
		pokemons.add(cara);
		Pokemon raup = new Pokemon("Raupy", Type.Fire);
		ali.addPokemon(raup);
		pokemons.add(raup);

		pika.addSwap(new Swap());
		pika.addSwap(new Swap());
		pika.addSwap(new Swap());
		pika.addSwap(new Swap());
		pika.addCompetition(new Competition());
		pika.addCompetition(new Competition());
		cara.addCompetition(new Competition());
		cara.addCompetition(new Competition());
		cara.addCompetition(new Competition());
		cara.addCompetition(new Competition());
		cara.addCompetition(new Competition());
		cara.addCompetition(new Competition());
		cara.addCompetition(new Competition());
		raup.addCompetition(new Competition());
		raup.addSwap(new Swap());
		raup.addSwap(new Swap());
		raup.addSwap(new Swap());
	}
}
