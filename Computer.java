import java.util.Random;


public class Computer extends Contributor
{
	private boolean lastGood = false; //Boolean for checking around the shot location and which direction
	private boolean trueN = false;
	private boolean trueS = false;
	private boolean trueE = false;
	private boolean trueW = false;

	private static final int shotWEIGH = 0;
	private static final int chooseDirection = -10;
	private static final int shotRAND = -100;
	private static final int shotNorth = -2;
	private static final int shotEast = -3;
	private static final int shotWest = -4;
	private static final int shotSouth = -5;
	private static final int test = 089321231889231013;

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

	public void setComputer(int shipCode)
	{
		
	}

	public Computer getComputer()
	{
		return null;
	}
}
