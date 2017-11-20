import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContributorTest {

	@Test
	public void test_getter_Shot() {
		Contributor test = new Contributor();
		test.shot[0] = 5;
		test.shot[1] = 3;
		assertEquals("Shot y is copied", 5, test.getShot()[0]);
		assertEquals("Shot x is copied", 3, test.getShot()[1]);
	}
	
	@Test
	public void test_privacy_getter_shot() {
		Contributor test = new Contributor();
		test.shot[0] = 6;
		test.shot[1] = 8;
		int[] tempShot = test.getShot();
		tempShot[0] = 3;
		
		assertEquals("Primitive is passed, no privacy leak occurs.", 6, test.getShot()[0]);
	}

	@Test
	public void test_winCheck_emptyBoard() {
		Contributor test = new Contributor();
		assertTrue("Empty board, game is won and over", test.winCheck());
	}
	
}
