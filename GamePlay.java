import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePlay {
	
	int MAXROW = 10;
	int MAXCOL = 10;
	int shipSize;
	char direction;

	// Carrier  5 squares - shipCode=5;
	// Battleship  4 squares- shipCode=6;
	// Cruiser  3 squares- shipCode=7;
	// Submarine  3 squares- shipCode=8;
	// Destroyer  2 squares- shipCode=9;

	public Human setPlayer() {
		
		//gets user input to pick positions on the board
		//checks that none of the ships overlap
		
		Human p = new Human();
		int maxship = p.getBoard().getShipList().size();
		int boardTotal = 0;

		for (int i = 0; i < maxship; i++) {
			System.out.println("This is your " + (i + 1) + " boat out of " + maxship + ".");
			Ship boat = p.getBoard().getShipList().get(i);
			int shipCode = boat.getShipCode();
			int shipSize = boat.getShipSize();

			boardTotal = boardTotal + (shipCode * shipSize);
			p.setShot(getInputForShot());
			p.setDirection(getInputForDirection());
			setPlayerShips(p, shipCode, boat, boardTotal);
		}
		return p;
	}

	public void setPlayerShips(Human p, int shipCode, Ship boat, int boardTotal) {
		
		if (p.getBoard().checkDirection(shipCode, p.getDirection())) {
			p.getBoard().setBoard(boat, p.getShot());
			p.getBoard().checkBoard(boardTotal);
//			System.out.println(boardTotal);
			
			if (!p.getBoard().getBoardOK()) {
				p.getBoard().copyBoard(p.getBoard().getOldBoard());
				System.out.println("Please select a valid position on the board. Note that you cannot place a ship ontop of another.");
				System.out.println();
				p.setShot(getInputForShot());
				p.setDirection(getInputForDirection());
				setPlayerShips(p, shipCode, boat, boardTotal);
			} 
			else {
				p.getBoard().setOldBoard(p.getBoard().getBoard());
			}
		} 
		
		else {
			System.out.println("Direction is out of bounds.");
			System.out.println("Please select again.");
			p.getBoard().copyBoard(p.getBoard().getOldBoard());
			p.setShot(getInputForShot());
			p.setDirection(getInputForDirection());
			setPlayerShips(p, shipCode, boat, boardTotal);
		}
		p.getBoard().printBoard();
	}


	//Creates Computer's board and sets up AI.
	public Computer setComputer(){
		Computer c = new Computer();
		c.getBoard().placeComputerShip();
		c.setAIText();
		return c;
	}

	public int[] getInputForShot(){
		int[] shot = new int[2];
		System.out.println("Enter your coordinates...");
		Scanner keyboard = new Scanner(System.in);
		System.out.println(" Horizontal (A-J):");
		shot[1] = Char2Int(keyboard.next().charAt(0));

		//Exception for if Vertical is not a number,
		//or if Direction is not a letter.
		try {
			System.out.println(" Vertical (1-10):");
			shot[0] = keyboard.nextInt() - 1;

		} catch (InputMismatchException e) {
			System.out.println("Invalid input - try again.");
			shot = getInputForShot();
		}
		return shot;
	}

	//Converts the letters for row of ship placement into int.
	public int Char2Int(char row){
		int num = 0;
		
		if (row == 'A' || row == 'a') {
			num = 0;
		} else if (row == 'B' || row == 'b') {
			num = 1;
		} else if (row == 'C' || row == 'c') {
			num = 2;
		} else if (row == 'D' || row == 'd') {
			num = 3;
		} else if (row == 'E' || row == 'e') {
			num = 4;
		} else if (row == 'F' || row == 'f') {
			num = 5;
		} else if (row == 'G' || row == 'g') {
			num = 6;
		} else if (row == 'H' || row == 'h') {
			num = 7;
		} else if (row == 'I' || row == 'i') {
			num = 8;
		} else if (row == 'J' || row == 'j') {
			num = 9;
		}
		return num;
	}

	public char getInputForDirection(){ ;
					   
		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.println(" Direction of ship (N,S,E,W):");
			direction= (keyboard.next().charAt(0));
		}
		//calls method again to try again if input is wrong.
		catch (InputMismatchException e) {
			System.out.println("Invalid input - try again.");
			getInputForDirection();
		}
		return direction;
	}

	public static void main(String args[]) {
		
		GamePlay game = new GamePlay();
		Display screen = new Display();

		//sets up Human's board
		Human p1 = game.setPlayer();
		Board playerBoard = p1.getBoard();
		screen.showPlayerBoard(playerBoard.getBoard());

		//sets up Computer's board
		Computer c1 = game.setComputer();
		Board computerBoard = c1.getBoard();
		screen.showComputerBoard(computerBoard.getBoard());
		boolean compHit = false;

		while (!p1.getWin() && !c1.getWin())
		{
			//gets player's move
			p1.setWin(c1.lossCheck());
			p1.setShot(game.getInputForShot());
			boolean playerHit = c1.HitOrMiss(p1.getShot(),c1); //checks if hit or miss, updates c1 board and p1.win
			
			if (playerHit){
				System.out.println("You hit!");
				//c1.setWin(c1.lossCheck());
			}
			
			else{
				System.out.println("You missed!");
			}
			
			//updates the computer's board based on the user's input.
			c1.getBoard().setBoard(playerHit,p1.getBoard(),p1.getShot());
			c1.setWin(p1.lossCheck());
			c1.setShot(compHit); //gets computer's move
			compHit = p1.HitOrMiss(c1.getShot(),p1);  //checks if hit or miss, updates p1 board and c1.win
			
			if (compHit){
				System.out.println("Computer hit!");
				//c1.setWin(p1.lossCheck());
			}
			
			else{
				System.out.println("Computer missed!");
			}
			
			p1.getBoard().setBoard(compHit,c1.getBoard(),c1.getShot());
			System.out.println("P1: ");
			screen.showPlayerBoard(playerBoard.getBoard());
			System.out.println("C1: ");
			screen.showComputerBoard(computerBoard.getBoard());

			//To be extra sure: setting win conditions again.
			p1.setWin(c1.lossCheck());
			c1.setWin(p1.lossCheck());
		}

		//displays game result messages
		if (p1.getWin() && !c1.getWin()) {
			System.out.println("You win!");
		} 
		else if (!p1.getWin() && !c1.getWin()) {
			System.out.println("Computer wins! You lose!");
		} 
		else if (p1.getWin() && c1.getWin()) {
			System.out.println("It's a tie!");
		}
		else {
			// System.out.println("BLARGH!");
		}
		
		System.out.println("GAME OVER");
	}
}
