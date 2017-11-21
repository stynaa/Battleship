
public class Contributor {
	private boolean win = false;
	private boolean endGame = false;
	private boolean shotHit = false;
	private Board b;
	int[] shot = new int[2];
	public static int MAXROW = 10;
	public static int MAXCOL = 10;
	private char direction;
	private String message="";
	//maybe add another board to track the shots on the opponent's board??

	public Contributor() {
		b = new Board();
	}

	public Contributor(Contributor toCopy){
		b=new Board(toCopy.getBoard());
		setShot(toCopy.getShot());
	}

	public int[] getShot() {
		//not pass by reference, can change this
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
		//Takes in computer�s move and see if hit�s player's game pieces
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

	public boolean HitOrMiss(int[] shot, Contributor b){
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

	public boolean winCheck() {
		boolean oppwin = true;

		for (int i = 0; i < MAXCOL; i++) {
			for (int j = 0; j < MAXROW; j++) {

				if (b.getBoard()[j][i] == 1) {
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

	public boolean getEndGame() {
		return endGame;
	}

	public void setEndGame(boolean end) {
		endGame = end;
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
