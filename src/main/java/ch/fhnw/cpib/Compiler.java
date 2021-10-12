package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.LexicalError;
import ch.fhnw.cpib.Scanner.Scanner;
import ch.fhnw.cpib.Token.ITokenList;

import java.io.*;

public class Compiler {
    private static String IML_TEST_CODE = "program Add17()\nglobal\n  var x:int32\ndo\n  debugin x init;\n  x := x + 17;\n  debugout x\nendprogram";

    public static void main(String[] args) throws LexicalError, IOException {
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
    }
}
