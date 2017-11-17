
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
	  
	  public Computer()
	  {
		  
	  }
	  
	  public boolean checkComputerSetup()
	  {
		  return true;
	  }
	  
	  public void setComputer()
	  {
		  
	  }
	  
	  public Computer getComputer()
	  {
		  return null;
	  }
}
