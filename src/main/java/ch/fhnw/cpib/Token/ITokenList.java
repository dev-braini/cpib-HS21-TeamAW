package ch.fhnw.cpib.Token;

public interface ITokenList {

    void add(Token base);
    void reset();
    Token nextToken();
    String toString();

}
