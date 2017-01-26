package model;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TileTest {
	
	private Tile aTile;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		aTile = new Tile(1, 1, "Fire", true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(aTile.getElement() == "Fire");
	}
	
	@Test
	public void testSpecial(){
		assertTrue(aTile.isSpecial() == true);
	}

}
