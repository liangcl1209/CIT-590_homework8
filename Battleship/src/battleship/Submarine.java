package battleship;

public class Submarine extends Ship {

    static final int length = 1;

    static final String shiptype = "Submarine";

    public Submarine(){
        super(length);
    }

    @Override 
    public String getShipType(){
        return shiptype;
    }
}
