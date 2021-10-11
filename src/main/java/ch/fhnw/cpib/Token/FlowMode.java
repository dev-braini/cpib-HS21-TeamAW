package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.FlowModes;
import ch.fhnw.cpib.Enums.Terminals;

public class FlowMode extends Token{
    private final FlowModes value;

    public FlowMode(FlowModes value){
        super(Terminals.FLOWMODE);
        this.value = value;
    }
    public FlowModes getValue(){return this.value;}

    public String toString() {
        return "(" + super.getTerminal() + ", " + this.value + ")";
    }
}
