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
		this.hit = new boolean[4];
		for (int n = 0; n <= 3; n++){
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
		
		int lengthOfShip = this.getLength();
		boolean ok = true;

 		if(horizontal){			
			int edgeColumnLeft = column-lengthOfShip + 1;
			int edgeColumnRight = column +1;
			int edgeRowUp = row - 1;
			int edgeRowDown = row + 1; 
			this.checkBoundaries(edgeColumnLeft, edgeColumnRight, edgeRowDown, edgeRowUp);
			if(column - lengthOfShip  < -1){
				return false;
			}else{				
				for(int n = edgeColumnRight; n> edgeColumnLeft; n--){
					boolean BC1 = ocean.isOccupied(edgeRowUp, n);
					boolean BC2 = ocean.isOccupied(edgeRowDown, n);
					boolean BC3 = ocean.isOccupied(row, n);
					if(!BC1 || !BC2 || !BC3){
						return false;
					}
				}
				return ok;
			}
		}else{
			if(row - lengthOfShip < -1){
				return false;
			}else{
				int edgeColumnLeft = column - 1;
				int edgeColumnRight = column +1;
				int edgeRowDown = row + 1;
				int edgeRowUp = row - lengthOfShip + 1;
				this.checkBoundaries(edgeColumnLeft, edgeColumnRight, edgeRowDown, edgeRowUp);
				for(int i = edgeRowUp; i < edgeRowDown; i++){
					boolean BC1 = ocean.isOccupied(i, edgeColumnLeft);
					boolean BC2 = ocean.isOccupied(i, edgeColumnRight);
					boolean BC3 = ocean.isOccupied(i, column);
					if(!BC1 || !BC2 || !BC3){
						return false;
						}
					}
					return ok;
				}
			}		
		}
	

/**
 * Check the boundaries of ship, if the ship is at the boundary, then set the column and row which are out of the matrix back to edge.
 * @param edgeColumnLeft
 * @param edgeColumnRight
 * @param edgeRowDown
 * @param edgeRowUp
 */
	void checkBoundaries(int edgeColumnLeft, int edgeColumnRight, int edgeRowDown, int edgeRowUp){
		if(edgeColumnLeft < 0) edgeColumnLeft = 0;
		if(edgeRowDown > 9) edgeRowDown = 9;
		if(edgeRowUp < 0) edgeRowUp = 0;
		if(edgeColumnRight > 9) edgeColumnRight = 9;
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
		this.setBowColumn(column);
		this.setBowRow(row);
		if(!horizontal){
			for(int i = row; i>=row-this.length+1 ; i--){
				ocean.getShipArray()[i][column] = this;
			}
		}else{
			for(int j = column; j>= column-this.length+1;j--){
				ocean.getShipArray()[row][j] = this;
			}
		}

	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as “hit”
	 * @param row
	 * @param column
	 * @return true after mark "hit"; otherwise return false
	 */
	boolean shootAt(int row, int column){
		boolean checkIsSunk = this.isSunk();
		if(checkIsSunk){
			return false;
		}else{
			int bowColumn = this.getBowColumn();
			int bowRow = this.getBowRow();
			int position;
			if(horizontal){
				position = bowColumn - column;
			}else{
				position = bowRow - row;
			}

			hit[position] = true; 
			return true;
		}
		
	}

	/**
	 * Return true if every part of the ship has been hit, false otherwise.
	 * @return if every part of the ship has been hit
	 */
	boolean isSunk(){
		boolean[] hit;
		hit = getHit();
		int hits = 0;
		for (int i = 0; i< 4;i++){
			if(hit[i] == true){
				hits += 1;
			}

		}
		if(hits == this.getLength()){
			
			return true;
		}else{
			return false;
		}
	
	}


	/**
	 * Returns a single-character String to use in the Ocean’s print method. 
	 * This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at. Since toString behaves exactly the same for all ship types.
	 */
	@Override 
	public String toString(){
		boolean sunk = this.isSunk();
		//System.out.println(sunk);
		if (sunk){
			return "s ";
		}else {
			return "x ";
		}
	}
}
