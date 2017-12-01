/*
package gui;
*/

import java.util.Scanner;

public class Display {
	private int[] coord = new int[2];
	private final static int MAXROW = 10;
	private final static int MAXCOL = 10;

	// Carrier 5 squares - shipCode=5;
	// Battleship 4 squares- shipCode=6;
	// Cruiser 3 squares- shipCode=7;
	// Submarine 3 squares- shipCode=8;
	// Destroyer 2 squares- shipCode=9;

	public void showPlayerBoard(int[][] board) {
		// Show the tracking view of where the player has tried,
		//symbolizing hits and misses
		System.out.println();
		System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");

		for(int row=0 ; row < MAXROW ; row++ ){
			System.out.print((row+1)+"   ");
			for(int column=0 ; column < MAXCOL ; column++ ){
				if(board[row][column]==-1){
					//for off the board
					System.out.print("\t"+"0");
				}else if(board[row][column]==0){
					//not hit yet
					if(row==9){
						System.out.print("|"+"     "+"| ");
					}
					else{
						System.out.print(" "+"|"+"     "+"|");
					}

				}
				else if(board[row][column]==2){
					System.out.print(" "+"|"+"  O  "+"|");
					//miss
				}
				else if(board[row][column]==3){
					System.out.print(" "+"|"+"  X  "+"|");
										//for if it hits the ship
				}
				else{
					System.out.print(" "+"|"+"  S  "+"|");
					//to show ship placement
				}
			}
			System.out.println();
		}
	}

	public void showComputerBoard(int[][] board) {
		// Show the tracking view of where the computer has tried,
		//symbolizing hits and misses
		System.out.println();
		System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");

		for(int row=0 ; row < MAXROW ; row++ ){
			System.out.print((row+1)+"   ");
			for(int column=0 ; column < MAXCOL ; column++ ){
				if(board[row][column]==-1){
					//for off the board?/not hit yet
					System.out.print("\t"+"0");
				}
				else if(board[row][column]==0){
					if(row==9){
						System.out.print("|"+"     "+"| ");
					}
					else{
						System.out.print(" "+"|"+"     "+"|");
					}

				}
				else if(board[row][column]==2){
					System.out.print(" "+"|"+"  O  "+"|");
					//for miss
				}
				else if(board[row][column]==3){
					System.out.print(" "+"|"+"  X  "+"|");
						//for if it hits the ship
				}
				else{
					System.out.print(" "+"|"+"  S  "+"|");
					//to show ship placement
				}
			}
			System.out.println();
		}
	}
}
