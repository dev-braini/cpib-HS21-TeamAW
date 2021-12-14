package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Helper.ConsoleWriter;
import ch.fhnw.cpib.Helper.IMLReader;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.Parser;
import ch.fhnw.cpib.Scanner.Scanner;
import ch.fhnw.cpib.Token.ITokenList;

import java.io.*;

public class Compiler {
    public static void main(String[] args) throws FileNotFoundException, NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, NameNotDeclaredError, CaseAlreadyDeclaredError, InvalidParamCountError, LRValError, DefaultCaseBoolOverkillError, AlreadyInitializedError {
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
        try {
            absSyn = parser.parse();
        }
        catch (GrammarError e) { System.out.println("Parser error"); e.printStackTrace(); }
        /* catch (NameAlreadyDeclaredError e) { System.out.println("Namespace check error"); e.printStackTrace(); }
        catch (NameNotDeclaredError e) { System.out.println("Namespace check error"); e.printStackTrace(); }
        catch (NameAlreadyGloballyDeclaredError e) { System.out.println("Namespace check error"); e.printStackTrace(); }
        catch (LRValError e) { System.out.println("LRVal check error"); e.printStackTrace(); }
        catch (InvalidParamCountError e) { System.out.println("Param check error"); e.printStackTrace(); }
        catch (AlreadyInitializedError e) { System.out.println("Init check error"); e.printStackTrace(); }
        catch (DefaultCaseBoolOverkillError e) { System.out.println("Switch case error"); e.printStackTrace(); }
        catch (CaseAlreadyDeclaredError e) { System.out.println("Switch case error"); e.printStackTrace(); }
        catch (TypeCheckError e) { System.out.println("Type check error"); e.printStackTrace(); }
        catch (NotInitializedError e) { System.out.println("Init check error"); e.printStackTrace(); }
        catch (GlobalInitializationProhibitedError e) { System.out.println("Init check error"); e.printStackTrace(); }
        catch (CannotAssignToConstError e) { System.out.println("Const error"); e.printStackTrace(); }
        */

        assert absSyn != null;
        cw.write("INPUT (IML)");
        // absSyn.doScopeChecking();
    }


}
