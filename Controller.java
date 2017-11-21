import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Controller class implements the model view controller design
//Connected with both the game logic and the GUI's to run Battleship.
public class Controller implements ActionListener {
    private JPanel container = new JPanel();
    private CardLayout cardLayout= new CardLayout();
    private Player player = new Player();
    private Computer computer= new Computer();
    private BoardSetUpGUI start= new BoardSetUpGUI(player,this); //ship placement view
    private BattleFrameGUI gui; //game play view
    public static int MAXROW=10;
    public static int MAXCOL=10;
    private int shipCode=5; //for ship placement
    private boolean shipsAreSetUp=false;
    private boolean nextShipFlag = true; //Allows player to see ship set in multiple directions or places before finalizing placement
    private int boardTotal=25; //makes sure that all of the ships are on the board & not placed on top of each other
    private int numberOfGuesses;
    private boolean gameEndFlag=false;

    //Constructor for the class, initializes the JFrame and starts the game
    public Controller(){
        JFrame frame = new JFrame("Battleship");
        computer.getBoard().placeComputerShip();
        frame.setLayout(new BorderLayout());
        container.setLayout(cardLayout);
        container.add(start,"START");
        cardLayout.show(container, "START");
        frame.add(container, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]){
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e){
        }
        new Controller();
    }

    //Handles all of the user interaction throughout the game
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed= e.getActionCommand(); //initialized variable to increase readability
        //System.out.println(buttonPressed); //for debugging
        if(buttonPressed.contains("Computer")&& !gameEndFlag){ //for during gamePlay
            gamePlayActions(buttonPressed); //for during the game play
        }
        else if(!gameEndFlag){
            startGUIActions(buttonPressed); //for during game set up
        }
        else {
            gui.getInfoPanel().setPlayerMessage("Game Over!");
            gui.getInfoPanel().setComputerMessage("");
        }

    }

    public void gamePlayActions(String buttonPressed){
        int[] coord=convertButtons(buttonPressed);
        player.setShot(coord);
        boolean shipHit= computer.HitOrMiss(player.getShot(),computer);
        computer.getBoard().setBoard(shipHit,player.getBoard(),player.getShot());
        gui.getComputerGrid().colorButtons(computer);
        numberOfGuesses++;
        gui.getInfoPanel().setNumberOfGuesses(numberOfGuesses);
        gui.getInfoPanel().setPlayerMessage("Player: "+computer.getMessage());
        if(computer.lossCheck()){
            gameEndFlag=true;
            gui.getInfoPanel().setPlayerMessage("Player Wins!");
            gui.getInfoPanel().setComputerMessage("");
        }
        computer.setShot();
        shipHit= player.HitOrMiss(computer.getShot(),player);
        player.getBoard().setBoard(shipHit,computer.getBoard(),computer.getShot());
        gui.getInfoPanel().setComputerMessage("Computer: "+player.getMessage());
        gui.getPlayerGrid().colorButtons(player);
        if(player.lossCheck()){
            gameEndFlag=true;
            gui.getInfoPanel().setPlayerMessage("Computer Wins!");
            gui.getInfoPanel().setComputerMessage("");
        }
    }


    public void startGUIActions(String buttonPressed){
        if(buttonPressed.equals("RESET")||buttonPressed.equals("DONE")){
            useDoneResetSetShips(buttonPressed);
        }
        else if(buttonPressed.equals("NORTH")||buttonPressed.equals("WEST")||buttonPressed.equals("EAST")||buttonPressed.equals("SOUTH")){
            setDirection(buttonPressed);
        }
        else if(buttonPressed.contains("Player") && !shipsAreSetUp){
            int[] coord= convertButtons(buttonPressed);
            player.setShot(coord);
        }
        else if(buttonPressed.equals("NEXT_SHIP")){
            if(player.getBoard().checkBoard(boardTotal)){
                nextShipFlag=true;
                shipCode++;
                boardTotal = boardTotal + (shipCode * player.getBoard().getShip(shipCode).getShipSize());
                if(shipCode>=10){
                    String s= "You have placed all of your ships!";
                    start.updateDirectionMsg(s);
                }
            }
            else{
                String s= "Place your ship first.";
                start.updateDirectionMsg(s);
            }}
        else if(buttonPressed.equals("EASY")||buttonPressed.equals("MEDIUM")||buttonPressed.equals("HARD")){
            setDifficulty(buttonPressed);
        }
        start.getPlayerGrid().colorButtons(player);
    }


    //converts the button the user pressed to coordinates on the board
    public int[] convertButtons(String buttonPressed){
        String sentenceArray[]= buttonPressed.split(" ");
        int row=Integer.parseInt(sentenceArray[1]);
        int column=Integer.parseInt(sentenceArray[2]);
        int[] coord= new int[2];
        coord[0]=row;
        coord[1]=column;
        return coord;
    }


    //Functionality for the Done & Reset buttons in the GUI, toggles movement from Game set up to Game Play view
    public void useDoneResetSetShips(String buttonPressed){
        if(buttonPressed.equals("RESET")){
            player.getBoard().clearBoard();
            shipCode=5;
        }
        else if(buttonPressed.equals("DONE")) {
            int sumBoard = 0;
            for (int i = 0; i < MAXROW; i++) {
                for (int j = 0; j < MAXCOL; j++) {
                    sumBoard += player.getBoard().getBoard()[i][j];
                }
            }
            if (sumBoard == 112) {
                gui = new BattleFrameGUI(player, computer,this);
                shipsAreSetUp=true;
                container.setPreferredSize(new Dimension(1000, 600));
                container.add(gui, "PLAY");
                cardLayout.show(container, "PLAY");

            } else {
                start.updateDirectionMsg("Please set up your ships first.");
            }
        }

    }

    //Converts the button the user presses into a direction for ship placement
    public void setDirection(String buttonPressed){
        char direction;
        if(buttonPressed.equals("NORTH")){
            direction='N';
            player.setDirection(direction);
        }
        else if(buttonPressed.equals("SOUTH")){
            direction='S';
            player.setDirection(direction);
        }
        else if(buttonPressed.equals("WEST")){
            direction='W';
            player.setDirection(direction);
        }
        else if(buttonPressed.equals("EAST")){
            direction='E';
            player.setDirection(direction);
        }
        placePlayerShips();
    }

    //Sets the difficulty for the computer
    //Commented out body for now, hopefully works with changes - need a copy constructor in either Contributor or Computer
    public void setDifficulty(String buttonPressed){
//        if(buttonPressed.equals("EASY")){
//            computer=new Easy(computer);
//        }
//        else if(buttonPressed.equals("MEDIUM")){
//            computer=new Medium(computer);
//        }
//        else if(buttonPressed.equals("HARD")){
//            computer=new Hard(computer);
//        }
    }

    //Interacts with the player class to display the current board in the GUI and update the player's board object
    public void placePlayerShips(){
        if(shipCode<=9) {
            if(nextShipFlag){
                nextShipFlag=false;
                player.getBoard().setOldBoard(player.getBoard().getBoard());
                player.getBoard().placeShips(boardTotal,shipCode,player.getShot(),player.getDirection());
            }
            else if(!nextShipFlag){
                player.getBoard().copyBoard(player.getBoard().getOldBoard());
                player.getBoard().placeShips(boardTotal,shipCode,player.getShot(),player.getDirection());
            }
            if(!player.getBoard().checkBoard(boardTotal)){
                player.getBoard().copyBoard(player.getBoard().getOldBoard());
            }
            String temp = player.getBoard().getMessage();
            start.updateDirectionMsg(temp);
            start.getPlayerGrid().colorButtons(player);
        }
        else {
            start.updateDirectionMsg("You have placed all of your ships.");
        }
    }
}
