package ch.fhnw.cpib.Parser;

import ch.fhnw.cpib.Enums.Terminals;
import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Parser.ConcreteSyntaxTree.*;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.ITokenList;

/**
 * I'm a parser
 */

public class Parser implements IParser {
    private final ITokenList tokenList;
    private IToken token;
    private Terminals terminal;
    private IConcSyn concSyn;

    public Parser(ITokenList tokenList){
        this.tokenList = tokenList;
        this.tokenList.reset();
        token = tokenList.nextToken();
        terminal = token.getTerminal();
        ConcSyn concSyn = new ConcSyn();
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

    public IConcSyn.IProgram parse() throws GrammarError {
        ConcSyn.IProgram program = program();

        // take the last bite
        consume(Terminals.SENTINEL);

        // TODO: TypeChecking
        // TODO: InitChecking

        return program;
    }

    private IConcSyn.IProgram program() throws GrammarError {
        if (this.terminal == Terminals.PROGRAM) {
            return new Program();
        }

        throw new GrammarError(this.token + ", " + Terminals.PROGRAM);
    }

    /*private IConcSyn.IRepMULTOPRfactor repMULTOPRfactor() throws GrammarError {
        if (terminal == Terminals.MULTOPR) {
            System.out.println("repMULTOPRfactor ::= MULTOPR factor repMULTOPRfactor");
            IOperators.MultOprs opr = ((IToken.MultOpr)consume(Terminals.MULTOPR)).getOpr();
            ConcSyn.IFactor factor = factor();
            ConcSyn.IRepMULTOPRfactor repMULTOPRfactor= repMULTOPRfactor();
            return new RepMULTOPRfactorOpr(opr, factor, repMULTOPRfactor);
        } else if (terminal == Terminals.DO || terminal == Terminals.ADDOPR){
            // System.out.println("repMULTOPRfactor ::= epsilon");
            return new RepMULTOPRfactorEps();
        } else {
            throw  new GrammarError("auxTerm3");
        }

        return null;
    }*/

    private IConcSyn.IFactor factor() {
        return null;
    }
}
