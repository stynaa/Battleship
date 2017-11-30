import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

//Source for water image: http://www.how-to-draw-funny-cartoons.com/vector-wave.html
//Source for fire image: https://www.123rf.com/photo_64993445_stock-vector-cartoon-comic-graphic-design-for-explosion-blast-dialog-box-background.html


public class Buttons extends  JPanel{
    public static int MAXROW = 10;
    public static int MAXCOL = 10;
    private JButton[][] button = new JButton[MAXROW][MAXCOL];
    public JPanel contributorGrid= new JPanel();
    public ImageIcon fire = new ImageIcon(getFire()); //initialized as an instance variable to increase application speed
    public ImageIcon water = new ImageIcon(getWater());//initialized as an instance variable to increase application speed

    //board value constants
    public static final int EMPTY=0;
    public static final int HIT=3;
    public static final int MISS=2;
    public static final int CARRIER=5;
    public static final int BATTLESHIP=6;
    public static final int CRUISER=7;
    public static final int SUBMARINE=8;
    public static final int DESTROYER=9;



    public Buttons(Player aPlayer, ActionListener listener) {
        contributorGrid.setVisible(true);
        contributorGrid.setLayout(new GridLayout(MAXROW,MAXCOL));
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                button[r][c] = new JButton();
                button[r][c].setPreferredSize(new Dimension(48, 48));
                button[r][c].addActionListener(listener);
                button[r][c].setActionCommand(aPlayer.getClass().getName()+" "+Integer.toString(r)+ " "+Integer.toString(c));
                button[r][c].setBackground(Color.blue);
                button[r][c].setIcon(water);
                contributorGrid.add(button[r][c]);
            }
        }
       colorButtons(aPlayer);
        add(contributorGrid);
    }

    //method to grab the water background from an image file.
    // IO Exception is handled through creation of a new image that is red
    //If the new image creation fails a stack trace is printed to the console.
        public Image getFire() {
        BufferedImage fireImage= null;
        try {
            fireImage = ImageIO.read(new File("lit.jpg"));
        }
        catch(IOException ioe) {
            try {
                fireImage = new BufferedImage(
                        48, 48, BufferedImage.TYPE_INT_RGB);
                File f = new File("Fire.png");
                int r = 255;
                int g = 0;
                int b = 0;
                int col = r | g | b;
                for (int x = 0; x < 48; x++) {
                    for (int y = 0; y < 48; y++) {
                        fireImage.setRGB(x, y, col);
                    }
                }
                ImageIO.write(fireImage, "PNG", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fireImage;
    }



    //method to grab the water background from an image file.
    //IO Exception is handled through creation of a new image that is blue
    //If the new image creation fails a stack trace is printed to the console.
    public Image getWater() {
        BufferedImage waterImage = null;
        try {
            waterImage = ImageIO.read(new File("wave.jpg"));
        }
        catch(IOException ioe) {
            try {
                waterImage = new BufferedImage(
                        45, 45, BufferedImage.TYPE_INT_RGB);

                File f = new File("Water.png");
                int r = 0;
                int g = 0;
                int b = 255;
                int col = r | g | b;
                for (int x = 0; x < 45; x++) {
                    for (int y = 0; y < 45; y++) {
                        waterImage.setRGB(x, y, col);
                    }
                }
                ImageIO.write(waterImage, "PNG", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return waterImage;
    }



    //updates the colors of the buttons to show the user where their ships are placed
    //Also shows if the user has hit or missed during the gamePlay
    public void colorButtons(Player aPlayer)  {
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                if (aPlayer.getBoard().getBoard()[r][c] == EMPTY) { //empty
                    button[r][c].setIcon(water);
                    button[r][c].setBackground(Color.blue);
                } else if (aPlayer.getBoard().getBoard()[r][c] == MISS) {
                    button[r][c].setIcon(null);
                    button[r][c].setBackground(Color.green);
                } else if (aPlayer.getBoard().getBoard()[r][c] == HIT) {
                   button[r][c].setIcon(fire);
                    button[r][c].setBackground(Color.red);
                }
//                if(aPlayer.getClass().getName().equals("Human")){
                else if (aPlayer.getBoard().getBoard()[r][c] == CARRIER) { //Carrier
                    button[r][c].setIcon(null);
                    button[r][c].setBackground(Color.yellow);
                } else if (aPlayer.getBoard().getBoard()[r][c] == BATTLESHIP) { //Battleship
                    button[r][c].setIcon(null);
                    button[r][c].setBackground(Color.cyan);
                } else if (aPlayer.getBoard().getBoard()[r][c] == CRUISER) {//Cruiser
                    button[r][c].setIcon(null);
                    button[r][c].setBackground(Color.orange);
                } else if (aPlayer.getBoard().getBoard()[r][c] == SUBMARINE) { //Submarine
                    button[r][c].setIcon(null);
                    button[r][c].setBackground(Color.pink);
                } else if (aPlayer.getBoard().getBoard()[r][c] == DESTROYER) { //Destroyer
                    button[r][c].setIcon(null);
                    button[r][c].setBackground(Color.magenta);
                }
            }
        }
    }

    //To show the user where they have clicked while they are setting up their ships
    public void colorSingleButton(Player aPlayer, int[] coord) {
        colorButtons(aPlayer);
        int row= coord[0];
        int col= coord[1];
        button[row][col].setIcon(null);
        button[row][col].setBackground(Color.green);
            }

}
