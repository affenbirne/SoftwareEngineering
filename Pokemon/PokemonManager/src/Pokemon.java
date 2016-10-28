public class Pokemon {
	private String name;
	private Type type;
	private Trainer trainer;
	private int number;
	private static int nextNumber = 1; // Sinnvoller Initialwert. Alternativ
										// kann abgefragt werden ob nextNumber
										// bereits initialisiert wurde

	/*
	 * Dieser Konstruktor ist sinnvoll, da die Parameter selbsterklaerend sind
	 * und alle Attribute des Objekts initialisiert werden.
	 */
	public Pokemon(String name, Type type) {
		this.number = Pokemon.nextNumber;
		Pokemon.nextNumber++;
		this.name = name;
		this.type = type;
	}
	
	public Pokemon(String name, Type type, Trainer trainer) {
		this.number = Pokemon.nextNumber;
		Pokemon.nextNumber++;
		this.name = name;
		this.type = type;
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
		System.out.println(this + " gehoert jetzt zu " + trainer);
	}
}
