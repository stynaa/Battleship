//Taken from
//https://www.cs.montana.edu/courses/spring2006/221/programs/program2.html


import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.*;

/**
 * Creates a panel for displaying gameplay information.  Extends the JPanel class.
 */

public class InfoPanel extends JPanel
{
    /** integer represenging the number of guesses made*/
    int numberOfGuesses = 0;
    final int WIDTH = 300;
    final int HEIGHT = 115;
    //String words;

    /** JLabel containing the integer numberOfGuesses*/
    JLabel guesses;
    JLabel talk;

    /**
     * Class Constructor.
     */

    public InfoPanel()
    {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(WIDTH,HEIGHT + 25));
        guesses = new JLabel("Guesses: " + numberOfGuesses);
        guesses.setForeground(Color.red);
        add(guesses);

        talk = new JLabel("Hello!");
        talk.setForeground(Color.black);
        add(talk);
    }

    /**
     * Paints the panel with the necessary information
     *
     * @param   g   the graphics instance to be painted
     */

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.white);
        g.setColor(Color.black);
        g.drawRect(0,25,WIDTH - 1, HEIGHT - 1);
        //g.fillRect(5,15,10,10);
        //g.drawString("Miss",20,25);
        g.setColor(Color.magenta);
        g.fillRect(5,30,10,10);
        g.setColor(Color.black);
        g.drawString("Destroyer (2)",20,40);
        g.setColor(Color.green);
        g.fillRect(5,45,10,10);
        g.setColor(Color.black);
        g.drawString("Submarine (3)",20,55);
        g.setColor(Color.orange);
        g.fillRect(5,60,10,10);
        g.setColor(Color.black);
        g.drawString("Crusier (3)",20,70);
        g.setColor(Color.red);
        g.fillRect(5,75,10,10);
        g.setColor(Color.black);
        g.drawString("Battleship (4)",20,85);
        g.setColor(Color.yellow);
        g.fillRect(5,90,10,10);
        g.setColor(Color.black);
        g.drawString("Carrier (5)",20,100);
    }
}
