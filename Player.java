import java.util.Scanner;
import java.util.*;


public class Player extends Contributor
{

	public Player() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int[] getShot()
	{
	    System.out.println("Enter your coordinates...");

	    Scanner keyboard = new Scanner(System.in);
	    System.out.println(" Horizontal (A-J):");
	    shot[1] = Char2Int(keyboard.next().charAt(0));

	    //Exception for if Vertical is not a number,
	    //or if Direction is not a letter.
	    try {
	      System.out.println(" Vertical (1-10):");
	      shot[0] = keyboard.nextInt() - 1;

	      System.out.println(" Direction of ship (N,S,E,W):");
	      setDirection(keyboard.next().charAt(0));
	    }
	    //Calls method again to try again if input is wrong.
	    catch (InputMismatchException e) {
	      System.out.println("Invalid input - try again.");
	      getShot();
	    }
	    return shot;
	}

}
