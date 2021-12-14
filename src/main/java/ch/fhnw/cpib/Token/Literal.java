package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Terminals;

public class Literal extends Token{
    private Boolean bool = null;
    private Integer integer = null;
    private String string = "";

    public Literal(){
        super(Terminals.LITERAL);
    }

    public Literal(Boolean bool){
        super(Terminals.LITERAL);
        this.bool = bool;
    }

    public Literal(Integer integer){
        super(Terminals.LITERAL);
        this.integer = integer;
    }

    @Override
    public String toString(){
        if (bool != null) {
            return "(LITERAL, BoolVal " + bool + ")";
        } else if (integer != null) {
            return "(LITERAL, Int64Val " + integer + ")";
        } else {
            return "";
        }
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = Boolean.valueOf(bool);
        this.integer = null;
        this.string = "";
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.bool = null;
        this.integer = integer;
        this.string = "";
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.bool = null;
        this.integer = null;
        this.string = string;
    }
}
