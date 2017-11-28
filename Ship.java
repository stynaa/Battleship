public class Ship {

    private int shipSize;
    private int shipCode;
    private int CARRIER = 5; 
    private int BATTLESHIP = 6;
    private int CRUISER = 7;
    private int SUBMARINE = 8;
    private int DESTROYER = 9;

    public Ship(int shipSize, int shipCode){
        this.shipSize = shipSize;
        this.shipCode = shipCode;
    }

    public void setShipCode(){
        if (shipCode == 5){
             shipCode = CARRIER;
        }
        else if (shipCode == 6){
            shipCode = BATTLESHIP;
        }
        else if (shipCode == 7){
            shipCode = CRUISER;
        }
        else if (shipCode == 8){
            shipCode = SUBMARINE;
        }
        else if (shipCode == 9){
            shipCode = DESTROYER;
        }
    }

    public int getShipCode(){
        return shipCode;
    }

    public void setShipSize(){

        if (shipCode == 5){
            shipSize = 5;
        }
        else if (shipCode == 6){
            shipSize = 4;
        }
        else if (shipCode == 7){
            shipSize = 3;
        }
        else if (shipCode == 8){
            shipSize = 3;
        }
        else if (shipCode == 9){
            shipSize = 2;
        }
    }

    public int getShipSize(){
        return shipSize;
    }

    public boolean isShipDestroyed(boolean shipDestroyed){
        return shipDestroyed;
    }

}