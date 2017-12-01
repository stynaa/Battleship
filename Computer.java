import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;


public class Computer extends Player
{
	/*
	 * The first set of booleans are used to determine shot direction and will direct the computer to fire across a line from their original shot.
	 */
	boolean lastGood = false; //Boolean for checking around the shot location and which direction
	//private boolean sweepShot = false; // Boolean for checking surrounding tiles, currently unused, lack of input
	public boolean trueN = false;
	public boolean trueS = false;
	public boolean trueE = false;
	public boolean trueW = false;
	boolean feedbackHit = false;

	 //Might have the button click this boolean
	 //Might have it as an int and have the hard-medium-easy click it for a number
	 //Will then have additional parameters for the if-statement in the logic
	 //public boolean stupidAI = false;
	//public int aiDiff = 3;
	//static final int easy = 1;
	//static final int medium = 2;
	//static final int hard = 3;
	Random rand = new Random();

	/*
	 *
	 */

	public ArrayList<Point> shotStore = new ArrayList<Point>(); //Storing of shots
	int shotType = 0;
	public int x = 0;
	public int y = 0;
	static final int weighted = 0;
	static final int shotNorth = -2;
	static final int shotEast = -3;
	static final int shotWest = -4;
	static final int shotSouth = -5;


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
	 * An overridden setShot of Contributor
	 * Two if-else blocks
	 * The first block determines the nature and shot selection method, based on if the current previous shot(s) had landed.
	 * The second block are the shot selection protocols. 
	 */
	//@Override
	public void setShot(Boolean shotFeedback) {
		shotChoice(shotFeedback);
		boolean oldMove = false;
		int[] boardChoice = new int[] {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9};
		while (shotStore.contains(new Point(x,y))) {
			if (shotType == weighted) {
		      x = boardChoice[rand.nextInt(boardChoice.length)]; //X
		      y = boardChoice[rand.nextInt(boardChoice.length)]; //Y
		      oldMove = false;
			}

			else if (shotType == shotNorth) {
				y--;
				if (y<0) {
					y = y + 2;
					trueN = false;
					trueS = true;
					shotType = shotSouth;
				}

				else if (oldMove = true) {
					shotType = weighted;
 					System.out.println("NorthOld");

				}
				oldMove = true;
			}

			else if (shotType == shotSouth) {
				y++;
				if (y>9) {
					y = y - 2;
					trueS = false;
					trueN = true;
					shotType = shotNorth;
				}
				else if (oldMove = true) {
					shotType = weighted;
					System.out.println("SouthOld");
				oldMove = true;
				}
			}

			else if (shotType == shotEast) {
				x++;
				if (x>9) {
					x = x - 2;
					trueE = false;
					trueW = true;
					shotType = shotWest;
				}
				else if (oldMove = true) {
					shotType = weighted;
					System.out.println("EastOld");
				oldMove = true;
				}
			}

			else if (shotType == shotWest) {
				x--;
				if (x>0) {
					x = x + 2;
					trueW = false;
					trueE = true;
					shotType = shotEast;
				}
				else if (oldMove = true) {
					shotType = weighted;
					System.out.println("WestOld");
				oldMove = true;
				}
			}
			System.out.println("WhileLoop");
		}
			shotStore.add(new Point(x,y));
			shot[1] = x;
			shot[0] = y;
			System.out.println(x +" "+ y);
			System.out.println(shotType);
			System.out.println("shotFeedback: " +shotFeedback);
			System.out.println("lastGood: " + lastGood);

	}
	public void randomShot() {
		while (shotStore.contains(new Point(x,y))) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
		}
		shot[1] = x;
		shot[0] = y;
	}

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
	public void setX(int set) {
		x = set;
	}
	public void setY(int set) {
		y = set;
	}
	*/

	public void setFeedback(boolean shipHit) {
		feedbackHit = shipHit;
	}


	public void setComputer(int shipCode)
	{

	}

	public boolean getGood() {
		return lastGood;
	}

	public void setAI(int AI)
	{
		aiDiff = AI;
	}

	//Used only for the text version.
	public void setAIText()
	{
		System.out.println("Select AI Difficulty:");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");
		Scanner kb = new Scanner(System.in);
		int AI = kb.nextInt();
		if (AI == 1 || AI == 2 || AI == 3)
		{
			setAI(AI);
		}
		else
		{
			System.out.println("Please only enter 1, 2, or 3.");
			setAIText();
		}
	}


	//public Computer getComputer()
	//{
		/*
		 * Will return a reference of computer
		 * This makes no sense to me
		 */
		//return Computer; //Desired(?) privacy leak
	//}
}
