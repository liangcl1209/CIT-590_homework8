package battleship;

public class BattleshipGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		Ship a = new Battleship();
		a.placeShipAt(5, 5, false, ocean);
		ocean.shootAt(5, 5);
		ocean.shootAt(1, 1);
		ocean.print();
	}

}
