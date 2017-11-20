import java.util.*;

public class Board 
{
	public final static int MAXROW = 10;
	public final static int MAXCOL = 10;
	private final static int MISS = 0; //Fill in
	private final static int HIT = 0; //Fill in
	private final static int EMPTY = 0; //Fill in
	private int[][] board = new int[MAXCOL][MAXROW];
	private int[] coord = new int[2]; //0 = col, 1 = row;
	private char direction;
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

	public void placeComputerShip()
	{
		setComputerShip(Carrier);
		setComputerShip(Cruiser);
		setComputerShip(Battleship);
		setComputerShip(Submarine);
		setComputerShip(Destroyer);
	}

	public void setComputerShip(Ship boat)
	{
		int shipLength = boat.getSize();
		int shipCode = boat.getCode();
		//Chooses a random point on the array to start off.
		Random rand = new Random();
		//Limits starting point based on the ship's length.
		int startingRow = rand.nextInt(MAXROW - shipLength) + 1;
		int startingCol = rand.nextInt(MAXCOL - shipLength) + 1;
		//0 = Vertical, 1 = Horizontal
		int vertOrHor = rand.nextInt(2);

		//Checks if the ship will occupy any spaces.
		if (checkComputerSetup(board, startingRow,
				startingCol, shipLength, vertOrHor))
		{
			//Changes row/col of board w/o changing ship's row/col.
			int shipCol = startingCol;
			int shipRow = startingRow;
			for (int sLength = 0; sLength < (shipLength); sLength++)
			{
				//replaces 0 with a number, indicating it is placed.
				if (vertOrHor == 0)
				{
					board[shipCol][startingRow] = shipCode;
					shipCol++;
				}
				else
				{
					board[startingCol][shipRow] = shipCode;
					shipRow++;
				}
			}
		}
		else
			//Finds a new startingRow and Col if the space is occupied.
			//Recursive, so repeats until it finds an empty space.
			setComputerShip(boat);
	}


	//Checks if the space the piece is to be placed is empty.
	public boolean checkComputerSetup(int[][] boardToCheck, int row,
			int col, int shipLength, int vertOrHor)
	{
		boolean emptySpace = true;
		//Cycles through the array for the length of shipLength
		for (int sLength = 0; sLength < shipLength; sLength++)
		{
			//0 = space is empty
			if (boardToCheck[col][row] != 0)
				emptySpace = false;
			else
				emptySpace = emptySpace && true;
			//Alters what spaces to check, depending on ship placement.
			if (vertOrHor == 0) //Vertical checking
				col++;
			else //Horizontal checking
				row++;
		}
		return emptySpace;
	}

	public boolean checkBoard(int currentBoardSum) 
	{
		int sumBoard = 0;
		for (int i = 0; i < board[0].length; i++) 
		{
			for (int j = 0; j < board[0].length; j++) 
			{
				sumBoard += board[i][j];
			}
		}
		if (sumBoard == currentBoardSum) 
		{
			boardOK = true;
		} 
		else 
		{
			boardOK = false;
		}
		return boardOK;
	}

	//Checks the direction of the ship placement.
	public boolean checkDirection(int shipSize) 
	{
		boolean validDirection = true;
		//Checks if the ship would go out of bounds.
		if (direction == 'N' || direction == 'n') 
		{
			if ((coord[0] - shipSize) < 0)
				validDirection = false;
		} 
		else if (direction == 'S' || direction == 's') 
		{
			if ((coord[0] + shipSize) > MAXCOL)
				validDirection = false;
		} 
		else if (direction == 'E' || direction == 'e') 
		{
			if ((coord[1] + shipSize) > MAXROW)
				validDirection = false;
		} 
		else if (direction == 'W' || direction == 'w') 
		{
			if ((coord[1] - shipSize) < 0)
				validDirection = false;
		} 
		else 
		{
			//If N, S, E, or W is not entered as a direction.
			System.out.println("Invalid direction selected.");
			System.out.println("Enter N, S, E, or W.");
			validDirection = false;
		}
		return validDirection;
	}

	public int[][] placeShips(int boardTotal, Ship boat, Board gameBoard) {
		coord = getPlayerCoord();
		int shipSize = boat.getSize();
		int shipCode = boat.getCode();
		if (checkDirection(shipCode)) 
		{
			setBoard(boat);
			boardOK = checkBoard(boardTotal);
			if (!boardOK) 
			{
				System.out.println("Please select a valid position on the board. Note that you cannot place a ship ontop of another.");
				System.out.println();
				Board copyBoard = new Board(gameBoard);
				placeShips(boardTotal, boat, copyBoard);
			}
			Board copyBoard = new Board(gameBoard);
		} 
		else 
		{
			System.out.println("Direction is out of bounds.");
			System.out.println("Please select again.");
			Board copyBoard = new Board(gameBoard);
			placeShips(boardTotal, boat, copyBoard);
		}
		return board;
	}

	//Updates the board array by placing the ships.
	//direction: which direction the ships are placed.
	//boat: which ship is being placed on the board.
	public void setBoard(Ship boat)
	{
		int shipSize = boat.getSize();
		int shipCode = boat.getCode();
		for (int i = 0; i < shipSize; i++) 
		{
			if (direction == 'N' || direction == 'n') 
			{
				board[coord[0] - i][coord[1]] = shipCode;
			}
			if (direction == 'S' || direction == 's') 
			{
				board[coord[0] + i][coord[1]] = shipCode;
			}
			if (direction == 'E' || direction == 'e') 
			{
				board[coord[0]][coord[1] + i] = shipCode;
			}
			if (direction == 'W' || direction == 'w') 
			{
				board[coord[0]][coord[1] - i] = shipCode;
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
			convertCoordToPosition(gameBoard.getCoord(), HIT);
		}
		else if (shipHit == false)
		{
			convertCoordToPosition(gameBoard.getCoord(), MISS);
		}
	}

	//Converts coordinates into the board array itself
	//gameBoard: which board to take the coordinates from.
	//code: whether it is HIT, MISS, or EMPTY.
	public void convertCoordToPosition(int[] coordinate, int code)
	{
		int row = coordinate[1];
		int col = coordinate[0];
		board[row][col] = code;
	}

	public int[] getPlayerCoord() {
		System.out.println("Enter your coordinates...");

		Scanner keyboard = new Scanner(System.in);
		System.out.println(" Horizontal (A-J):");
		setXCoord(Char2Int(keyboard.next().charAt(0)));

		//Exception for if Vertical is not a number,
		//or if Direction is not a letter.
		try {
			System.out.println(" Vertical (1-10):");
			setYCoord(keyboard.nextInt() - 1);

			System.out.println(" Direction of ship (N,S,E,W):");
			direction = keyboard.next().charAt(0);
		}
		//Calls method again to try again if input is wrong.
		catch (InputMismatchException e) {
			System.out.println("Invalid input - try again.");
			getCoord();
		}
		return coord;
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

	public static void main(String[] args)
	{
		Board test = new Board();
		int[] coordinates = new int[2];
		coordinates[0] = 5;
		coordinates[1] = 5;
		System.out.println("Zero: " + coordinates[0] + " One! " + coordinates[1]);
		test.convertCoordToPosition(coordinates, 1);

		int[][] array = test.getBoard();
		System.out.println(array[5][5]);
		boolean same = (array[5][5] == 1);
		System.out.println(same);
	}
}
