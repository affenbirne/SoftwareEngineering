import java.util.ArrayList;
import java.util.List;

public class Pokemon {
	private String name;
	private Type type;
	private Trainer trainer;
	private int number;
	private static int nextNumber = 1;
	private List<Swap> swaps;
	private List<Competition> competitions;
	private boolean swapAllow = false;

	public List<Swap> getSwaps() {
		return swaps;
	}

	public void addSwap(Swap swap) {
		this.swaps.add(swap);
	}

	public boolean isSwapAllow() {
		return swapAllow;
	}

	public void setSwapAllow(boolean swapAllow) {
		this.swapAllow = swapAllow;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void addCompetition(Competition cmt) {
		this.competitions.add(cmt);
	}
	
	/*
	 * Konstruktor
	 */
	public Pokemon(String name, Type type) {
		this.number = Pokemon.nextNumber;
		Pokemon.nextNumber++;
		this.name = name;
		this.type = type;
		//List is only an interface, thats why ArrayList is needed
		this.swaps = new ArrayList<Swap>();
		this.competitions = new ArrayList<Competition>();
	}
	
	public Pokemon(String name, Type type, Trainer trainer) {
		// call other Constructor
		this(name, type);
		this.setTrainer(trainer);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String toString() {
		return "<" + this.number + " " + name + " (" + this.type + ")>";
	}

	public void setType(Type type) {
		/*
		 * this ist eine Referenz auf das Objekt selbst, welches die Methode
		 * aufruft. Hier ändert das Objekt SEIN Attribut type in den
		 * eingegebenen Parameter type. Ohne this koennte Java type und type
		 * nicht auseinanderhalten.
		 */
		this.type = type;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		if (this.trainer != null && this.trainer != trainer) {
			this.trainer.takePokemon(this);
		}
		this.trainer = trainer;
		if (!this.trainer.hasPokemon(this)) {
			this.trainer.givePokemon(this);
		}
		swapAllow = true;
		System.out.println(this + " gehoert jetzt zu " + trainer);
	}
}
