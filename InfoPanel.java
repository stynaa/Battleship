//Taken from
//https://www.cs.montana.edu/courses/spring2006/221/programs/program2.html


import javax.swing.*;

import java.awt.*;

/**
 * Creates a panel for displaying gameplay information.  Extends the JPanel class.
 */

public class InfoPanel extends JPanel {
    /**
     * integer representing the number of guesses made
     */
    int numberOfGuesses = 0;
    //String words;

    /**
     * JLabel containing the integer numberOfGuesses
     */
    JLabel guesses;
    JLabel playerMessage;
    JLabel computerMessage;

    /**
     * Class Constructor.
     */

    public InfoPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(1000, 120));
        guesses=new JLabel(" ");
//        guesses = new JLabel("Guesses: " + numberOfGuesses);
//        guesses.setForeground(Color.red);
        add(Box.createHorizontalGlue());
        add(guesses);
       add(Box.createHorizontalGlue());

        playerMessage = new JLabel("Hello!");
        playerMessage.setForeground(Color.green);
        computerMessage = new JLabel(" ");
        computerMessage.setForeground(Color.green);
        add(playerMessage);
        add(Box.createHorizontalGlue());
        add(computerMessage);
        add(Box.createHorizontalGlue());
    }

    public void setNumberOfGuesses(int num){
        System.out.println(num);
        numberOfGuesses=num;
    }
    public int getNumberOfGuesses(){
        return numberOfGuesses;
    }

    public void setPlayerMessage(String s){
        playerMessage.setText(s);
    }
    public void setComputerMessage(String s){
        computerMessage.setText(s);
    }

    /**
     * Paints the panel with the necessary information
     *
     * @param g the graphics instance to be painted
     */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.white);
        g.setColor(Color.black);
        g.drawRect(0, 0, 1000, 120);
        g.setColor(Color.green);
        g.fillRect(5, 15, 10, 10);
        g.setColor(Color.black);
        g.drawString("Miss", 20, 25);
        g.setColor(Color.black);
        g.drawString("Hit", 20, 40);
        g.setColor(Color.red);
        g.fillRect(5, 30, 10, 10);
        g.setColor(Color.black);
        g.drawString("Destroyer (2)", 20, 55);
        g.setColor(Color.magenta);
        g.fillRect(5, 45, 10, 10);
        g.setColor(Color.black);
        g.drawString("Submarine (3)", 20, 70);
        g.setColor(Color.pink);
        g.fillRect(5, 60, 10, 10);
        g.setColor(Color.black);
        g.drawString("Cruiser (3)", 20, 85);
        g.setColor(Color.orange);
        g.fillRect(5, 75, 10, 10);
        g.setColor(Color.black);
        g.drawString("Battleship (4)", 20, 100);
        g.setColor(Color.cyan);
        g.fillRect(5, 90, 10, 10);
        g.setColor(Color.black);
        g.drawString("Carrier (5)", 20, 115);
        g.setColor(Color.yellow);
        g.fillRect(5, 105, 10, 10);
    }


}
