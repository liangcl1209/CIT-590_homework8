package battleship;


public class Battleship extends Ship {

    static final int length = 4;

    static final String shiptype = "Battleship";

    public Battleship(){
        super(length);
    }

    @Override
    public String getShipType(){

        return shiptype;
    }
}
