package battleship;

public class Cruiser extends Ship {

    static final int length = 3;

    static final String shiptype = "Crusier";

    public Cruiser(){
        super(length);
    }

    @Override 
    public String getShipType(){
        return shiptype;
    }
}
