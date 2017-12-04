
public abstract class Player {
	private boolean win = false;
	private Board board;
	int[] shot = new int[2];
	public static int MAXROW = 10;
	public static int MAXCOL = 10;
	private char direction;
	private String message="";

/*
Player Constructor - nothing taken in
creates a new board
*/
	public Player() {
		board = new Board();
	}

/*
Player Constructor - takes in a Player
saves board and shot from the player to copy
*/
	public Player(Player toCopy){
		board = new Board(toCopy.getBoard());
		setShot(toCopy.getShot());
	}

/*
getShot
returns the shot currently saved in player class
*/
	public int[] getShot() {
		return shot;
	}

/*
setShot
takes in a shot coordinate and saves it as player's shot
*/
	public void setShot(int[] shotCoordinate) {
		shot[0] = shotCoordinate[0];
		shot[1] = shotCoordinate[1];
	}

/*
HitOrMiss - nothing taken in
Gets the opponent's shot (saved in shot)
Checks current state of player board at the coordinate of the opponent's shot
Returns true if the player board at opponent's shot coordinate is equal to a ship value
alters player's board accordingly to record that the ship has been shot
*/
	public boolean HitOrMiss() {
		int[] oppShot=getShot();

		boolean oppHit = false;
		if (board.getBoard()[oppShot[0]][oppShot[1]] == 1) {

			board.getBoard()[oppShot[0]][oppShot[1]] = 3;
			oppHit = true;

		} else {
			board.getBoard()[oppShot[0]][oppShot[1]] = 2;
			oppHit = false;
		}
		return oppHit;
	}

	/*
	HitOrMiss - takes in opponent's shot and current player
	Checks current state of current player board at the coordinate of the opponent's shot
	Returns true if the player board at opponent's shot coordinate is equal to a ship value
	sets the message according to the result
	*/
	public boolean HitOrMiss(int[] oppShot, Player curPlayer){
		boolean oppHit = false;
		if (curPlayer.getBoard().getBoard()[oppShot[0]][oppShot[1]] == 5||curPlayer.getBoard().getBoard()[oppShot[0]][oppShot[1]] == 6||
				curPlayer.getBoard().getBoard()[oppShot[0]][oppShot[1]] == 7||curPlayer.getBoard().getBoard()[oppShot[0]][oppShot[1]] == 8||
				curPlayer.getBoard().getBoard()[oppShot[0]][oppShot[1]] == 9) {

			oppHit = true;
			setMessage("Hit!");

		} else {

			oppHit = false;
			setMessage("Miss!");

		}
		return oppHit;
	}

/*
lossCheck
checks the values inside of the player's board for remaining unhit ship values
returns false (opponent has not won) if there are still unhit ships within the player's board
*/
	public boolean lossCheck() {
		boolean oppwin = true;
		int[][] gameBoard = board.getBoard();

		for (int j = 0; j < MAXCOL; j++) {
			for (int i = 0; i < MAXROW; i++)
			{
				if (gameBoard[j][i] == 5 || gameBoard[j][i] == 6 || gameBoard[j][i] == 7 ||
						gameBoard[j][i] == 8 || gameBoard[j][i] == 9) {
					oppwin = false;
				}
			}
		}
		return oppwin;
	}

/*
getWin
returns whether or not player has won
*/
	public boolean getWin() {
		return win;
	}

/*
setWin
sets the win boolean in player
*/
	public void setWin(boolean result) {
		win = result;
	}

/*
getBoard
returns player's board
*/
	public Board getBoard() {
		return board;
	}

/*
setDirection
sets the direction in player for placing ships
*/
	public void setDirection(char aDirection){
		direction = aDirection;
	}

/*
getDirection
returns the direction set in player for placing ships
*/
	public char getDirection(){
		return direction;
	}

/*
setMessage
sets the message in player for player status
*/
	public void setMessage(String aMessage){
		message = aMessage;
	}

/*
getMessage
returns the message saved in player for display
*/
	public String getMessage(){
		return message;
	}

}
