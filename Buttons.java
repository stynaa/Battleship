import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Buttons extends  JPanel{
    public static int MAXROW = 10;
    public static int MAXCOL = 10;
    private JButton[][] button = new JButton[MAXROW][MAXCOL];
    public JPanel contributorGrid= new JPanel();

    //board value constants
    public static int EMPTY=0;
    public static int HIT=3;
    public static int MISS=2;
    public static int CARRIER=5;
    public static int BATTLESHIP=6;
    public static int CRUISER=7;
    public static int SUBMARINE=8;
    public static int DESTROYER=9;


    public Buttons(Contributor aContributor, ActionListener listener) {
        contributorGrid.setVisible(true);
        contributorGrid.setLayout(new GridLayout(MAXROW,MAXCOL));
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                button[r][c] = new JButton();
                button[r][c].setPreferredSize(new Dimension(48, 48));
                button[r][c].setBackground(Color.blue);
                button[r][c].addActionListener(listener);
                button[r][c].setActionCommand(aContributor.getClass().getName()+" "+Integer.toString(r)+ " "+Integer.toString(c));
                contributorGrid.add(button[r][c]);
            }
        }
       colorButtons(aContributor);
        add(contributorGrid);
    }

    //updates the colors of the buttons to show the user where their ships are placed
    //Also shows if the user has hit or missed during the gamePlay
    public void colorButtons(Contributor aContributor) {
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                if (aContributor.board[r][c] == EMPTY) { //empty
                    button[r][c].setBackground(Color.blue);
                } else if (aContributor.board[r][c] == MISS) { //Carrier
                    button[r][c].setBackground(Color.green);
                } else if (aContributor.board[r][c] == HIT) { //Carrier
                    button[r][c].setBackground(Color.red);
                }
//                if(aContributor.getClass().getName().equals("Player")){
                else if (aContributor.board[r][c] == CARRIER) { //Carrier
                    button[r][c].setBackground(Color.yellow);
                } else if (aContributor.board[r][c] == BATTLESHIP) { //Battleship
                    button[r][c].setBackground(Color.cyan);
                } else if (aContributor.board[r][c] == CRUISER) {//Cruiser
                    button[r][c].setBackground(Color.orange);
                } else if (aContributor.board[r][c] == SUBMARINE) { //Submarine
                    button[r][c].setBackground(Color.pink);
                } else if (aContributor.board[r][c] == DESTROYER) { //Destroyer
                    button[r][c].setBackground(Color.magenta);
                }//}
            }
        }
    }

}