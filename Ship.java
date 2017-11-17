import java.util.Random;


public class Ship 
{
	private char Direction;
	private int[] coord = new int[2];
	private int SHIPCODE;
	private final static int CARRIER = 5;
	private final static int BATTLESHIP = 6;
	private final static int CRUISER = 7;
	private final static int SUBMARINE = 8;
	private final static int DESTROYER = 9;
	
	public int getShipSize(int shipCode)
	{
		int shipSize = 0;
	    if (shipCode == CARRIER) {
	        shipSize = 5;
	      } else if (shipCode == BATTLESHIP) {
	        shipSize = 4;
	      } else if (shipCode == CRUISER) {
	        shipSize = 3;
	      } else if (shipCode == SUBMARINE) {
	        shipSize = 3;
	      } else if (shipCode == DESTROYER) {
	        shipSize = 2;
	      }
	      return shipSize;	
	}
	
	public Ship getShip()
	{
		return null;
	}
	
	public void setShip(int shipCode)
	{

	}
	
	public boolean isShipDestroyed()
	{
		return true;
	}

}
