public enum Type {
	Fire, Water, Poison;

	/*
	 * Returns 0 for same Type, +1 for t1 > t2, -1 for t1 < t2
	 */
	public static int getAdvantage(Type t1, Type t2) {
		if (t1 == Poison && t2 == Water) {
			// Poison > Water
			return 1;
		}
		if (t1 == Water && t2 == Fire) {
			// Water > Fire
			return 1;
		}
		if (t1 == Fire && t2 == Poison) {
			// Fire > Poison
			return 1;
		}
		if (t2 == Poison && t1 == Water) {
			// Poison > Water
			return -1;
		}
		if (t2 == Water && t1 == Fire) {
			// Water > Fire
			return -1;
		}
		if (t2 == Fire && t1 == Poison) {
			// Fire > Poison
			return -1;
		}
		return 0;
	}
}