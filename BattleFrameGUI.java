/*
package gui;
import logic.*;
*/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BattleFrameGUI extends JPanel{
    private InfoPanel info; //info panel that explains the current game status.
    private Buttons playerGrid; //Button array displaying the player's board
    private Buttons computerGrid; //Button array displaying the computer's board

    //initializes the game play views
    public BattleFrameGUI(Human human, Computer computer, ActionListener listener) {
        JPanel content = new JPanel();
        JPanel gameBoard = new JPanel();
        JPanel boardLabels = new JPanel();
        playerGrid= new Buttons(human, listener);
        computerGrid= new Buttons(computer, listener);
        setPreferredSize(new Dimension(1000,800));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);
        boardLabels=createBoardLabels(boardLabels);
        content.add(boardLabels);
        gameBoard.setLayout(new GridLayout(1,2,10,10));
        gameBoard.add(playerGrid);
        gameBoard.add(computerGrid);
        content.add(gameBoard);
        info = new InfoPanel();
        content.add(info);
        add(content);
    }

    public JPanel createBoardLabels(JPanel boardLabels){
        boardLabels.setLayout(new BoxLayout(boardLabels,BoxLayout.X_AXIS));
        JLabel playerLbl = new JLabel("Human");
        JLabel computerLbl = new JLabel("Computer");
        playerLbl.setFont(new Font("Serif", Font.BOLD, 18));
        computerLbl.setFont(new Font("Serif", Font.BOLD, 18));
        boardLabels.add(Box.createHorizontalGlue());
        boardLabels.add(playerLbl);
        boardLabels.add(Box.createHorizontalGlue());
        boardLabels.add(Box.createHorizontalGlue());
        boardLabels.add(computerLbl);
        boardLabels.add(Box.createHorizontalGlue());
        return boardLabels;
    }

    //accessor for the computer Grid button array
    public Buttons getComputerGrid(){return computerGrid;}

    //accessor for the player Grid button array
    public Buttons getPlayerGrid(){return playerGrid;}

    //accessor for the Info Panel
    public InfoPanel getInfoPanel() {return info;}


}

