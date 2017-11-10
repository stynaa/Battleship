import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BattleFrameGUI extends JPanel{
  public int MAXROW = 10;
  public int MAXCOL = 10;
  private JPanel content= new JPanel();
  private JPanel computerGrid = new JPanel();
  private JPanel playerGrid = new JPanel();
  private JPanel gameBoard = new JPanel();
  //grid for the buttons
  //buttons for the grid :P
  public JButton button[][] = new JButton[MAXROW][MAXCOL];
  //colour of buttons
  Color bDefaultColour;
  //temp board
  public int board[][] = new int[MAXROW][MAXCOL];

  private InfoPanel info;

  public GamePlay game = new GamePlay();
  private Display screen = new Display();
  private Player p1 = new Player();
  private Computer c1 = new Computer();

  private boolean gameOver = false;

//initializes the game play views
  public BattleFrameGUI(Player playerToCopy) {
      p1=playerToCopy;
      c1 = game.setComputer();
    setPreferredSize(new Dimension(1000,600));
    setSize(550,550);
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
    content.setAlignmentX(Component.CENTER_ALIGNMENT);
    setVisible(true);

    gameBoard.setLayout(new GridLayout(1,2,10,10));

      playerGrid.setLayout(new GridLayout(MAXROW,MAXCOL));
      for (int r = 0; r < MAXROW; r++) {
          for (int c = 0; c < MAXCOL; c++) {
              button[r][c] = new JButton();
              button[r][c].setPreferredSize(new Dimension(48, 48));
              playerGrid.add(button[r][c]);
          }
      }
      colorButtons();
    computerGrid.setLayout(new GridLayout(MAXROW,MAXCOL));
    for (int r = 0; r < MAXROW; r++)
    {
        for (int c = 0; c < MAXCOL; c++)
        {
            button[r][c] = new JButton();
//            grid[r][c].setLayout(new FlowLayout());
            button[r][c].setPreferredSize(new Dimension(48,48));
            button[r][c].setBackground(Color.blue);
            button[r][c].addActionListener(new ButtonPressed(r,c));
            computerGrid.add(button[r][c]);
//            content.add(grid[r][c]);
        }
    }

    gameBoard.add(playerGrid);
    gameBoard.add(computerGrid);
    content.add(gameBoard);

    info = new InfoPanel();

    content.add(info);

    bDefaultColour = button[1][1].getBackground();//set the default Color of the JButtons
      add(content);
  }

  //colors the buttons according to their value or ship type
    public void colorButtons(){
        int[][] board = p1.getBoard();
        for (int r = 0; r < MAXROW; r++)
        {
            for (int c = 0; c < MAXCOL; c++)
            {
                if(board[r][c]==0){ //empty
                    button[r][c].setBackground(Color.blue);
                }
                else if(board[r][c]==5){ //Carrier
                    button[r][c].setBackground(Color.yellow);
                }
                else if(board[r][c]==6){ //Battleship
                    button[r][c].setBackground(Color.red);
                }
                else if(board[r][c]==7){//Cruiser
                    button[r][c].setBackground(Color.orange);
                }
                else if(board[r][c]==8){ //Submarine
                    button[r][c].setBackground(Color.green);
                }
                else if(board[r][c]==9){ //Destroyer
                    button[r][c].setBackground(Color.magenta);
                }
            }}
    }



  public void setComputer(Computer c)
  {
    c1 = c;
  }

  public void setPlayer (Player p) {
    p1 = p;
  }

  public class ButtonPressed implements ActionListener
  {
      int r;//row the button was pressed in
      int c;//column the button was pressed in

      public ButtonPressed(int row, int column)
      {
          r = row;
          c = column;
      }

       //Checks if the guess was a hit or miss.  Displays the result on the JFrame.
       //@param   evt the specific ActionEvent that was triggered
      public void actionPerformed(ActionEvent evt)
      {
          if (!gameOver) {
          //if the guess was a miss
          if (c1.board[r][c] == 0)
          {
              info.talk.setText("You Missed!");
              button[r][c].setBackground(Color.red);
              c1.board[r][c] = 2;
              c1.getMove(p1);
              c1.win = p1.HitorMiss(c1);

          }

          //if the guess was a hit
          else if (c1.board[r][c] > 4 && c1.board[r][c] < 10) {
            info.talk.setText("You Hit!");
            button[r][c].setBackground(Color.green);
            c1.board[r][c] = 3;

            //check if player won
            boolean wwin = true;
            for (int i=0; i<MAXCOL; i++) {
              for (int j=0; j<MAXROW; j++) {
                int cCheck = c1.board[j][i]; // Less clutter
                if (cCheck == 5 || cCheck == 6 || cCheck == 7 || cCheck == 8 || cCheck == 9) { //Adjusted
                  wwin = false;
                }
              }
            }
            p1.win = wwin;

            if (!p1.win) {
              c1.getMove(p1);
              c1.win = p1.HitorMiss(c1);
            }

          }
          else if (c1.board[r][c] == 2 || c1.board[r][c] == 3) {
            info.talk.setText("You already moved here. Press something else.");
          }
          else {
            info.talk.setText("error: board value out of bounds.");
          }

          if (c1.win || p1.win) {
            winner();
          }
        }
      }
  }





  //}


  public void setPlayerGUI() {
    //communicates with setPlayer in GamePlay to get Player's moves
    int maxship = 5;
    int boardTotal = 17;

    //for ()

    }

  public void winner() {
            if (p1.win && !c1.win) {
              info.talk.setText("You win! GAME OVER");
              gameOver = true;
            } else if (!p1.win && c1.win) {
              info.talk.setText("Computer wins! You lose! GAME OVER");
              gameOver = true;
            } else if (p1.win && c1.win) {
              info.talk.setText("It's a tie! GAME OVER");
              gameOver = true;
            }


  }

}
