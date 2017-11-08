import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//creates the starting game GUI view , where the player can place their ships.
public class BoardSetUpGUI extends JPanel implements ActionListener {
    public int MAXROW = 10;
    public int MAXCOL = 10;
    private JPanel content= new JPanel(); //holds the content of the GameStarting screen
    private JPanel squareGrid= new JPanel(); //Holds the players game grid
    private JButton[][] button = new JButton[MAXROW][MAXCOL];
    private JPanel doneResetPanel = new JPanel(); //holds the done and reset buttons
    private JOptionPane shipSetUpWindow = new JOptionPane();
    private JLabel msg = new JLabel("Welcome to Battleship!");
    private Player p = new Player();
    private int boardTotal;
    private JPanel container = new JPanel(); //for the CardLayout to work

    //initializes the player set up view
    public BoardSetUpGUI(Player playerToCopy, JPanel containerToCopy){
        p=playerToCopy;
        container=containerToCopy;
        setPreferredSize(new Dimension(600,800));
        setSize(550,550);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);
        squareGrid.setLayout(new GridLayout(MAXROW,MAXCOL));

        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(msg);
        for (int r = 0; r < MAXROW; r++)
        {
            for (int c = 0; c < MAXCOL; c++)
            {
                button[r][c] = new JButton();
                button[r][c].setPreferredSize(new Dimension(48,48));
                button[r][c].setBackground(Color.blue);
                button[r][c].addActionListener(this);
                squareGrid.add(button[r][c]);
            }
        }
        content.add(squareGrid);
        setDoneResetPanel();
        content.add(doneResetPanel);
        add(content);

    }

    //Creates a pop-up window that gets user input to place their ships on the board.
    public void showShipSetUpWindow(){
        int maxship = 5;
        boardTotal=25;
        int shipCode=5;
        for (int i = 0; i < maxship; i++) {
        JDialog dialog = shipSetUpWindow.createDialog(content, "Setting Up Your Ships.");
        String getColumn = shipSetUpWindow.showInputDialog("This is your " + (i+1) + " boat out of " + maxship + ". Please enter a column for your ship to be placed (A-J):");
        if(getColumn.length()>1){
            getColumn = shipSetUpWindow.showInputDialog("Please enter a column for your ship to be placed (A-J):");
        }
          char column= getColumn.charAt(0);
          p.coord[1]=p.char2Int(column);

        String getRow = shipSetUpWindow.showInputDialog("Please enter a row for your ship to be placed (1-10):");
        int row= Integer.parseInt(getRow);
        p.coord[0]= row-1;


        Object[] possibleValues = { "N", "E", "S", "W" };
        Object selectedValue = shipSetUpWindow.showInputDialog(null,
                "Select a direction", "Input",
                shipSetUpWindow.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        p.direction=selectedValue.toString().charAt(0);

            p.board=p.placeShips(boardTotal,shipCode,p.coord,p.direction);
            shipCode++;
            boardTotal=boardTotal+(shipCode*p.getShipSize(shipCode));
            updateButtons();
        }

    }

    //Creates the button options to set the ships, complete ship setup or reset their board.
    public void setDoneResetPanel(){
        doneResetPanel.setLayout(new BoxLayout(doneResetPanel, BoxLayout.X_AXIS));
        doneResetPanel.setBackground(Color.black);

        JButton done = new JButton("Done");
        done.addActionListener(this);
        done.setActionCommand("DONE");

        JButton reset= new JButton("Reset");
        reset.addActionListener(this);
        reset.setActionCommand("RESET");

        JButton setShips = new JButton("Set Up Your Ships");
        setShips.addActionListener(this);
        setShips.setActionCommand("SET_SHIPS");

        doneResetPanel.add(Box.createHorizontalGlue());
        doneResetPanel.add(setShips);
        doneResetPanel.add(Box.createHorizontalGlue());
        doneResetPanel.add(done);
        doneResetPanel.add(Box.createHorizontalGlue());
        doneResetPanel.add(reset);
        doneResetPanel.add(Box.createHorizontalGlue());
    }

    public void updateMsg(String s){
        msg.setText(s);
    }

    //manages the actions when the user presses the done, reset and set ships button.
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed= e.getActionCommand(); //initialized variable to increase readability
        if(buttonPressed.equals("SET_SHIPS")){
            showShipSetUpWindow();
        }
        else if(buttonPressed.equals("RESET")){
            p.clearBoard();
        }
        else if(buttonPressed.equals("DONE")){
            int sumBoard = 0;
            for (int i = 0; i < MAXROW; i++) {
                for (int j = 0; j < MAXCOL; j++) {
                    sumBoard += p.board[i][j];
                }
            }
            if(sumBoard==112){
                BattleFrameGUI gui= new BattleFrameGUI(p);
                CardLayout cardLayout = (CardLayout) container.getLayout();
                container.setPreferredSize(new Dimension(1000,600));
                container.add(gui,"PLAY");
                cardLayout.show(container, "PLAY");

            }
            else{
                updateMsg("Please set up your ships first.");
            }
        }
        updateButtons();
    }

    //updates the colors of the buttons to show the user where their ships are placed
    public void updateButtons(){
        int[][] board = p.getBoard();
        for (int r = 0; r < MAXROW; r++)
        {
            for (int c = 0; c < MAXCOL; c++)
            {
                if(board[r][c]==0){ //empty
                    button[r][c].setBackground(Color.blue);
                }
                else if(board[r][c]==5){ //Carrier
                    button[r][c].setBackground(Color.yellow);
                }
                else if(board[r][c]==6){ //Battleship
                    button[r][c].setBackground(Color.red);
                }
                else if(board[r][c]==7){ //Cruiser
                    button[r][c].setBackground(Color.orange);
                }
                else if(board[r][c]==8){ //Submarine
                    button[r][c].setBackground(Color.green);
                }
                else if(board[r][c]==9){ //Destroyer
                    button[r][c].setBackground(Color.magenta);
                }
            }}
    }
}
