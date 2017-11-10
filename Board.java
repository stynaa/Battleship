//package battleship;

public class Board 
{

	final int MAXCOL = 10;
	final int MAXROW = 10;
	int[][] board = new int[MAXCOL][MAXROW]; //initialized in setComputer, 5 by 5 board
	int[] coord = new int[2]; //used to store x,y coordinate for set up and game play

	
	public Board() 
	{
		for (int row = 0; row < MAXROW; row++) {
		      for (int column = 0; column < MAXCOL; column++) {
		        board[row][column] = 0;
		      }
		    }
	}

	public int Char2Int(char h) {
	    int num = 0;
	    if (h == 'A' || h == 'a') {
	      num = 0;
	    } else if (h == 'B' || h == 'b') {
	      num = 1;
	    } else if (h == 'C' || h == 'c') {
	      num = 2;
	    } else if (h == 'D' || h == 'd') {
	      num = 3;
	    } else if (h == 'E' || h == 'e') {
	      num = 4;
	    } else if (h == 'F' || h == 'f') {
	      num = 5;
	    } else if (h == 'G' || h == 'g') {
	      num = 6;
	    } else if (h == 'H' || h == 'h') {
	      num = 7;
	    } else if (h == 'I' || h == 'i') {
	      num = 8;
	    } else if (h == 'J' || h == 'j') {
	      num = 9;
	    }
	    return num;
	  }

}