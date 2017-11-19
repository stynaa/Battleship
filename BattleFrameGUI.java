import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BattleFrameGUI extends JPanel{
    private InfoPanel info; //info panel that explains the current game status.
    private Buttons playerGrid; //Button array displaying the player's board
    private Buttons computerGrid; //Button array displaying the computer's board

    //initializes the game play views
    public BattleFrameGUI(Player player, Computer computer, ActionListener listener) {
        JPanel content = new JPanel();
        JPanel gameBoard = new JPanel();
        playerGrid= new Buttons(player, listener);
        computerGrid= new Buttons(computer, listener);
        setPreferredSize(new Dimension(1000,800));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);
        gameBoard.setLayout(new GridLayout(1,2,10,10));
        gameBoard.add(playerGrid);
        gameBoard.add(computerGrid);
        content.add(gameBoard);
        info = new InfoPanel();
        content.add(info);
        add(content);
    }

    //accessor for the computer Grid button array
    public Buttons getComputerGrid(){return computerGrid;}

    //accessor for the player Grid button array
    public Buttons getPlayerGrid(){return playerGrid;}

    //accessor for the Info Panel
    public InfoPanel getInfoPanel() {return info;}


}

