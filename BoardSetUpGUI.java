import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



//creates the starting game GUI view , where the player can place their ships.
public class BoardSetUpGUI extends JPanel implements ActionListener {
    private JPanel content= new JPanel(); //holds the content of the GameStarting screen
    private JPanel doneResetPanel = new JPanel(); //holds the done and reset buttons
//    private JOptionPane shipSetUpWindow = new JOptionPane();
    private JLabel msg = new JLabel("Welcome to Battleship!");
    private Player player = new Player();
    private Buttons playerGrid; //Holds the players game grid
    private int boardTotal;
    private JPanel directionPanel= new JPanel();
    private JLabel directionMsg = new JLabel("Let's place our ships.");
    private JPanel shipSetUpPanel = new JPanel();

    //initializes the player set up view
    public BoardSetUpGUI(Player playerToCopy, ActionListener listener){
        player =playerToCopy;
        playerGrid = new Buttons(player, listener);
        setPreferredSize(new Dimension(600,800));
        setSize(550,550);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(msg);
        content.add(playerGrid);
        setDoneResetPanel(listener);
        content.add(doneResetPanel);
        directionMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(directionMsg);
        setShipSetUpPanel(listener);
        content.add(shipSetUpPanel);
        add(content);

    }

    public Buttons getPlayerGrid() {
        return playerGrid;
    }

    public void setShipSetUpPanel(ActionListener listener){
        setDirectionPanel(listener);
        shipSetUpPanel.setLayout(new BoxLayout(shipSetUpPanel, BoxLayout.X_AXIS));
        shipSetUpPanel.add(directionPanel);
       shipSetUpPanel.add(Box.createHorizontalGlue());
        JButton shipSet = new JButton("Next Ship");
        shipSet.addActionListener(listener);
        shipSet.setActionCommand("NEXT_SHIP");
        shipSetUpPanel.add(shipSet);
        shipSetUpPanel.add(Box.createHorizontalGlue());

    }

    //Creates a pop-up window that gets user input to place their ships on the board.


    public void placeShipsButton(){
        directionMsg.setText("Please select a square then a direction for placing your ships. ");
    }

    //Creates the button options to set the ships, complete ship setup or reset their board.
    public void setDoneResetPanel(ActionListener listener){
        doneResetPanel.setLayout(new BoxLayout(doneResetPanel, BoxLayout.X_AXIS));
        doneResetPanel.setBackground(Color.black);

        JButton done = new JButton("Done");
        done.addActionListener(listener);
        done.setActionCommand("DONE");

        JButton reset= new JButton("Reset");
        reset.addActionListener(listener);
        reset.setActionCommand("RESET");

        JButton setShips = new JButton("Set Up Your Ships");
        setShips.addActionListener(listener);
        setShips.setActionCommand("SET_SHIPS");

        doneResetPanel.add(Box.createHorizontalGlue());
        doneResetPanel.add(setShips);
        doneResetPanel.add(Box.createHorizontalGlue());
        doneResetPanel.add(done);
        doneResetPanel.add(Box.createHorizontalGlue());
        doneResetPanel.add(reset);
        doneResetPanel.add(Box.createHorizontalGlue());
    }

    //Direction Panel hold the buttons (North, South, East, West) for placing ships.
    public void setDirectionPanel(ActionListener listener){
        directionPanel.setLayout(new GridLayout(3,3));
        directionPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        directionPanel.setPreferredSize(new Dimension(300,100));
        directionPanel.add(new JLabel()); //for spacing out the buttons in a nice way

        JButton north = new JButton("North");
        north.addActionListener(listener);
        north.setActionCommand("NORTH");

        directionPanel.add(north);
        directionPanel.add(new JLabel()); //for spacing out the buttons in a nice way

        JButton east = new JButton("East");
        east.addActionListener(listener);
        east.setActionCommand("EAST");
        directionPanel.add(east);
        directionPanel.add(new JLabel()); //for spacing out the buttons in a nice way

        JButton west = new JButton("West");
        west.addActionListener(listener);
        west.setActionCommand("WEST");
        directionPanel.add(west);

        JButton south = new JButton("South");
        south.addActionListener(listener);
        south.setActionCommand("SOUTH");
        directionPanel.add(new JLabel()); //for spacing out the buttons in a nice way
        directionPanel.add(south);
        directionPanel.add(new JLabel()); //for spacing out the buttons in a nice way
    }

    public void updateMsg(String s){
        msg.setText(s);
    }

    public void updateDirectionMsg(String s){directionMsg.setText(s);}

    //manages the actions when the user presses the done, reset and set ships button.
    @Override
    public void actionPerformed(ActionEvent e) {
        playerGrid.colorButtons(player);
    }

}
