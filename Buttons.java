import java.awt.*;
import javax.swing.*;

public class Buttons extends  JPanel {
    public static int MAXROW = 10;
    public static int MAXCOL = 10;
    private JButton[][] button = new JButton[MAXROW][MAXCOL];
    public JPanel contributerGrid= new JPanel();

    //board value constants
    public static int EMPTY=0;
    public static int HIT=3;
    public static int MISS=2:
    public static int CARRIER=5;
    public static int BATTLESHIP=6;
    public static int CRUISER=7;
    public static int SUBMARINE=8;
    public static int DESTROYER=9;


    public Buttons(Contributor aContributor) {
        contributerGrid.setLayout(new GridLayout(MAXROW,MAXCOL));
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                button[r][c] = new JButton();
                button[r][c].setPreferredSize(new Dimension(48, 48));
                button[r][c].setBackground(Color.blue);
//                button[r][c].addActionListener(new ButtonPressed(r,c));
                contributerGrid.add(button[r][c]);
            }
        }
        colorButtons(Contributor aContributor);
    }


    public void colorButtons(Contributer aContributor) {
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                if (aContributor.board[r][c] == EMPTY) { //empty
                    button[r][c].setBackground(Color.blue);
                } else if (aContributor.board[r][c] == MISS) { //Carrier
                    button[r][c].setBackground(Color.green);
                } else if (aContributor.board[r][c] == HIT) { //Carrier
                    button[r][c].setBackground(Color.red);
                } else if (aContributor.board[r][c] == CARRIER) { //Carrier
                    button[r][c].setBackground(Color.yellow);
                } else if (aContributor.board[r][c] == BATTLESHIP) { //Battleship
                    button[r][c].setBackground(Color.cyan);
                } else if (aContributor.board[r][c] == CRUISER) {//Cruiser
                    button[r][c].setBackground(Color.orange);
                } else if (aContributor.board[r][c] == SUBMARINE) { //Submarine
                    button[r][c].setBackground(Color.pink);
                } else if (aContributor.board[r][c] == DESTROYER) { //Destroyer
                    button[r][c].setBackground(Color.magenta);
                }
            }
        }
    }

}

