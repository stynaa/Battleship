import javax.swing.*;
import java.awt.*;

public class Controller {
   private JFrame frame = new JFrame("Battleship");
    public JPanel container = new JPanel();
    private JPanel gameStart = new JPanel();
    private JPanel gamePlaying = new JPanel();
    public CardLayout c= new CardLayout();
    private Player p = new Player();
   private BoardSetUpGUI start= new BoardSetUpGUI(p,container);
   private BattleFrameGUI gui= new BattleFrameGUI(p);



    public Controller(){
        frame.setLayout(new BorderLayout());
        container.setLayout(c);
        gameStart.add(start);
        gamePlaying.add(gui);
        container.add(start,"START");
        container.add(gui,"PLAY");
        c.show(container, "START");
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
}
