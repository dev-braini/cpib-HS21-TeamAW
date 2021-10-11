package ch.fhnw.cpib.Token;

public interface ITokenList {

    void add(IToken token);
    void reset();
    IToken nextToken();
    String toString();

}
