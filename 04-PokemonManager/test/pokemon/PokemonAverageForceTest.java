package pokemon;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class PokemonAverageForceTest extends TestCase {

	private Pokemon pokemon0;
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception {
		super.setUp();
		pokemon0 = new Pokemon("Pikachu", Type.Poison);
		pokemon1 = new Pokemon("Carapuce", Type.Water);
		pokemon2 = new Pokemon("Raupy", Type.Fire);
		Trainer t0 = new Trainer("Peter", "Lustig");
		Trainer t1 = new Trainer("Alisa", "Traurig");
		t0.addPokemon(pokemon0);
		List<Pokemon> ps = new ArrayList<Pokemon>();
		ps.add(pokemon1);
		ps.add(pokemon2);
		t1.setPokemons(ps);
		Competition c0 = new Competition();
		c0.execute(pokemon0, pokemon1);
	}
	
	public void testPokemonAverageForceTypeWaterOneScore() throws Exception {
		System.out.println("\nStart TestCase 1");
		assertNotNull(pokemon1);
		// ensure pokemon has the desired type
		assertEquals(Type.Water, pokemon1.getType());
		// max force value for pokemon type
		double maxForce = pokemon1.getType().ordinal() + 1;
		// check that the average force is below the allowed maximum force
		// this is the most important call of the test
		assertTrue(maxForce > pokemon1.averageForce() );
		assertEquals("Carapuce", pokemon1.getName());
		System.out.println(pokemon1 + "averageForce:" + pokemon1.averageForce() );
		System.out.println("End TestCase 1\n");
	}
	
	public void testPokemonAverageForceAnotherTest() throws Exception {
		// TODO: implement another JUnit test here ... 
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		pokemon0 = null;
		pokemon1 = null;
		pokemon2 = null;
	}
}
