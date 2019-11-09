package battleship;

public abstract class Ship {
	
	/**
	 * The abstract Ship class has the instance variables below
	 */

	 //The row that contains the bow (front part of the ship)
	private int bowRow;

	//The column that contains the bow (front part of the ship)
	private int bowColumn;

	//The length of the ship
	private int length;

	//A boolean that represents whether the ship is going to be placed horizontally or vertically
	private boolean horizontal;

	//An array of booleans that indicate whether that part of the ship has been hit or not
	private boolean[] hit;

	/**
	 * This constructor sets the length property of the particular ship and initializes the hit array
	 * @param length length of the ship
	 */
	public Ship(int length){
		this.length = length;
		this.hit = new boolean[length];
		for (int n = 0; n <= length-1; n++){
			hit[n] = false;
		}
	}

	/**
	 * @return the ship length
	 */
	public int getLength(){
		return this.length;
	}

	/**
	 * 
	 * @return the row corresponding to the position of the bow
	 */
	public int getBowRow(){
		return this.bowRow;

	}

	/**
	 * 
	 * @return the bow column location
	 */
	public int getBowColumn(){
		return this.bowColumn;
	}

	/**
	 * 
	 * @return the hit array
	 */
	public boolean[] getHit(){
		return this.hit;
	}

	/**
	 * 
	 * @return whether the ship is horizontal or not
	 */
	public boolean isHorizontal(){
		return this.horizontal;
	}

	/**
	 * Sets the value of bowRow
	 * @param row the value of bowRow
	 */
	public void setBowRow(int row){
		this.bowRow = row;
	}

	/**
	 * Sets the value of bowColumn
	 * @param column the value of bowColumn
	 */
	public void setBowColumn(int column){
		this.bowColumn = column;
	}

	/**
	 * Sets the value of the instance variable horizontal
	 * @param horizontal horizontal
	 */
	public void setHorizontal( boolean horizontal){
		this.horizontal = horizontal;
	}

	/**
	 * Returns the type of ship as a String. 
	 * Every specific type of Ship (e.g. BattleShip, Cruiser, etc.) has to override and implement this method and return the corresponding ship type.
	 * @return the type of ship
	 */
	public abstract String getShipType();

	/**
	 * Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location; false otherwise.
	 * The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally), and it must not ”stick out” beyond the array. 
	 * Does not actually change either the ship or the Ocean 
	 * it just says if it is legal to do so.
	 * @param row row of ship
	 * @param column column of ship
	 * @param horizontal the orintation of ship
	 * @param ocean 
	 * @return if it is legal to place ship
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){

		return false;

	}

	/**
	 * “Puts” the ship in the ocean. 
	 * This involves giving values to the bowRow, bowColumn, 
	 * and horizontal instance variables in the ship, 
	 * and it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object. 
	 * (Note: This will be as many as four identical references; you can’t refer to a ”part” of a ship, only to the whole ship.)
	 * horizontal ships face East
	 * vertical ships face South
	 * @param row bowRow
	 * @param column bowColumn
	 * @param horizontal horizontal instance variables
	 * @param ocean Ocean object
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){

	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as “hit”
	 * @param row
	 * @param column
	 * @return true after mark "hit"; otherwise return false
	 */
	boolean shootAt(int row, int column){

		return true;
	}

	/**
	 * Return true if every part of the ship has been hit, false otherwise.
	 * @return if every part of the ship has been hit
	 */
	boolean isSunk(){
		boolean[] hit;
		hit = getHit();
		for (boolean item : hit){
			if(item == false){
				return false;
			}
		}
		return true;
	}


	/**
	 * Returns a single-character String to use in the Ocean’s print method. 
	 * This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at. Since toString behaves exactly the same for all ship types.
	 */
	@Override 
	public String toString(){
		boolean sunk = isSunk();
		if (sunk == true){
			return "s";
		}else {
			return "x";
		}
	}
}
