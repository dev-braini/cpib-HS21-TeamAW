package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Errors.LexicalError;
import ch.fhnw.cpib.Helper.ConsoleWriter;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.Parser;
import ch.fhnw.cpib.Scanner.Scanner;
import ch.fhnw.cpib.Token.ITokenList;

import java.io.*;

public class Compiler {
    /* ATTENTION: IML needs a blank line at the end */
    private static final String IML_TEST_CODE_FILE = "add17.iml";
    // private static final String IML_TEST_CODE_FILE = "doSomeMath.iml";
    public static void main(String[] args) throws LexicalError, GrammarError, FileNotFoundException {
        // read iml code sample
        StringBuilder imlCode = readIMLCode();

        ITokenList tokenList = null;
        Scanner scanner = new Scanner();
        ConsoleWriter cw = new ConsoleWriter();

        try {
            tokenList = scanner.scan(imlCode);
        } catch (LexicalError e) {
            System.out.println("Scanner error");
            e.printStackTrace();
        }

        Parser parser = new Parser(tokenList);
        AbsSyn absSyn = null;

        cw.write("INPUT (IML)", String.valueOf(imlCode));
        cw.write("TokenList (horizontal)", tokenList.toString());
        cw.write("TokenList (vertical)");
        tokenList.print();
        cw.write("OUTPUT (Parser)");
        absSyn = parser.parse();
    }

    private static StringBuilder readIMLCode() throws FileNotFoundException {
        String filePath = "src/main/resources/IML_samples/" + IML_TEST_CODE_FILE;
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        BufferedReader bufferedReader = null;
        String line;
        StringBuilder s = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                s.append(line).append('\n');
            }
        } catch (IOException e) {
            System.out.println("Error while reading iml code sample");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        return s;
    }
}
