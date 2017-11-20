import java.util.Scanner;

public class GamePlay {
	int MAXROW = 10;
	int MAXCOL = 10;
	int shipSize;
	// Carrier – 5 squares - shipCode=5;
	// Battleship – 4 squares- shipCode=6;
	// Cruiser – 3 squares- shipCode=7;
	// Submarine – 3 squares- shipCode=8;
	// Destroyer – 2 squares- shipCode=9;

	public Player setPlayer() {
		//gets user input to pick positions on the board
		//checks that none of the ships overlap
		//return player
		Player p = new Player();
		int maxship = p.getBoard().getShipList().size();
		int boardTotal=0;

		for (int i = 0; i < maxship; i++) 
		{
			System.out.println("This is your " + (i+1) + " boat out of " + maxship + ".");
			Ship boat = p.getBoard().getShipList().get(i);
			int shipCode = boat.getShipCode();
			int shipSize = boat.getShipSize();
			boardTotal = boardTotal + (shipCode * shipSize);
			p.getBoard().placeShips(boardTotal, boat, p.getBoard());

			System.out.println("***" + boardTotal);
			System.out.println("BT: " + boardTotal + " SC " + shipCode + " SS " + shipSize);
			
			System.out.println("!!!" + boardTotal);

			p.getBoard().printBoard();
		}
		return p;
	}

	public Computer setComputer()
	{
		Computer c = new Computer();
		c.getBoard().placeComputerShip();
		return c;
	}

	public static void main(String args[]) {
		GamePlay game = new GamePlay();
		Display screen = new Display();

		Player p1 = game.setPlayer();
		Board playerBoard = p1.getBoard();
		screen.showPlayerBoard(playerBoard.getBoard());
		Computer c1 = game.setComputer();
		Board computerBoard = c1.getBoard();
		screen.showComputerBoard(computerBoard.getBoard());

		boolean endGame = p1.getEndGame();

		while (!endGame) {
			p1.setShot(p1.getShot());

			//Gets player’s move
			boolean playerHit = (p1.HitOrMiss()); //Check if hit or a miss, updates c1 board and p1.win
			if (playerHit)
			{
				System.out.println("You hit!");
				p1.setWin(p1.winCheck());
			}
			else
			{
				System.out.println("You missed!");
			}
			//screen.showPlayerBoard(p1.board); //// COMMENT LATER

			c1.setShot(true); //Gets computer’s move
			boolean compHit = (c1.HitOrMiss()); //Check if hit or miss, updates p1 board and c1.win
			if (compHit)
			{
				System.out.println("Computer hit!");
				c1.setWin(c1.winCheck());
			}
			else
			{
				System.out.println("Computer missed!");
			}
			System.out.println("P1: ");
			screen.showPlayerBoard(playerBoard.getBoard());
			System.out.println("C1: ");
			screen.showComputerBoard(computerBoard.getBoard());
			//screen.showPlayerBoard(p1.board); Troubleshooting;
			// System.out.println("COMP");
			// c1.printBoard(c1.board);
			//Screen.displayGameProgress(c1,p1); //Display turns, player’s health etc.

			//endGame = game.winCheck(c1,p1); // Check if game is over
		}

		//display game result messages
		if (p1.getWin() && !c1.getWin()) {
			System.out.println("You win!");
		} else if (!p1.getWin() && !c1.getWin()) {
			System.out.println("Computer wins! You lose!");
		} else if (p1.getWin() && c1.getWin()) {
			System.out.println("It's a tie!");
		}
		System.out.println("GAME OVER");
	}
}
