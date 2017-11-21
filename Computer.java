import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;


public class Computer extends Contributor
{
	/*
	 * The first set of booleans are used to determine shot direction and will direct the computer to fire across a line from their original shot.
	 */
	private boolean lastGood = false; //Boolean for checking around the shot location and which direction
	//private boolean sweepShot = false; // Boolean for checking surrounding tiles, currently unused, lack of input
	public boolean trueN = false;
	public boolean trueS = false;
	public boolean trueE = false;
	public boolean trueW = false;
	
	 //Might have the button click this boolean
	 //Might have it as an int and have the hard-medium-easy click it for a number
	 //Will then have additional parameters for the if-statement in the logic
	 //public boolean stupidAI = false;
	public int aiDiff = 1;
	private static final int easy = 1;
	private static final int medium = 2;
	private static final int hard = 3;
	
	/*
	 *
	 */
	
	public ArrayList<Point> shotStore = new ArrayList<Point>(); //Storing of shots
	private int shotType = 0;
	public int x = 0;
	public int y = 0;
	private static final int weighted = 0;
	private static final int chooseDirection = -10;
	private static final int shotRAND = -100;
	private static final int shotNorth = -2;
	private static final int shotEast = -3;
	private static final int shotWest = -4;
	private static final int shotSouth = -5;
	

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

	public void setShot(){
		Random rand = new Random();
		shot[0]=rand.nextInt(10);
		shot[1]=rand.nextInt(10);
	}

	/*
	 * An overridden setShot of Contributor
	 * Two if-else blocks
	 * The first block determines the nature and shot selection method, based on if the current previous shot(s) had landed.
	 * The second block are the shot selection protocols. 
	 */
	//@Override
	public void setShot(Boolean shotHit) {
		Random rand = new Random();
		if (!shotHit) {
			lastGood = false;
			shotType = weighted;
		}
		else if (shotHit && !lastGood && aiDiff == hard) {
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
		else if (shotHit && lastGood) {
			shotType = shotType;
		}
		else {
			shotType = weighted; //If I messed anything up
		}
		
		if (aiDiff == easy) {
			int x = rand.nextInt(10);
			int y = rand.nextInt(10);
		}
		
		else if (shotType == weighted && aiDiff == medium) {
		      int[] boardChoice = new int[] {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9};
		      x = boardChoice[rand.nextInt(boardChoice.length)]; //X
		      y = boardChoice[rand.nextInt(boardChoice.length)]; //Y
		}
		
		else if (shotType == shotNorth) {
			x = getShot()[1];
			y = getShot()[0]-1;
			if (y<0) {
				//sweepShot = false;
				//lastGood = false;
				setShot(shotHit);
			}
		}
		
		else if (shotType == shotEast) {
			x = getShot()[1]+1;
			y = getShot()[0];
			// x>9 must be replaced
			if (x>9) {
				//sweepShot = false;
				//lastGood = false;
				setShot(shotHit);
			}
		}
		
		else if (shotType == shotWest) {
			x = getShot()[1]-1;
			y = getShot()[0];
			if (x<0) {
				//sweepShot = false;
				//lastGood = false;
				setShot(shotHit);
			}
		}
		
		else if (shotType == shotSouth) {
			x = getShot()[1];
			y = getShot()[0]+1;
			// y>9 must be replaced
			if (y>9) {
				//sweepShot = false;
				//lastGood = false;
				setShot(shotHit);
			}
		}
		
		Point temp = new Point(x,y);
		if (shotStore.contains(temp)) {
			//sweepShot = false;
			//lastGood = false;
			setShot(shotHit);
		}
		shotStore.add(new Point(x,y));
		//
		shot[1] = x;
		shot[0] = y;
	}
	
	/*
	public void setX(int set) {
		x = set;
	}
	public void setY(int set) {
		y = set;
	}
	*/
	
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
