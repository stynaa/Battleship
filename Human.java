import java.util.Scanner;
import java.util.*;


public class Human extends Player
{

	public Human()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Human(Human toCopy){
		super(toCopy);
	}

	public void setDirection(){
		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.println(" Direction of ship (N,S,E,W):");
			super.setDirection(keyboard.next().charAt(0));
		}
		//Calls method again to try again if input is wrong.
		catch (InputMismatchException e) {
			System.out.println("Invalid input - try again.");
			setDirection();
		}
	}


	public void setShot() {
		System.out.println("Enter your coordinates...");

		Scanner keyboard = new Scanner(System.in);
		System.out.println(" Horizontal (A-J):");
		shot[1] = getBoard().Char2Int(keyboard.next().charAt(0));

		//Exception for if Vertical is not a number,
		//or if Direction is not a letter.
		try {
			System.out.println(" Vertical (1-10):");
			shot[0] = keyboard.nextInt() - 1;

		} catch (InputMismatchException e) {
			System.out.println("Invalid input - try again.");
			setShot();
		}
		super.setShot(shot);
	}
	



}
