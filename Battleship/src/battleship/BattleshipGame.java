package battleship;

public class BattleshipGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		Ship a = new Battleship();
		a.placeShipAt(5, 5, false, ocean);
		boolean b = ocean.shootAt(5, 5);
		int c = ocean.getHitCount();
		System.out.println(c);
		System.out.println(b);
		for(int n = 0;n<4;n++){
			System.out.print(a.getHit()[n] + " ");
		}
		System.out.println(ocean.isOccupied(1, 0));
		System.out.println(a.isSunk());
		ocean.print();
	}

}
