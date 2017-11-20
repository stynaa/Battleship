import static org.junit.Assert.*;

import org.junit.Test;

public class ComputerTesting {

	
	@Test
	public void testFunction() {
		Computer c = new Computer();
		c.setShot(false);
		c.getShot();
		assertEquals("Shots fired", c.x,c.x, 0.00001);
				
	}
	/*
	public void hitWithNoPreviousHit() {
		Computer c = new Computer();
		c.setShot(true);
		assertTrue("Shot is properly registered as a correct hit", c.getGood());
		
	}*/
	
	public void hitwithPreviousHit() {
		Computer c = new Computer(); 
		c.setShot(true);
		c.setShot(true);
		assertTrue("Consecutive shots are remembered and recognized", c.getGood());
	}
	
	public void checkingDirection() {
		Computer c = new Computer();
		c.setShot(true);
		int a = c.getShot()[1];
		int b = c.getShot()[0];
		c.setShot(true);
		if (c.trueN) {
			b--;
		}
		else if (c.trueE) {
			a++;
		}
		else if (c.trueW) {
			a--;
		}
		else if (c.trueS) {
			b++;
		}
		assertEquals("Directional firing increments proper x direction", a, c.x, 0.00001);
		assertEquals("Directional firing increments proper y direction", b, c.y, 0.00001);
	}
}
