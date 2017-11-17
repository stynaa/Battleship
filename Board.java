
public class Board 
{
	private final static int MAXROW = 10;
	private final static int MAXCOL = 10;
	private int[][] board = new int[MAXCOL][MAXROW];
	private int[] coord = new int[2];
	private boolean boardOK;
	private int boardState;
	private Ship Carrier = new Ship();
	private Ship Battleship = new Ship();
	private Ship Cruiser = new Ship();
	private Ship Submarine = new Ship();
	private Ship Destroyer = new Ship();

	public Board()
	{
		for (int row = 0; row < MAXROW; row++) {
			for (int column = 0; column < MAXCOL; column++) {
				board[row][column] = 0;
			}
		}
	}

	public Board(Board toCopy)
	{

	}

	public int Char2Int(char row)
	{
		int num = 0;
		if (row == 'A' || row == 'a') {
			num = 0;
		} else if (row == 'B' || row == 'b') {
			num = 1;
		} else if (row == 'C' || row == 'c') {
			num = 2;
		} else if (row == 'D' || row == 'd') {
			num = 3;
		} else if (row == 'E' || row == 'e') {
			num = 4;
		} else if (row == 'F' || row == 'f') {
			num = 5;
		} else if (row == 'G' || row == 'g') {
			num = 6;
		} else if (row == 'H' || row == 'h') {
			num = 7;
		} else if (row == 'I' || row == 'i') {
			num = 8;
		} else if (row == 'J' || row == 'j') {
			num = 9;
		}
		return num;
	}

	public void createBoard()
	{

	}

	public void printBoard()
	{

	}

	public int[][] getBoard()
	{
		return board;
	}

	public void setBoard(int coord)
	{

	}

	public void setBoard(boolean shipHit, int coord)
	{

	}

	public void convertCoordToPosition(int coord)
	{

	}
}
