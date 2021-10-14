package ch.fhnw.cpib.Parser;

import ch.fhnw.cpib.Enums.Terminals;
import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.ITokenList;

/**
 * I'm a parser
 */

public class Parser implements IParser {
    private final ITokenList tokenList;
    private IToken token;
    private Terminals terminal;

    public Parser(ITokenList tokenList){
        this.tokenList = tokenList;
        this.tokenList.reset();
        token = tokenList.nextToken();
        terminal = token.getTerminal();
        // concSyn = new ConcSyn();
    }

    private IToken consume(Terminals expectedTerminal) throws GrammarError {
        if (terminal == expectedTerminal) {
            IToken consumedToken = token;
            if (terminal != Terminals.SENTINEL) {
                token = tokenList.nextToken();
                terminal = token.getTerminal();
            }
            return consumedToken;
        } else {
            throw new GrammarError("Terminal expected: " + expectedTerminal + ", Terminal found: " + terminal);
        }
    }

    public void parse() throws GrammarError {
        // ConcSyn.IProgram program = program();
        consume(Terminals.SENTINEL);
        //return program
    }
}
