import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

/*
*/

public class Computer extends Player
{
	/*
	* Direction booleans, used as switches to determine the nature and direction of the Computer's shots
	*/
	private boolean lastGood = false;
	public boolean trueN = false;
	public boolean trueS = false;
	public boolean trueE = false;
	public boolean trueW = false;
	private boolean oldMove;

	public boolean feedbackHit = false;
	public int aiDiff = 3;

	private Random rand = new Random();
	private int[] boardChoice = new int[] {0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9};
	private ArrayList<Point> shotStore = new ArrayList<Point>(); //Storing of shots
	private int shotType = 0;
	private int x = 0;
	private int y = 0;

	static final int easy = 1;
	static final int medium = 2;
	static final int weighted = 0;
	static final int chooseDirection = -10;
	static final int shotNorth = -2;
	static final int shotEast = -3;
	static final int shotWest = -4;
	static final int shotSouth = -5;

	/**
	 * Default constructor, calls the default constructor in the Player Class
	 */
	public Computer()
	{
		super();
	}

	public boolean checkComputerSetup(int[][] boardToCheck, int row,
	int col, int shipLength, int vertOrHor)
	{
		boolean emptySpace = true;
		//Cycles through the array for the length of shipLength
		for (int sLength = 0; sLength < shipLength; sLength++)
		{
			//0 = space is empty
			if (boardToCheck[col][row] != 0)
			emptySpace = false;
			else
			emptySpace = emptySpace && true;
			//Alters what spaces to check, depending on ship placement.
			if (vertOrHor == 0) //Vertical checking
			col++;
			else //Horizontal checking
			row++;
		}
		return emptySpace;
	}

	/*
	* An overridden setShot of Player
	* Represents the standard difficulty of the Computer
	* While-loop implementation
	* Will adjust x and y based on boolean input
	* Contains multiple private methods for readability
	*/
	public void setShot(Boolean shotFeedback) {
		shotChoice(shotFeedback);
		oldMove = false;
		while (shotStore.contains(new Point(x,y))) {
			if (shotType == weighted) {
				x = boardChoice[rand.nextInt(boardChoice.length)]; //X
				y = boardChoice[rand.nextInt(boardChoice.length)]; //Y
			}

			else if (shotType == shotNorth) {
				checkNorth();
				oldMoveCheck();
				oldMove = true;
			}

			else if (shotType == shotSouth) {
				checkSouth();
				oldMoveCheck();
				oldMove = true;
			}

			else if (shotType == shotEast) {
				checkEast();
				oldMoveCheck();
				oldMove = true;
			}

			else if (shotType == shotWest) {
				checkWest();
				oldMoveCheck();
				oldMove = true;
			}
			System.out.println("WhileLoop");
			System.out.println("x: " + x + " y: " + y);

		}
		outofBounds();
		shotStore.add(new Point(x,y));
		shot[1] = x;
		shot[0] = y;
		oldMove = false;
		System.out.println(x +" "+ y);
		System.out.println(shotType);
		System.out.println("shotFeedback: " +shotFeedback);
		System.out.println("lastGood: " + lastGood);

	/*
	 * randomShot class represents an 'easy' Computer to play against
	 * Coordinates will be purely pseudo-randomly
	 */
	}
	public void randomShot() {
		while (shotStore.contains(new Point(x,y))) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
		}
		shot[1] = x;
		shot[0] = y;
	}

	/*
	 * Private method to improve readability of setShot method
	 * Receives the input of setShot, will assign a method to obtaining the next coordinates
	 */
	private void shotChoice(Boolean shotFeedback) {
		if (!shotFeedback) {
			lastGood = false;
			trueN = false;
			trueE = false;
			trueW = false;
			trueS = false;
			shotType = weighted;
		}

		else if (shotFeedback && lastGood == false) {
			int tempDir = rand.nextInt(4);
			shotType = -(tempDir + 2);
			if (shotType == shotNorth) {
				trueN = true;
			}
			else if (shotType == shotEast) {
				trueE = true;
			}
			else if (shotType == shotWest) {
				trueW = true;
			}
			else if (shotType == shotSouth) {
				trueS = true;
			}
			lastGood = true;
		}

		else if (shotFeedback && lastGood == true) {
			//Shot-type should not change
			if (trueN) {
				shotType = shotNorth;
			}
			else if (trueS) {
				shotType = shotSouth;
			}
			else if (trueW) {
				shotType = shotWest;
			}
			else if (trueE) {
				shotType = shotEast;
			}
		}
		else {
			lastGood = false;
			trueN = false;
			trueE = false;
			trueW = false;
			trueS = false;
			shotType = weighted;
			System.out.println("Else shotChoice");
		}
	}
	/*
	 *	Private method that will catch out of bound selections in setShot
	 */
	private void outofBounds() {
		shotStore.add(new Point(x,y));
		if (x > 9 || x < 0) {
			x = 5;
		}
		if (y > 9 || y < 0) {
			y = 5;
		}
		shotType = weighted;
	}

	/*
	 * Private method that will prevent infinite loops in setShot
	 */

	private void oldMoveCheck() {
		if (oldMove) {
			shotType = weighted;
			trueN = false;
			trueS = false;
			trueE = false;
			trueW = false;
		}
	}

	/*
	 * Private method to improve readability of setShot method
	 * Will adjust vertical coordinate to represent an up-direction
	 */
	private void checkNorth() {
		y--;
		if (y<0) {
			y = y + 2;
			trueN = false;
			trueS = true;
			shotType = shotSouth;
		}
	}

	/**
	 * Private method to improve readability of setShot method
	 * Will adjust vertical coordinate to represent a down-direction
	 */
	private void checkSouth() {
		y++;
		if (y>9) {
			y = y - 2;
			trueS = false;
			trueN = true;
			shotType = shotNorth;
		}
	}

	/**
	 * Private method to improve readability of setShot method
	 * Will adjust horizontal coordinate to represent a right-direction
	 */
	private void checkEast() {
		x++;
		if (x>9) {
			x = x - 2;
			trueE = false;
			trueW = true;
			shotType = shotWest;
		}
	}

	/**
	 * Private method to improve readability of setShot method
	 * Will adjust horizontal coordinate to represent a left-direction
	 */
	private void checkWest() {
		x--;
		if (x>0) {
			x = x + 2;
			trueW = false;
			trueE = true;
			shotType = shotEast;
		}
	}

	public void setFeedback(boolean shipHit) {
		feedbackHit = shipHit;
	}


	public boolean getGood() {
		return lastGood;
	}

	public void setAI(int AI)
	{
		aiDiff = AI;
	}

}
