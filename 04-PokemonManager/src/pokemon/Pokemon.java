package pokemon;

import java.util.ArrayList;
import java.util.List;

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
		this.type = Type.Water;
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
	public void addSwap(Swap swap) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType() + "' has trainer '"
				+ getTrainer() + "'";
	}

	/**
	 * Calculate the average force of a Pokemon based on the passed Competitions
	 * of the Pokemon
	 * 
	 * @return double average force of Pokemon
	 */
	public double averageForce() {
		double averageForce;
		double sumScores = 0;
		int nrOfValidScores = 0;
		// check if competitions list is initialized
		if (this.getCompetitions() != null) {
			// check if competitions exist
			if (!this.getCompetitions().isEmpty()) {
				// sum up the score of all competitions
				for (Competition c : this.getCompetitions()) {
					// get the score of the completion for Pokemon
					double cscore = c.getScores().get(this);
					// get the score for the type +1 since ordinal
					// starts with 0
					if (cscore != 0) {
						double tscore = (this.getType().ordinal() + 1);
						// normalize the score
						double nscore = tscore * (cscore / (cscore+1));
						sumScores += nscore;
						nrOfValidScores++;
					}
				}
			} else {
				// list of competition is empty
				return 0;
			}
		} else {
			// competitions not initialized
			return -1;
		}
		// List not empty, but no valid scores
		if (nrOfValidScores < 1) {
			return -1;
		}
		averageForce = sumScores / nrOfValidScores;
		return averageForce;
	}

}