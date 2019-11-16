package battleship;
import java.util.Scanner;

public class BattleshipGame {

	    /**
     * Displays the given question and prompts for user input using the given scanner.
     * @param question to ask
     * @param scanner to use for user input
     * @return true if the user inputs 'y'
     */
        static boolean getYesOrNoToQuestion(String question, Scanner scanner) {
    	boolean response = true;
    	
    	String answer;

    	System.out.println();
        System.out.print(question + " ");
        
        while(true) {
	        answer = scanner.next();
	        
	        if (answer.toLowerCase().charAt(0) == 'y') {
	        	response = true;
	        	break;
	        } else if (answer.toLowerCase().charAt(0) == 'n') {
	        	response = false;
	        	break;
	        }
        }

        return response;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean continumeGame = true;
		Scanner scanner = new Scanner(System.in);
		int row, column;
		while(continumeGame){
			Ocean ocean = new Ocean();
			ocean.placeAllShipsRandomly();
			ocean.print();
			while(!ocean.isGameOver()){
				
				System.out.println("Please input where you want to fire!");
			/*	
				while(true){
					System.out.println("row, column: ");
					row = scanner.nextInt();
					column = scanner.nextInt();
					if(row > 9 || row < 0){
						System.out.println("please input the row index between 0-9!");
					}else if(column > 9 || column <0){
						System.out.println("please input the column index between 0-9!");
					}else{
						break;
					}
			}
			
			ocean.shootAt(row, column);
			*/

			for(int i =0; i<=9; i++){
				for(int j =0;j<=9;j++){
					ocean.shootAt(i, j);
				}
			}
			System.out.println("You have already fired: " + ocean.getShotsFired() + "times");
			System.out.println("You have hitted: " + ocean.getHitCount()+ "times");
			System.out.println("You have led " + ocean.getShipsSunk() + "ship(s) sunk.");
			ocean.print();	

			}
			System.out.println("You have already fired: " + ocean.getShotsFired() + "times");
			continumeGame = getYesOrNoToQuestion("Do you want to play this again?", scanner);
				
		}
		scanner.close();
	}

}


