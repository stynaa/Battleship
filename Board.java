
public class Board 
{
	public final static int MAXROW = 10;
	public final static int MAXCOL = 10;
	private final static int MISS = 0; //Fill in
	private final static int HIT = 0; //Fill in
	private final static int EMPTY = 0; //Fill in
	private int[][] board = new int[MAXCOL][MAXROW];
	private int[] coord = new int[2];
	private boolean boardOK;
	private int boardState;
	private Ship Carrier = new Ship(5, 5);
	private Ship Battleship = new Ship(6, 4);
	private Ship Cruiser = new Ship(7, 3);
	private Ship Submarine = new Ship(8, 3);
	private Ship Destroyer = new Ship(9, 2);

	public Board()
	{
		for (int row = 0; row < MAXROW; row++) 
		{
			for (int column = 0; column < MAXCOL; column++) 
			{
				board[row][column] = EMPTY;
			}
		}
	}

	//Copies a Board's board array.
	public Board(Board toCopy)
	{
		int[][] copyBoard = toCopy.getBoard();
	    for (int row = 0; row < copyBoard.length; row++)
	        for (int col = 0; col < copyBoard[row].length; col++)
	          board[row][col] = copyBoard[row][col];
	}
	
	//Converts the letters for row of ship placement into int.
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

	//Creates an empty board array.
	public void createBoard()
	{
		for (int row = 0; row < MAXROW; row++) 
		{
			for (int column = 0; column < MAXCOL; column++) 
			{
				board[row][column] = EMPTY;
			}
		}
	}

	//Prints out board array to terminal.
	public void printBoard()
	{
	    for (int row = 0; row < MAXROW; row++)
	    {
	      for (int col = 0; col < MAXCOL; col++)
	      {
	        System.out.print(" " + board[row][col] + " ");
	      }
	      System.out.println("");
	    }
	}

	public int[][] getBoard()
	{
		return board;
	}

	//Updates the board array by placing the ships.
	//gameBoard: the board that will be updated.
	//direction: which direction the ships are placed.
	//boat: which ship is being placed on the board.
	public void setBoard(Board gameBoard, char direction, Ship boat)
	{
		int[] coordinate = gameBoard.getCoord();
		int shipSize = boat.getSize();
		int shipCode = boat.getCode();
		    for (int i = 0; i < shipSize; i++) 
		    {
		      if (direction == 'N' || direction == 'n') 
		      {
		        board[coordinate[0] - i][coordinate[1]] = shipCode;
		      }
		      if (direction == 'S' || direction == 's') 
		      {
		        board[coordinate[0] + i][coordinate[1]] = shipCode;
		      }
		      if (direction == 'E' || direction == 'e') 
		      {
		        board[coordinate[0]][coordinate[1] + i] = shipCode;
		      }
		      if (direction == 'W' || direction == 'w') 
		      {
		        board[coordinate[0]][coordinate[1] - i] = shipCode;
		      }
		    }
	}

	//Updates the board array to change if ship is hit or miss.
	//shipHit: whether or not the ship was hit.
	//gameBoard: which Board to update.
	public void setBoard(boolean shipHit, Board gameBoard)
	{
		if (shipHit == true)
		{
			convertCoordToPosition(gameBoard, HIT);
		}
		else if (shipHit == false)
		{
			convertCoordToPosition(gameBoard, MISS);
		}
	}
	
	//Converts coordinates into the board array itself
	//gameBoard: which board to take the coordinates from.
	//code: whether it is HIT, MISS, or EMPTY.
	public void convertCoordToPosition(Board gameBoard, int code)
	{
		int[] coordinate = gameBoard.getCoord();
		int row = coordinate[1];
		int col = coordinate[0];
		board[row][col] = code;
	}

	public int[] getCoord()
	{
		return coord;
	}
	
	public void setCoord(int row, int col)
	{
		setXCoord(row);
		setYCoord(col);
	}

	public void setXCoord(int row)
	{
		coord[1] = row;
	}

	public void setYCoord(int col)
	{
		coord[0] = col;
	}
}
