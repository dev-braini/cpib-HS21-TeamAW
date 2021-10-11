package ch.fhnw.cpib.Scanner;

import ch.fhnw.cpib.Enums.*;
import ch.fhnw.cpib.Errors.LexicalError;
import ch.fhnw.cpib.Token.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * I'm a scanner
 */
public class Scanner {
    private final ITokenList tokenList;
    private final List<String> symbols = Arrays.asList("(", ")", ",", ";", ":", "=", "*", "+", "-", "/", "<", ">", "&", "|", "?");
    public Map<String, Token> keywords = new HashMap<>();
    public Scanner() {

        //Terminals
        keywords.put("(",new Token(Terminals.LPAREN));
        keywords.put(")",new Token(Terminals.RPAREN));
        keywords.put(",",new Token(Terminals.COMMA));
        keywords.put(";",new Token(Terminals.SEMICOLON));
        keywords.put(":",new Token(Terminals.COLON));
        keywords.put(":=",new Token(Terminals.BECOMES));
        keywords.put("call",new Token(Terminals.CALL));
        keywords.put("debugin",new Token(Terminals.DEBUGIN));
        keywords.put("debugout",new Token(Terminals.DEBUGOUT));
        keywords.put("do", new Token(Terminals.DO));
        keywords.put("if", new Token(Terminals.IF));
        keywords.put("then", new Token(Terminals.THEN));
        keywords.put("else", new Token(Terminals.ELSE));
        keywords.put("endif", new Token(Terminals.ENDIF));
        keywords.put("fun", new Token(Terminals.FUN));
        keywords.put("endfun", new Token(Terminals.ENDFUN));
        keywords.put("proc", new Token(Terminals.PROC));
        keywords.put("endproc", new Token(Terminals.ENDPROC));
        keywords.put("program", new Token(Terminals.PROGRAM));
        keywords.put("endprogram", new Token(Terminals.ENDPROGRAM));
        keywords.put("while", new Token(Terminals.WHILE));
        keywords.put("endwhile", new Token(Terminals.ENDWHILE));
        keywords.put("local", new Token(Terminals.LOCAL));
        keywords.put("global", new Token(Terminals.GLOBAL));
        keywords.put("init", new Token(Terminals.INIT));
        keywords.put("returns", new Token(Terminals.RETURNS));
        keywords.put("not", new Token(Terminals.NOTOPR));
        keywords.put("skip", new Token(Terminals.SKIP));

        //Operators
        keywords.put("+", new Operator(Terminals.ADDOPR, Operators.PLUS));
        keywords.put("-", new Operator(Terminals.ADDOPR, Operators.MINUS));
        keywords.put("*", new Operator(Terminals.MULTOPR, Operators.TIMES));
        keywords.put("=", new Operator(Terminals.RELOPR, Operators.EQ));
        keywords.put("/=", new Operator(Terminals.RELOPR, Operators.NE));
        keywords.put("<", new Operator(Terminals.RELOPR, Operators.LT));
        keywords.put(">", new Operator(Terminals.RELOPR, Operators.GT));
        keywords.put("<=", new Operator(Terminals.RELOPR, Operators.LE));
        keywords.put(">=", new Operator(Terminals.RELOPR, Operators.GE));
        keywords.put("&&", new Operator(Terminals.RELOPR, Operators.AND));
        keywords.put("||", new Operator(Terminals.RELOPR, Operators.OR));
        keywords.put("&?", new Operator(Terminals.RELOPR, Operators.CAND));
        keywords.put("|?", new Operator(Terminals.RELOPR, Operators.COR));
        keywords.put("divE", new Operator(Terminals.MULTOPR, Operators.DIV_E));
        keywords.put("modE", new Operator(Terminals.MULTOPR, Operators.MOD_E));

        //Types
        keywords.put("int64", new Type(Types.INT64));
        keywords.put("bool", new Type(Types.BOOL));

        //MechModes
        keywords.put("copy", new MechMode(MechModes.COPY));
        keywords.put("ref", new MechMode(MechModes.REF));

        //Literals
        keywords.put("true", new Literal(true));
        keywords.put("false", new Literal(false));

        //ChangeModes
        keywords.put("const", new ChangeMode(ChangeModes.CONST));
        keywords.put("var", new ChangeMode(ChangeModes.VAR));

        //FlowModes
        keywords.put("in", new FlowMode(FlowModes.IN));
        keywords.put("out", new FlowMode(FlowModes.OUT));
        keywords.put("inout", new FlowMode(FlowModes.INOUT));

        tokenList = new TokenList();

    }

    public ITokenList scan(CharSequence cs) throws LexicalError {
        assert cs.length() == 0 || cs.charAt(cs.length() -1) == '\n';

        int state = 0;
        StringBuffer lexAccu = new StringBuffer();
        long numAccu = 0L;
        //Token lastToken = null;

        int lineCounter = 1, posCounterStart = 0, posCounter;

        for (int i = 0; i < cs.length(); i++) {
            char c = cs.charAt(i);
            if (c == '\n') {
                posCounterStart = i + 1;
            }
            posCounter = i - posCounterStart + 1;
            switch (state) {
                case 0 -> {
                    if (Character.isDigit(c)) {
                        state = 1;
                        numAccu = Character.digit(c, 10);
                    } else if (Character.isLetter(c)) {
                        state = 2;
                        i = i - 1;
                    } else if (isSymbol(c)) {
                        state = 3;
                        i = i - 1;
                    } else if (c == ' ' || c == '\n' || c == '\r') {
                        if (c == '\n') {
                            lineCounter++;
                            posCounterStart = i + 1;
                        }
                    } else {
                        throw new LexicalError("Unknown character: '" + c + "' Line: " + lineCounter + ", Position: " + posCounter);
                    }
                }
                case 1 -> {
                    if (Character.isDigit(c)) {
                        state = 1;
                        int digit = Character.digit(c, 10);
                        numAccu = numAccu * 10 + digit;
                        if (numAccu > Integer.MAX_VALUE) {
                            throw new LexicalError("Integer literal is too large!" + " Line: " + lineCounter + ", Position: " + posCounter);
                        }
                    } else {
                        state = 0;
                        i = i - 1;
                        Literal intLiteralToken = new Literal((int) numAccu);
                        tokenList.add(intLiteralToken);
                    }
                }
                case 2 -> {
                    if (Character.isLetter(c) || Character.isDigit(c) || '\'' == c || '_' == c) {
                        state = 2;
                        lexAccu.append(c);
                    } else if (checkIfToken(lexAccu.toString())) {
                        state = 0;
                        i = i - 1;
                        lexAccu.delete(0, lexAccu.length());
                    } else {
                        state = 0;
                        i = i - 1;
                        Ident ident = new Ident(lexAccu.toString());
                        tokenList.add(ident);
                        lexAccu.delete(0, lexAccu.length());
                    }
                }
                case 3 -> {
                    if (isSymbol(c) && lexAccu.length() < 2) {
                        state = 3;
                        lexAccu.append(c);
                        if ("//".equals(lexAccu.toString())) {
                            state = 4;
                            lexAccu.delete(0, lexAccu.length());
                        }
                    } else if (checkIfToken(lexAccu.toString())) {
                        state = 0;
                        i = i - 1;
                        lexAccu.delete(0, lexAccu.length());
                    } else {
                        lexAccu = lexAccu.delete(1, lexAccu.length());
                        if (checkIfToken(lexAccu.toString())) {
                            state = 0;
                            i = i - 2;
                            lexAccu.delete(0, lexAccu.length());
                        }
                    }
                }
                case 4 -> {
                    if (c == '\n') {
                        lineCounter++;
                        state = 0;
                    }
                }
            }
        }
        assert state == 0;
        tokenList.add(new Token(Terminals.SENTINEL));
        return tokenList;
    }

    private boolean isSymbol(char c) {
        String string = String.valueOf(c);
        return symbols.contains(string);
    }

    private boolean checkIfToken(String string) {
        if (keywords.containsKey(string)) {
            Token token = keywords.get(string);
            tokenList.add(token);
            return true;
        }
        return false;
    }
}
