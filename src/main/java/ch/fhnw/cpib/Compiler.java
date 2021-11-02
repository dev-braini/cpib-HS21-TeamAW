package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Errors.LexicalError;
import ch.fhnw.cpib.Parser.Parser;
import ch.fhnw.cpib.Scanner.Scanner;
import ch.fhnw.cpib.Token.ITokenList;

public class Compiler {
    /* ATTENTION: IML needs a blank line at the end */
    private static final String IML_TEST_CODE = "//I'm a super program\nprogram Add17\nglobal\n var x:int64\ndo\n  debugin x init;\n  x := x + 17;\n  debugout x\n endprogram \n";

    public static void main(String[] args) throws LexicalError, GrammarError {
        ITokenList tokenList;
        Scanner scanner = new Scanner();
        tokenList = scanner.scan(IML_TEST_CODE);

        System.out.println("INPUT (IML):");
        System.out.println("---------------------");
        System.out.println(IML_TEST_CODE + "\n");

        System.out.println("OUTPUT (ITokenList):");
        System.out.println("---------------------");
        System.out.println(tokenList.toString());
        System.out.println("---------------------");
        tokenList.print();

        Parser parser = new Parser(tokenList);
        parser.parse();
    }
}
