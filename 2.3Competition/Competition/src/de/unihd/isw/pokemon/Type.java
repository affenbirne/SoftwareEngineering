package de.unihd.isw.pokemon;

/**
 * 
 * @author David Kirk & Tornike Tsereteli
 *
 */
public enum Type {
	/**
	 * So far only three Types.
	 */
	Fire, Water, Poison;
	
	/**
	 * The type advantage factor. The higher, the bigger the type advantage. 
	 */
	private static final float TAF = 0.33f;
	
	/**
	 * 
	 * @param t1 First Type
	 * @param t2 Second Type
	 * @return Returns 0 for same Type, +TAF for t1 > t2, -TAF for t1 < t2
	 */
	public static float getAdvantage(final Type t1, final Type t2) {
		if (t1 == Poison && t2 == Water) {
			// Poison > Water
			return TAF;
		}
		if (t1 == Water && t2 == Fire) {
			// Water > Fire
			return TAF;
		}
		if (t1 == Fire && t2 == Poison) {
			// Fire > Poison
			return TAF;
		}
		if (t2 == Poison && t1 == Water) {
			// Poison > Water
			return -TAF;
		}
		if (t2 == Water && t1 == Fire) {
			// Water > Fire
			return -TAF;
		}
		if (t2 == Fire && t1 == Poison) {
			// Fire > Poison
			return -TAF;
		}
		return 0;
	}
}