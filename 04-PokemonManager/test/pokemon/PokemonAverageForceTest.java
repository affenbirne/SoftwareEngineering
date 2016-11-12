package pokemon;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class PokemonAverageForceTest {

	private Pokemon pokemon0;
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	
	public PokemonAverageForceTest() {
		pokemon0 = new Pokemon("Pikachu", Type.Poison);
		pokemon1 = new Pokemon("Carapuce", Type.Water);
		pokemon2 = new Pokemon("Raupy", Type.Fire);
		Trainer t0 = new Trainer("Peter", "Lustig");
		Trainer t1 = new Trainer("Alisa", "Traurig");
		t0.addPokemon(pokemon0);
		t1.addPokemon(pokemon1);
		t1.addPokemon(pokemon2);
		Competition c0 = new Competition();
		c0.execute(pokemon0, pokemon1);
	}
	
	/**
	 * g_averageForce2.2 test.
	 * @throws Exception
	 */
	@Test
	public void testPokemonAverageForceTypeWaterOneScore() throws Exception {
		System.out.println("\nStart TestCase g_averageForce2.2");
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
		System.out.println("End TestCase g_averageForce2.2\n");
	}
	
	@Test
	public void testPokemonAverageForceTypeFireNoScore() throws Exception {
		System.out.println("\nStart TestCase g_averageForce1.1");
		assertNotNull("Error: Pokemon does not exist!", pokemon2);
		// ensure pokemon has the desired type
		assertEquals("Error: Pokemon has wrong Type!", Type.Fire, pokemon2.getType());
		// ensure pokemon has no competitions
		assertTrue("Error: Pokemon has competitions!", 0 == pokemon2.getCompetitions().size());
		// result should be 0
		assertTrue("Error: Pokemon has too high averageForce!", 0 == pokemon2.averageForce());
		assertEquals("Error: Pokemon has wrong name!", "Raupy", pokemon2.getName());
		System.out.println(pokemon2 + "averageForce:" + pokemon2.averageForce() );
		System.out.println("End TestCase g_averageForce1.1\n");
	}
}
