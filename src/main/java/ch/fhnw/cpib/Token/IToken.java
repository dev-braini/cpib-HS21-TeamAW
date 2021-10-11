package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Terminals;

public interface IToken {
    public Terminals getTerminal();
    public String toString();
}