/*
package gui;
import logic.*;
*/

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

//Source for water image: http://www.how-to-draw-funny-cartoons.com/vector-wave.html
//Source for fire image: https://www.123rf.com/photo_64993445_stock-vector-cartoon-comic-graphic-design-for-explosion-blast-dialog-box-background.html

/**
 * Used to display the button array to represent the Computer & User's game board and game play
 */
public class Buttons extends  JPanel{
    
    public static int MAXROW = 10;
    public static int MAXCOL = 10;

    private JButton[][] button = new JButton[MAXROW][MAXCOL];
    private JPanel playerGrid = new JPanel();
    private ImageIcon fire = new ImageIcon(getFire()); //initialized as an instance variable to increase application speed
    private ImageIcon water = new ImageIcon(getWater());//initialized as an instance variable to increase application speed

    //board value constants
    public static final int EMPTY = 0;
    public static final int HIT = 3;
    public static final int MISS = 2;
    public static final int CARRIER = 5;
    public static final int BATTLESHIP = 6;
    public static final int CRUISER = 7;
    public static final int SUBMARINE = 8;
    public static final int DESTROYER = 9;


    /**
     * initializes the gameBoard button arrays
     * @param aPlayer player that is being initialized
     * @param listener to implement the Action in the Controller Class
     */
    public Buttons(Player aPlayer, ActionListener listener) {
        
        playerGrid.setVisible(true);
        playerGrid.setLayout(new GridLayout(MAXROW,MAXCOL));
        
        for (int r = 0; r < MAXROW; r++) {
            for (int c = 0; c < MAXCOL; c++) {
                button[r][c] = new JButton();
                button[r][c].setPreferredSize(new Dimension(48, 48));

                button[r][c].addActionListener(listener);
                button[r][c].setActionCommand(aPlayer.getClass().getName()+" "+Integer.toString(r)+ " "+Integer.toString(c));

                button[r][c].setBackground(Color.blue);
                button[r][c].setIcon(water);

                playerGrid.add(button[r][c]);
            }
        }
        colorButtons(aPlayer);
        add(playerGrid);
    }


    /**
     * method to grab the fire (hit) image from the image file
     * IO Exception is handled through creation of a new image that is red
     * If the new image creation fails the icon is set to null and the button will display as it's background color
     * @return fireImage the image file
     */
        public Image getFire() {
        BufferedImage fireImage = null;
            
        try {
            fireImage = ImageIO.read(new File("lit.jpg"));
        }
        catch(IOException ioe) {
            try {
                //Creates a red file if image cannot be found
                fireImage = new BufferedImage(48, 48, BufferedImage.TYPE_INT_RGB);
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
                //Set the image to null if there is another error
                fireImage=null;
            }
        }
        return fireImage;
    }


    /**
     * method to grab the water background from the image file
     * IO Exception is handled through creation of a new image that is blue
     * If the new image creation fails the icon is set to null and the button will display as it's background color
     * @return waterImage the image file
     */
    public Image getWater() {
        BufferedImage waterImage = null;
        
        try {
            waterImage = ImageIO.read(new File("wave.jpg"));
        }
        catch(IOException ioe) {
            try {
                //Creates a blue file if image cannot be found
                waterImage = new BufferedImage(45, 45, BufferedImage.TYPE_INT_RGB);

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
                //Set the image to null if there is another error
                waterImage=null;
            }
        }
        return waterImage;
    }


    /**
     * updates the colors of the buttons to show the user where their ships are placed
     * also shows if the user or computer has hit or missed during the game play
     * @param aPlayer the player that is being updated
     */
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
                if(aPlayer.getClass().getName().equals("Human")) {
                    if (aPlayer.getBoard().getBoard()[r][c] == CARRIER) {
                        button[r][c].setIcon(null);
                        button[r][c].setBackground(Color.yellow);

                    } else if (aPlayer.getBoard().getBoard()[r][c] == BATTLESHIP) {
                        button[r][c].setIcon(null);
                        button[r][c].setBackground(Color.cyan);

                    } else if (aPlayer.getBoard().getBoard()[r][c] == CRUISER) {
                        button[r][c].setIcon(null);
                        button[r][c].setBackground(Color.orange);

                    } else if (aPlayer.getBoard().getBoard()[r][c] == SUBMARINE) {
                        button[r][c].setIcon(null);
                        button[r][c].setBackground(Color.pink);

                    } else if (aPlayer.getBoard().getBoard()[r][c] == DESTROYER) {
                        button[r][c].setIcon(null);
                        button[r][c].setBackground(Color.magenta);
                    }
                }
            }
        }
    }


    /**
     * To show the user where they have clicked while they are setting up their ships
     * @param aPlayer the player who is placing their ships
     * @param coord the coordinates of the button that was pressed
     */
    public void colorSingleButton(Player aPlayer, int[] coord) {
        colorButtons(aPlayer);
        int row = coord[0];
        int col = coord[1];
        button[row][col].setIcon(null);
        button[row][col].setBackground(Color.green);
            }


}
