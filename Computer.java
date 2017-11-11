//package battleship;

import java.util.Random;

public class Computer extends Board{
  char direction; //for setPlayer and setComputer, for direction of ships

  //currently poorly implemented booleans
  boolean lastGood = false; //Boolean for checking around the shot location and which direction
  boolean trueN = false;
  boolean trueS = false;
  boolean trueE = false;
  boolean trueW = false;

  //Recent idea variables from TA feedback
  //Instead of holding arbitrary numbers, there will be variable names that provide useful information
  public static final int shotWEIGH = 0;
  public static final int chooseDirection = -10;
  public static final int shotRAND = -100;
  public static final int shotNorth = -2;
  public static final int shotEast = -3;
  public static final int shotWest = -4;
  public static final int shotSouth = -5;

  //An array which will hold a copy of the original shot
  int[] originalShot = new int[2];

  //maybe move this to board class
  boolean win = false; //to determine winner in winCheck()
  int playerHitCounter=0;

  public Computer(){
   super();
  }

  //Gets move of Computer
  //Stores move in coord
  public void getMove(Player p1) {
    Random rand = new Random(); // Initalize random


    if (p1.boardState == shotWEIGH) { // Weight random
      int[] boardChoice;
      //May implement a more sophisticated weight shot
      //Central and odd lines are slightly favoured during targetting
      //
      boardChoice = new int[] {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9};
      coord[1] = boardChoice[rand.nextInt(boardChoice.length)]; //X
      coord[0] = boardChoice[rand.nextInt(boardChoice.length)]; //Y
      //Troubleshooting messages
      //need to figure out how to print this to GUI
      System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
      System.out.println("RANDOM");
    }

    else if (p1.boardState == shotRAND) { //Normal random, previously used as basic firing, DO NOT USE
      coord[1] = rand.nextInt(MAXROW); //X
      coord[0] = rand.nextInt(MAXCOL); //Y
      System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
    }

    else if (p1.boardState == shotNorth) { // Hit, checking Y-North
    //I am not sure if I need to do this "copy" correctly
    //Temporary copy to ensure not out of bounds
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1];      //X?
      temp[0] = p1.coordStored[0] - 1;  //Y?
      //Troubleshooting for the  temporary array
      System.out.println(p1.board.length);
      System.out.println(temp[1]);
      System.out.println(temp[0]);
      if (temp [0] >= 0) {
        coord[1] = p1.coordStored[1];
        coord[0] = p1.coordStored[0] - 1;
        trueN = true;
        //
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
        System.out.println("North");
      }
      //My poor attempt at recursion, just a temporary else
      //Tracing through this, it's quite pointless
      //Will require assistance for properly implementing it
      else {
        p1.boardState = chooseDirection;
        getMove(p1);
      }
    }

    else if (p1.boardState == shotEast) { // Hit, checking X-East
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1] + 1;      //X
      temp[0] = p1.coordStored[0];          //Y
      System.out.println(p1.board.length);
      System.out.println(temp[1]);
      System.out.println(temp[0]);
      if (temp [1] <= p1.board.length) {
        coord[1] = p1.coordStored[1] + 1;
        coord[0] = p1.coordStored[0];
        trueE = true;
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
        System.out.println("East");
      }
            else {
              p1.boardState = chooseDirection;
        getMove(p1);
      }
    }

    else if (p1.boardState == shotWest) { // Hit, checking X-West
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1] - 1;      //X
      temp[0] = p1.coordStored[0];          //Y
      System.out.println(p1.board.length);
      System.out.println(temp[1]);
      System.out.println(temp[0]);
//      if (temp [1] < p1.coord.getLength) {
      if (temp[1] >= 0) {
        coord[1] = p1.coordStored[1] - 1;
        coord[0] = p1.coordStored[0];
        trueW = true;
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
        System.out.println("West");
      }
            else {
              p1.boardState = chooseDirection;
        getMove(p1);
      }
    }

    else if (p1.boardState == shotSouth) { // Hit, checking Y-South
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1];          //X
      temp[0] = p1.coordStored[0] + 1;      //Y
      System.out.println(p1.coord.length);
      System.out.println(temp[1]);
      System.out.println(temp[0]);
//      if (temp [1] < p1.coord.getLength) {
      if (temp[0] <= p1.board.length) {
        coord[1] = p1.coordStored[1];
        coord[0] = p1.coordStored[0] + 1;
        trueS = true;
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
        System.out.println("South");
      }
      else {
        p1.boardState = chooseDirection;
        getMove(p1);
      }

    }
    else if (p1.boardState == chooseDirection) {  //Barrage
    	//Currently not implemented as desired
    	/*
    	Missing:
    	Saved original shot
    	Ship hit data (if possible)
    	A counter for how long to 'barrage' for
    	Implementation will be based on desired action
    	*/
      int tempDir = rand.nextInt(4);
      p1.boardState = -(tempDir + 2);
      System.out.println("Direction " + p1.boardState);
      getMove(p1);
      //System.out.println("practice recursive");

    }

    else {
      p1.boardState = chooseDirection;
      getMove(p1); //This is a really messy protocol
    }

  }

  public boolean HitorMiss(Player p1) {
    //Takes in computer�s move and see if hit�s player's game pieces
    //uses coord from p1 and computer's board
    //checks if p1 coords hit computer's board
    //alters computer's board accordingly
    //firing protocol is selected accordingly
    if (board[p1.coord[0]][p1.coord[1]] == 1 )
      {
      board[p1.coord[0]][p1.coord[1]] = 3;
      System.out.println("Hit Computer!");
      hitCounter();
      }
    else if (board[p1.coord[0]][p1.coord[1]] != 1 )
      {
        board[p1.coord[0]][p1.coord[1]] = -1;
      }
    else {
      board[p1.coord[0]][p1.coord[1]] = 2;
      System.out.println("Missed Computer");
    }
    boolean oppwin = true;
    for (int i=0; i<MAXCOL; i++) {
      for (int j=0; j<MAXROW; j++) {
        int cCheck = board[j][i]; // Less clutter
        if (cCheck == 5 || cCheck == 6 || cCheck == 7 || cCheck == 8 || cCheck == 9) { //Adjusted
          oppwin = false;
        }
      }
    }
    return oppwin;
  }

  public void hitCounter(){
    playerHitCounter=playerHitCounter+1;
  }


  //adds values to the computer's board
  public int[][] createBoard()
  {
    int[][] board = new int[MAXROW][MAXCOL];
    for(int row=0 ; row < MAXROW ; row++ )
    {
      for(int column=0 ; column < MAXCOL ; column++ )
      {
        board[row][column]= 0;
        System.out.print(" " + board[row][column] + " ");
      }
      System.out.println("");
    }
    return board;
  }

  //for testing purposes
  public void printBoard(int[][] boarda)
  {
    for (int row = 0; row < MAXROW; row++)
    {
      for (int col = 0; col < MAXCOL; col++)
      {
        System.out.print(" " + boarda[row][col] + " ");
      }
      System.out.println("");
    }
  }

  public void placeShips()
  {
    //Carrier, Battleship, Crusier, Submarine, Destroyer
    setShip(5, 5);
    setShip(4, 6);
    setShip(3, 7);
    setShip(3, 8);
    setShip(2, 9);
  }

  //Symbol is for debugging
  public void setShip(int shipLength, int symbol)
  {
    //Chooses a random point on the array to start off.
    Random rand = new Random();
    //Limits starting point based on the ship's length.
    int startingRow = rand.nextInt(MAXROW - shipLength) + 1;
    int startingCol = rand.nextInt(MAXCOL - shipLength) + 1;
    //0 = Vertical, 1 = Horizontal
    int vertOrHor = rand.nextInt(2);

    //Testing
    //System.out.println("StartCol: " + startingCol);
    //System.out.println("StartRow: " + startingRow);
    //System.out.println("Vert?: " + vertOrHor);

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
          board[shipCol][startingRow] = symbol;
          shipCol++;
        }
        else
        {
          board[startingCol][shipRow] = symbol;
          shipRow++;
        }
      }
      //Testing
      //printBoard(board);
    }
    else
    //Finds a new startingRow and Col if the space is occupied.
    //Recursive, so repeats until it finds an empty space.
    setShip(shipLength, symbol);
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
}
