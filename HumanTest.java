import static org.junit.Assert.*;

import org.junit.Test;

public class HumanTest{

  @Test
	public void testDefaultConstructor(){
        Human h= new Human();
        int[] shot = h.getShot();
        assertEquals("Test default shot[0]",0 , shot[0]);
        assertEquals("Test default shot[1]",0, shot[1]);
        assertEquals("Test default win", false, h.getWin());
        assertEquals("Test default message", "", h.getMessage());
    }

    //Copy constructor for the Human class should only copy the board and the shot
    @Test
    public void testCopyConstructor(){
          Human h = new Human();
          h.setDirection('n');
          h.setMessage("hello");
          h.setWin(true);
          int[] hShot= {9,1};
          h.setShot(hShot);

          Human copy= new Human(h);
          int[] shot = copy.getShot();
          assertEquals("Test copy shot[0]",9 , shot[0]);
          assertEquals("Test copy shot[1]",1, shot[1]);
          assertEquals("Test copy win (should be default value)", false, copy.getWin());
          assertEquals("Test copy message (should be default value)", "", copy.getMessage());
      }


}
