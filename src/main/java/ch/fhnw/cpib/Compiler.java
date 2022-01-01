package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Helper.*;
import ch.fhnw.cpib.VM.*;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.Parser;
import ch.fhnw.cpib.Scanner.Scanner;
import ch.fhnw.cpib.Token.ITokenList;

import java.io.*;

public class Compiler {
    public static void main(String[] args) throws FileNotFoundException {
        // read iml code sample
        Scanner scanner = new Scanner();
        ConsoleWriter cw = new ConsoleWriter();

        /**
         * Read IML code
         */
        final String iml = "Factorial";
        // final String iml = "Fibonacci_Position";
        // final String iml = "Switchception";

        StringBuilder imlCode = IMLReader.read(iml + ".iml");
        ITokenList tokenList = null;

        /**
        * Scanner
        */
        try {
            tokenList = scanner.scan(imlCode);
        } catch (LexicalError e) {
            System.out.println("Scanner error");
            e.printStackTrace();
        }

        /**
         * Tokenlist
         */
        assert tokenList != null;
        Parser parser = new Parser(tokenList);
        AbsSyn absSyn = null;

        cw.write("INPUT (IML)", String.valueOf(imlCode));
        cw.write("TokenList (horizontal)", tokenList.toString());
        cw.write("TokenList (vertical)");
        tokenList.print();
        cw.write("OUTPUT (Parser)");

        /**
         * Parser
         */
        try { absSyn = parser.parse(); }
        catch (GrammarError e) {
            System.out.println("Parser error"); e.printStackTrace(); }
        catch (NameAlreadyDeclaredError | NameAlreadyGloballyDeclaredError | NameNotDeclaredError e) {
            System.out.println("Namespace check error"); e.printStackTrace(); }
        catch (LRValError e) {
            System.out.println("LRVal check error"); e.printStackTrace(); }
        catch (InvalidParamCountError e) {
            System.out.println("Param check error"); e.printStackTrace(); }
        catch (AlreadyInitializedError | NotInitializedError | GlobalInitializationProhibitedError e) {
            System.out.println("Init check error"); e.printStackTrace(); }
        catch (DefaultCaseBoolOverkillError | CaseAlreadyDeclaredError e) {
            System.out.println("Switch case error"); e.printStackTrace(); }
        catch (TypeCheckError e) {
            System.out.println("Type check error"); e.printStackTrace(); }
        catch (CannotAssignToConstError e) {
            System.out.println("Const error"); e.printStackTrace(); }


        /**
         * Start VM
         */
        assert absSyn != null;
        try {
            cw.write("Generate code array");
            ICodeArray codeArray = absSyn.getCodeArray();
            System.out.println(codeArray.toString());
            cw.write("Run programm...");
            new VirtualMachine(codeArray, 65536);
        } catch (IVirtualMachine.ExecutionError | ICodeArray.CodeTooSmallError e) {
            System.out.println("VM error");
            e.printStackTrace();
        }



    }


}
