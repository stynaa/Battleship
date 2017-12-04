
public abstract class Player {
	
	private boolean win = false;
	private Board b;
	int[] shot = new int[2];
	public static int MAXROW = 10;
	public static int MAXCOL = 10;
	private char direction;
	private String message = "";
	//maybe add another board to track the shots on the opponent's board??

	public Player() {
		b = new Board();
	}

	public Player(Player toCopy){
		b=new Board(toCopy.getBoard());
		setShot(toCopy.getShot());
	}

	public int[] getShot() {
		//not passed by reference
		int[] copyShot = new int[2];
		copyShot[0] = shot[0];
		copyShot[1] = shot[1];

		return copyShot;
	}

	public void setShot(int[] shotCoordinate) {
		shot[0] = shotCoordinate[0];
		shot[1] = shotCoordinate[1];
	}

	public boolean HitOrMiss() {
		
		//Takes in computers move and see if hits player's game pieces
		//uses coord from p1 and computer's board
		//checks if p1 coords hit computer's board
		//alters computer's board accordingly

		int[] oppShot=getShot();

		boolean oppHit = false;
		if (b.getBoard()[oppShot[0]][oppShot[1]] == 1) {
			
			b.getBoard()[oppShot[0]][oppShot[1]] = 3;
			oppHit = true;
			
			//hitCounter();

		} else {
			//make sure something else checks validity of shot
			b.getBoard()[oppShot[0]][oppShot[1]] = 2;
			oppHit = false;
		}
		return oppHit;
	}

	public boolean HitOrMiss(int[] shot, Player b){
		
		boolean oppHit = false;
		
		if (b.getBoard().getBoard()[shot[0]][shot[1]] == 5||b.getBoard().getBoard()[shot[0]][shot[1]] == 6||
				b.getBoard().getBoard()[shot[0]][shot[1]] == 7||b.getBoard().getBoard()[shot[0]][shot[1]] == 8||
				b.getBoard().getBoard()[shot[0]][shot[1]] == 9) {

//			b.getBoard().getBoard()[shot[0]][shot[1]] = 3;
			oppHit = true;
			setMessage("Hit!");
			//hitCounter();

		} else {
			//make sure something else checks validity of shot
//			b.getBoard().getBoard()[shot[0]][shot[1]] = 2;
			oppHit = false;
			setMessage("Miss!");
		}
		return oppHit;
	}

	public boolean lossCheck() {
		
		boolean oppwin = true;
		int[][] gameBoard = b.getBoard();
		//System.out.println("CHECKING!!!!!");
		
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

	public boolean getWin() {
		return win;
	}

	public void setWin(boolean b) {
		win = b;
	}

	public Board getBoard() {
		return b;
	}

	public void setDirection(char c){
		direction=c;
	}

	public char getDirection(){
		return direction;
	}

	public void setMessage(String s){
		message=s;
	}

	public String getMessage(){
		return message;
	}

}
