import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;


public class Computer extends Contributor
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
	public int aiDiff = 3;
	static final int easy = 1;
	static final int medium = 2;
	static final int hard = 3;
	Random rand = new Random();
	
	/*
	 *
	 */
	
	public ArrayList<Point> shotStore = new ArrayList<Point>(); //Storing of shots
	int shotType = 0;
	public int x = 0;
	public int y = 0;
	static final int weighted = 0;
	static final int chooseDirection = -10;
	static final int shotRAND = -100;
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
	public void setShot(Boolean shotHit) {
		//System.out.println(shotHit);
		if (!shotHit) {
			lastGood = false;
			shotType = weighted;
		}
		else if (shotHit && lastGood==false) {
		    int tempDir = rand.nextInt(4);
		    shotType = -(tempDir + 2); //Can be re-implemented as a random number from an array, current chooses between -2 and -5
		    
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
		}
		else if (shotHit && lastGood==true) {
			shotType = shotType;
			lastGood = true;
		}
		else {
			shotType = weighted; //If I messed anything up
			lastGood = true;
		}
		

		if (aiDiff == easy) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			System.out.println("easy");
		}
		
		else if (shotType == weighted) {
		      int[] boardChoice = new int[] {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9};
		      x = boardChoice[rand.nextInt(boardChoice.length)]; //X
		      y = boardChoice[rand.nextInt(boardChoice.length)]; //Y
		      System.out.println("Weighted");
		}
		
		else if (shotType == shotNorth) {
			x = getShot()[1];
			y = getShot()[0]-1;
			if (y<0) {
				trueN = false;
				System.out.println("north");
				if (shotHistory(x,y)) {
				shotType = weighted;
				}
				else { setShot(shotHit); }
			}

		}
		
		else if (shotType == shotEast) {
			x = getShot()[1]+1;
			y = getShot()[0];
			// x>9 must be replaced
			if (x>9) {
				trueE = false;
				System.out.println("east");
				if (shotHistory(x,y)) {
				shotType = weighted;
				}
				else { setShot(shotHit); }
			}
		}
		
		else if (shotType == shotWest) {
			x = getShot()[1]-1;
			y = getShot()[0];
			if (x<0) {
				trueW = false;
				System.out.println("west");
				if (shotHistory(x,y)) {
				shotType = weighted;
				}
				else { setShot(shotHit); }
			}
		}
		
		else if (shotType == shotSouth) {
			x = getShot()[1];
			y = getShot()[0]+1;
			// y>9 must be replaced
			if (y>9) {
				trueS = false;
				System.out.println("south");
				if (shotHistory(x,y)) {
				shotType = weighted;
				}
				else { setShot(shotHit); }
			}
		}
		
		Point temp = new Point(x,y);
		if (shotHistory(x,y)) {
			trueN = false;
			trueE = false;
			trueW = false;
			trueS = false;
			lastGood = false;
			System.out.println("storage");
			shotHit = false;
			shotType = weighted;
			setShot(shotHit);
			}
		else {
			shotStore.add(temp);
			shot[1] = x;
			shot[0] = y;
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
	
	public boolean shotHistory(int x, int y) {
		Point temp1 = new Point(x,y);
		boolean tempbool = false;
		if (shotStore.contains(temp1)) {
			trueN = false;
			trueE = false;
			trueW = false;
			trueS = false;
			tempbool = true;
		}
		return tempbool;
		}
	
	public void setComputer(int shipCode)
	{
		
	}
	
	public boolean getGood() {
		return lastGood;
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
