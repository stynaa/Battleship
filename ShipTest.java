import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest{

	@Test
	public void test_defaultConstructor(){

        shipTesting1 = new Ship();
        assertEquals("Gets ship code", 5, shipTesting1.getShipCode());
        assertEquals("Gets ship size", 5, shipTesting.getShipSize());
    }

    @Test 
    public void test_secondShip(){

        shipTesting2 = new Ship();
        assertEquals("Gets ship code", 6, shipTesting2.getShipCode());
        assertEquals("Gets ship size", 4, shipTesting2.getShipSize());
    }

    @Test
    public void test_thirdShip(){

        shipTesting3 = new Ship();
        assertEquals("Gets ship code", 7, shipTesting3.getShipCode());
        assertEquals("Gets ship size", 3, shipTesting3.getShipSize());
    }

    @Test
    public void test_fourthShip(){

        shipTesting4 = new Ship();
        assertEquals("Gets ship code", 8, shipTesting4.getShipCode());
        assertEquals("Gets ship size", 3, shipTesting4.getShipSize());
    }

    @Test
    public void test_fifthShip(){

        shipTesting5 = new Ship();
        assertEquals("Gets ship code", 9, shipTesting5.getShipCode());
        assertEquals("Gets ship size", 2, shipTesting5.getShipSize());
    }
}