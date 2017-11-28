import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Buttons extends  JPanel{
    public static int MAXROW = 10;
    public static int MAXCOL = 10;
    private JButton[][] button = new JButton[MAXROW][MAXCOL];
    public JPanel contributorGrid= new JPanel();

    //board value constants
    public static final int EMPTY=0;
    public static final int HIT=3;
    public static final int MISS=2;
    public static final int CARRIER=5;
    public static final int BATTLESHIP=6;
    public static final int CRUISER=7;
    public static final int SUBMARINE=8;
    public static final int DESTROYER=9;
    public static final int BUTTON_CLICKED=1;



    public Buttons(Player aPlayer, ActionListener listener) {
        contributorGrid.setVisible(true);
        contributorGrid.setLayout(new GridLayout(MAXROW,MAXCOL));
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                button[r][c] = new JButton();
                button[r][c].setPreferredSize(new Dimension(48, 48));
                button[r][c].setBackground(Color.blue);
                button[r][c].addActionListener(listener);
                button[r][c].setActionCommand(aPlayer.getClass().getName()+" "+Integer.toString(r)+ " "+Integer.toString(c));
                contributorGrid.add(button[r][c]);
            }
        }
       colorButtons(aPlayer);
        add(contributorGrid);
    }

    //updates the colors of the buttons to show the user where their ships are placed
    //Also shows if the user has hit or missed during the gamePlay
    public void colorButtons(Player aPlayer) {
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                if (aPlayer.getBoard().getBoard()[r][c] == EMPTY) { //empty
                    button[r][c].setBackground(Color.blue);
                } else if (aPlayer.getBoard().getBoard()[r][c] == MISS) { //Carrier
                    button[r][c].setBackground(Color.green);
                } else if (aPlayer.getBoard().getBoard()[r][c] == HIT) { //Carrier
                    button[r][c].setBackground(Color.red);
                }
//                if(aPlayer.getClass().getName().equals("Human")){
                else if (aPlayer.getBoard().getBoard()[r][c] == CARRIER) { //Carrier
                    button[r][c].setBackground(Color.yellow);
                } else if (aPlayer.getBoard().getBoard()[r][c] == BATTLESHIP) { //Battleship
                    button[r][c].setBackground(Color.cyan);
                } else if (aPlayer.getBoard().getBoard()[r][c] == CRUISER) {//Cruiser
                    button[r][c].setBackground(Color.orange);
                } else if (aPlayer.getBoard().getBoard()[r][c] == SUBMARINE) { //Submarine
                    button[r][c].setBackground(Color.pink);
                } else if (aPlayer.getBoard().getBoard()[r][c] == DESTROYER) { //Destroyer
                    button[r][c].setBackground(Color.magenta);
                }
            }
        }
    }

    //To show the user where they have clicked while they are setting up their ships
    public void colorSingleButton(Player aPlayer, int[] coord){
        colorButtons(aPlayer);
        int row= coord[0];
        int col= coord[1];
        button[row][col].setBackground(Color.green);
            }

}
