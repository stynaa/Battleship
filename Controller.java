import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Controller class implements the model view controller design
//Connected with both the game logic and the GUI's to run Battleship.
public class Controller implements ActionListener {
    private JPanel container = new JPanel();
    private CardLayout cardLayout= new CardLayout();
    private Human human = new Human();
    private Computer computer= new Computer();
    private BoardSetUpGUI start= new BoardSetUpGUI(human,this); //ship placement view
    private BattleFrameGUI gui; //game play view
    public static int MAXROW=10;
    public static int MAXCOL=10;
    private int shipCode=5; //for ship placement
    private boolean shipsAreSetUp=false;
    private boolean nextShipFlag = true; //Allows human to see ship set in multiple directions or places before finalizing placement
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
        human.setShot(coord);
        boolean shipHit= computer.HitOrMiss(human.getShot(),computer);
        computer.getBoard().setBoard(shipHit, human.getBoard(), human.getShot());
        gui.getComputerGrid().colorButtons(computer);
        numberOfGuesses++;
        gui.getInfoPanel().setNumberOfGuesses(numberOfGuesses);
        gui.getInfoPanel().setPlayerMessage("Human: "+computer.getMessage());
        if(computer.lossCheck()){
            gameEndFlag=true;
            gui.getInfoPanel().setPlayerMessage("Human Wins!");
            gui.getInfoPanel().setComputerMessage("");
        }
        computer.setShot(computer.feedbackHit);
        shipHit= human.HitOrMiss(computer.getShot(), human);
        computer.setFeedback(shipHit);
        human.getBoard().setBoard(shipHit,computer.getBoard(),computer.getShot());
        gui.getInfoPanel().setComputerMessage("Computer: "+ human.getMessage());
        gui.getPlayerGrid().colorButtons(human);
        if(human.lossCheck()){
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
            start.getPlayerGrid().colorButtons(human);
        }
        else if(buttonPressed.contains("Human") && !shipsAreSetUp){
            int[] coord= convertButtons(buttonPressed);
            human.setShot(coord);
            start.getPlayerGrid().colorSingleButton(human, coord);
        }
        else if(buttonPressed.equals("NEXT_SHIP")){
            if(human.getBoard().checkBoard(boardTotal)){
                nextShipFlag=true;
                shipCode++;
                boardTotal = boardTotal + (shipCode * human.getBoard().getShip(shipCode).getShipSize());
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
            human.getBoard().clearBoard();
            shipCode=5;
        }
        else if(buttonPressed.equals("DONE")) {
            int sumBoard = 0;
            for (int i = 0; i < MAXROW; i++) {
                for (int j = 0; j < MAXCOL; j++) {
                    sumBoard += human.getBoard().getBoard()[i][j];
                }
            }
            if (sumBoard == 112) {
                gui = new BattleFrameGUI(human, computer,this);
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
            human.setDirection(direction);
        }
        else if(buttonPressed.equals("SOUTH")){
            direction='S';
            human.setDirection(direction);
        }
        else if(buttonPressed.equals("WEST")){
            direction='W';
            human.setDirection(direction);
        }
        else if(buttonPressed.equals("EAST")){
            direction='E';
            human.setDirection(direction);
        }
        placePlayerShips();
    }

    //Sets the difficulty for the computer
    //Commented out body for now, hopefully works with changes - need a copy constructor in either Player or Computer
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

    //Interacts with the human class to display the current board in the GUI and update the human's board object
    public void placePlayerShips(){
        if(shipCode<=9) {
            if(nextShipFlag){
                nextShipFlag=false;
                human.getBoard().setOldBoard(human.getBoard().getBoard());
                human.getBoard().placeShips(boardTotal,shipCode, human.getShot(), human.getDirection());
            }
            else if(!nextShipFlag){
                human.getBoard().copyBoard(human.getBoard().getOldBoard());
                human.getBoard().placeShips(boardTotal,shipCode, human.getShot(), human.getDirection());
            }
            if(!human.getBoard().checkBoard(boardTotal)){
                human.getBoard().copyBoard(human.getBoard().getOldBoard());
            }
            String temp = human.getBoard().getMessage();
            start.updateDirectionMsg(temp);
            start.getPlayerGrid().colorButtons(human);
        }
        else {
            start.updateDirectionMsg("You have placed all of your ships.");
        }
    }
}
