package pokemon.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pokemon.Competition;
import pokemon.Swap;

/**
 * @author paul
 *
 */
public class Pokemon {

	/**	 */
	private String name;
	/**	 */
	private Type type;
	/**	 */
	private Trainer trainer;
	/**	 */
	private int number;
	/**	 */
	private static int nextNumber;

	/**	 */
	private List<Swap> swaps = new ArrayList<Swap>();
	/**	 */
	private boolean swapAllow = true;

	/**	 */
	private List<Competition> competitions = new ArrayList<Competition>();
	
	
	/**
	 * @param name
	 * @param type
	 */
	public Pokemon(String name, Type type) {
		this.name = name;
		this.type = type;
		this.number = nextNumber;
		nextNumber++;
	}

	/**	 */
	public String getName() {
		return name;
	}
	
	/**	 */
	public void setName(String name) {
		// this references the actual object instance
		this.name = name;
	}

	/**	 */
	public Type getType() {
		return type;
	}

	/**	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**	 */
	public Trainer getTrainer() {
		return trainer;
	}

	/**	 */
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	/**	 */
	public int getNumber() {
		return this.number;
	}

	/**	 */
	public List<Swap> getSwaps() {
		return swaps;
	}

	/**	 */
	public void setSwaps(List<Swap> swaps) {
		this.swaps = swaps;
	}
	
	/**	 */
	public void addSwap(Swap swap){
		getSwaps().add(swap);
	}

	/**	 */
	public List<Competition> getCompetitions() {
		return competitions;
	}

	/**	 */
	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}
	
	/**
	 * @param competition
	 */
	public void addCompetition(Competition competition) {
		getCompetitions().add(competition);
	}

	
	/**
	 * @return
	 */
	public boolean isSwapAllow() {
		return swapAllow;
	}

	/**
	 * @param swapAllow
	 */
	public void setSwapAllow(boolean swapAllow) {
		this.swapAllow = swapAllow;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType() + "' has trainer '"
				+ getTrainer() + "'";
	}

	/**
	 * @param args
	 * 
	 */
	public static void main(final String[] args) {
		// geschenkt

	}
	
	public static Comparator<Pokemon> compareByName = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon p1, Pokemon p2) {
			return p1.name.compareTo(p2.name);
		}
	};
	public static Comparator<Pokemon> compareByNumber = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon p1, Pokemon p2) {
			return p1.number - p2.number;
		}
	};
	public static Comparator<Pokemon> compareByType = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon p1, Pokemon p2) {
			return p1.type.compareTo(p2.type);
		}
	};
	public static Comparator<Pokemon> compareByTrainer = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon p1, Pokemon p2) {
				return p1.trainer.toString().compareTo(p2.trainer.toString());
		}
	};
	public static Comparator<Pokemon> compareBySwaps = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon p1, Pokemon p2) {
				return p1.swaps.size() - p2.swaps.size();
		}
	};
	public static Comparator<Pokemon> compareBySwapAllow = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon p1, Pokemon p2) {
				return Boolean.compare(p1.swapAllow, p2.swapAllow);
		}
	};
	public static Comparator<Pokemon> compareByCompetitions = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon p1, Pokemon p2) {
				return p1.competitions.size() - p2.competitions.size();
		}
	};
}
