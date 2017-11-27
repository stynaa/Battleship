import java.util.*;

public class Board
{
	public final static int MAXROW = 10;
	public final static int MAXCOL = 10;
	private final static int MISS = 2; //Fill in
	private final static int HIT = 3; //Fill in
	private final static int EMPTY = 0; //Fill in
	private int[][] board = new int[MAXCOL][MAXROW];
	private int[][] oldBoard = new int[MAXROW][MAXCOL];
	private int[] coord = new int[2]; //0 = col, 1 = row;
	private char direction;
	private boolean boardOK;
	private int boardState;
	private Ship Carrier = new Ship(5, 5);
	private Ship Battleship = new Ship(4, 6);
	private Ship Cruiser = new Ship(3, 7);
	private Ship Submarine = new Ship(3, 8);
	private Ship Destroyer = new Ship(2, 9);
	private ArrayList<Ship> shipList = new ArrayList<Ship>();
	private  String message=" ";

	public Board()
	{
		for (int row = 0; row < MAXROW; row++)
		{
			for (int column = 0; column < MAXCOL; column++)
			{
				board[row][column] = EMPTY;
			}
		}
		addShipsToList();
	}

	//Copies a Board's board array.
	public Board(Board toCopy)
	{
		int[][] copyBoard = toCopy.getBoard();
		for (int row = 0; row < MAXROW; row++)
			for (int col = 0; col < MAXCOL; col++)
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

	//Adds the ships to an array list.
	public void addShipsToList()
	{
		shipList.add(Carrier);
		shipList.add(Battleship);
		shipList.add(Cruiser);
		shipList.add(Submarine);
		shipList.add(Destroyer);
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
		System.out.println("***");
	}

	public int[][] getBoard()
	{
		return board;
	}

	public void copyBoard(int[][] copyBoard){
		for (int row = 0; row < MAXROW; row++)
		{
			for (int column = 0; column < MAXCOL; column++)
			{
				board[row][column] = copyBoard[row][column];
			}
		}
	}

	public void setOldBoard(int[][] copyBoard){
		for (int row = 0; row < MAXROW; row++)
		{
			for (int column = 0; column < MAXCOL; column++)
			{
				oldBoard[row][column] = copyBoard[row][column];
			}
		}
	}

	public int[][] getOldBoard(){return oldBoard;}

	public int[][] clearBoard(){
		for (int row = 0; row < MAXROW; row++)
		{
			for (int column = 0; column < MAXCOL; column++)
			{
				board[row][column] = EMPTY;
			}
		}
		return board;
	}

	//The actual placing of the ships
	public void placeComputerShip()
	{
		setComputerShip(Carrier);
		setComputerShip(Cruiser);
		setComputerShip(Battleship);
		setComputerShip(Submarine);
		setComputerShip(Destroyer);
	}

	//Placing the ship onto the computer's board.
	//Boat: which ship to place.
	public void setComputerShip(Ship boat)
	{
		int shipLength = boat.getShipSize();
		int shipCode = boat.getShipCode();
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
				emptySpace = true;
			//Alters what spaces to check, depending on ship placement.
			if (vertOrHor == 0) //Vertical checking
				col++;
			else //Horizontal checking
				row++;
		}
		return emptySpace;
	}

	//Used to check victory conditions.
	//BoardSum: shipcode * shiplength
	public boolean checkBoard(int currentBoardSum)
	{
		int sumBoard = 0;
		for (int i = 0; i < board[0].length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				//System.out.println(sumBoard);
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
			if ((coord[0] - shipSize) <0)
				validDirection = false;
		}
		else if (direction == 'S' || direction == 's')
		{
			if ((coord[0] + shipSize)> MAXROW)
				validDirection = false;
		}
		else if (direction == 'E' || direction == 'e')
		{
			if ((coord[1] - shipSize) <0)
				validDirection = false;
		}
		else if (direction == 'W' || direction == 'w')
		{
			if ((coord[1] + shipSize) >MAXCOL)
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

	//place ship method for working with the text based version
	public int[][] placeShips(int boardTotal, Ship boat, int[] coordCopy, char directionCopy, Player player) {
		int shipCode = boat.getShipCode();
		setCoord(coordCopy[0],coordCopy[1]);
		direction=directionCopy;
		if (checkDirection(shipCode))
		{
			setBoard(boat);
			boardOK = checkBoard(boardTotal);
			//System.out.println(boardTotal);
			if (!boardOK)
			{
				copyBoard(oldBoard);
				System.out.println("Please select a valid position on the board. Note that you cannot place a ship ontop of another.");
				System.out.println();
				player.setShot();
				player.setDirection();
				placeShips(boardTotal,boat,player.getShot(),player.getDirection(),player);
			}
			else{
				setOldBoard(board);
			}
//			Board copyBoard = new Board(gameBoard);
		}
		else
		{
			System.out.println("Direction is out of bounds.");
			System.out.println("Please select again.");
			copyBoard(oldBoard);
			player.setShot();
			player.setDirection();
			placeShips(boardTotal,boat,player.getShot(),player.getDirection(),player);
		}
		return board;
	}


	//Method to handle ship placement for the GUI
	public void placeShips(int boardTotal, int shipCode, int[] coordCopy, char directionCopy) {
		Ship boat= getShip(shipCode);
		setCoord(coordCopy[0],coordCopy[1]);
		direction=directionCopy;
		setMessage("Let's place our ships!");
		if(checkDirection(boat.getShipSize())){
			setBoard(boat);
			boardOK = checkBoard(boardTotal);
			if (!boardOK) {
				setMessage("Please select a valid position on the board.");
			}
		}
		else{
			setMessage("Please select a valid position on the board.");
		}

	}

	//Updates the board array by placing the ships.
	//direction: which direction the ships are placed.
	//boat: which ship is being placed on the board.
	public void setBoard(Ship boat){
		int shipSize = boat.getShipSize();
		int shipCode = boat.getShipCode();
		for (int i = 0; i < shipSize; i++)
		{
			if (direction == 'N' || direction == 'n') {
				board[coord[0] - i][coord[1]] = shipCode;
			}
			if (direction == 'S' || direction == 's') {
				board[coord[0] + i][coord[1]] = shipCode;
			}
			if (direction == 'E' || direction == 'e') {
				board[coord[0]][coord[1] - i] = shipCode;
			}
			if (direction == 'W' || direction == 'w') {
				board[coord[0]][coord[1] + i] = shipCode;
			}
		}


	}

	//Updates the board array to change if ship is hit or miss.
	//shipHit: whether or not the ship was hit.
	//gameBoard: which Board to update.
	public void setBoard(boolean shipHit, Board gameBoard, int[] coordCopy)
	{ setCoord(coordCopy[0],coordCopy[1]);
		if (shipHit == true)
		{
			convertCoordToPosition(HIT);
		}
		else if (shipHit == false)
		{
			convertCoordToPosition(MISS);
		}
	}

	//Converts coordinates into the board array itself
	//gameBoard: which board to take the coordinates from.
	//code: whether it is HIT, MISS, or EMPTY.
	public void convertCoordToPosition(int code)
	{
		int row = coord[0];
		int col = coord[1];
		board[row][col] = code;
	}


	public int[] getCoord()
	{
		return coord;
	}

	public ArrayList<Ship> getShipList()
	{
		return shipList;

	}

	public void setCoord(int row, int col)
	{
		coord[0]=row;
		coord[1]=col;
	}


	public Ship getShip(int shipCode){
		Ship s= new Ship(5,5);
		if(shipCode==5){
			s = Carrier;
		}
		else if(shipCode==6){
			s = Battleship;
		}
		else if(shipCode==7){
			s = Cruiser;
		}
		else if(shipCode==8){
			s = Submarine;
		}
		else if(shipCode==9){
			s = Destroyer;
		}
		return s;
	}

	public void setMessage(String s){message=s;}

	public String getMessage(){return message;}
}
