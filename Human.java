/**
 * Human Class to represent the user's game play
 *  Class is maintained help with readability in the Controller classes
 */
public class Human extends Player
{
	/**
	 * Default constructor, calls the default constructor in the Player Class
	 */
	public Human()
	{
		super();
	}

	/**
	 * Copy constructor, calls the copy constructor in the Player Class
	 */
	public Human(Human toCopy){
		super(toCopy);
	}

}
