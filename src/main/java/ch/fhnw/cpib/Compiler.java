package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Errors.LexicalError;
import ch.fhnw.cpib.Helper.ConsoleWriter;
import ch.fhnw.cpib.Helper.IMLReader;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.Parser;
import ch.fhnw.cpib.Scanner.Scanner;
import ch.fhnw.cpib.Token.ITokenList;

import java.io.*;

public class Compiler {
    public static void main(String[] args) throws GrammarError, FileNotFoundException {
        // read iml code sample
        Scanner scanner = new Scanner();
        ConsoleWriter cw = new ConsoleWriter();

        StringBuilder imlCode = IMLReader.read("add17.iml");
        ITokenList tokenList = null;

        try {
            tokenList = scanner.scan(imlCode);
        } catch (LexicalError e) {
            System.out.println("Scanner error");
            e.printStackTrace();
        }


        assert tokenList != null;
        Parser parser = new Parser(tokenList);
        AbsSyn absSyn = null;

        cw.write("INPUT (IML)", String.valueOf(imlCode));
        cw.write("TokenList (horizontal)", tokenList.toString());
        cw.write("TokenList (vertical)");
        tokenList.print();
        cw.write("OUTPUT (Parser)");
        absSyn = parser.parse();
    }


}
