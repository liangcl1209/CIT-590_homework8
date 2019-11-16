package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Shiptest {

	Ship emptySea, submarine, destroyer, battleship, crusier;
	Ocean ocean;
	@BeforeEach
	void setUp() throws Exception {
		 emptySea = new EmptySea();
		 submarine = new Submarine();
		 destroyer = new Destroyer();
		 battleship = new Battleship();
		 crusier = new Cruiser();
		 ocean = new Ocean();
		 emptySea.placeShipAt(0, 0, true, ocean);
		 submarine.placeShipAt(1, 0, true, ocean);
		 destroyer.placeShipAt(2, 1, true, ocean);
		 battleship.placeShipAt(3, 3, true, ocean);
		 crusier.placeShipAt(4, 2, true, ocean);

		 destroyer.setHorizontal(true);
		 crusier.setHorizontal(false);
		 submarine.setHorizontal(true);
		 battleship.setHorizontal(true);

	}


	@Test
	void testGetLength() {
		
		assertEquals(1, emptySea.getLength());
		assertEquals(1, submarine.getLength());
		assertEquals(2, destroyer.getLength());
		assertEquals(3, crusier.getLength());
		assertEquals(4, battleship.getLength());
	}

	@Test
	void testGetBowRow() {
		assertEquals(0, emptySea.getBowRow()); 
		assertEquals(1, submarine.getBowRow());
		assertEquals(2, destroyer.getBowRow());
		assertEquals(3, battleship.getBowRow());
		assertEquals(4, crusier.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		assertEquals(0, emptySea.getBowColumn()); 
		assertEquals(0, submarine.getBowColumn());
		assertEquals(1, destroyer.getBowColumn());
		assertEquals(3, battleship.getBowColumn());
		assertEquals(2, crusier.getBowColumn());
	}

	@Test
	void testGetHit() {
		boolean[] hit = {false,false,false,false};
		assertArrayEquals(hit, emptySea.getHit());
		hit[0] = true;
		assertNotEquals(hit[0], submarine.getHit()[0]); 
	}
	@Test
	void testGetHit2(){
		boolean[] hit = {true,false,false,false};
		battleship.shootAt(3, 3);
		assertArrayEquals(hit, battleship.getHit());
		battleship.shootAt(3, 1);
		boolean[] hit2 = {true,false,true,false};
		assertArrayEquals(hit2, battleship.getHit());
	}
	@Test
	void testIsHorizontal() {
		assertTrue(submarine.isHorizontal());
		assertTrue(battleship.isHorizontal());
		assertFalse(crusier.isHorizontal());
	}

	@Test
	void testSetBowRow() {
		submarine.setBowRow(5);
		assertNotEquals(1,submarine.getBowRow());
		emptySea.setBowRow(78);
		assertEquals(78, emptySea.getBowRow());
		destroyer.setBowRow(6);
		assertEquals(6, destroyer.getBowRow());

	}

	@Test
	void testSetBowColumn() {
		crusier.setBowColumn(5);
		assertNotEquals(1,submarine.getBowColumn());
		emptySea.setBowColumn(4);
		assertEquals(4, emptySea.getBowColumn());
		battleship.setBowColumn(6);
		assertEquals(6, battleship.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		destroyer.setHorizontal(false);
		assertFalse(destroyer.isHorizontal());
		crusier.setHorizontal(true);
		assertTrue(crusier.isHorizontal());
		battleship.setHorizontal(true);
		assertTrue(battleship.isHorizontal());
	}

	@Test
	void testGetShipType() {
		assertSame("empty", emptySea.getShipType());
		assertEquals("Destroyer", destroyer.getShipType());
		assertEquals("Crusier", crusier.getShipType());
		assertEquals("Submarine", submarine.getShipType());
		assertEquals("Battleship", battleship.getShipType());
	}


	@Test
	void testToString() {
		battleship.shootAt(3, 3);
		battleship.shootAt(3, 2);
		battleship.shootAt(3, 1);
		battleship.shootAt(3, 0);
		String bat =  battleship.toString();
		assertEquals("s ", bat);
		assertEquals("x ", crusier.toString());
		assertEquals("- ", emptySea.toString());

	}

}
