package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Errors.LexicalError;
import ch.fhnw.cpib.Parser.Parser;
import ch.fhnw.cpib.Scanner.Scanner;
import ch.fhnw.cpib.Token.ITokenList;

import java.io.*;

public class Compiler {
    /* ATTENTION: IML needs a blank line at the end */
    //private static final String IML_TEST_CODE_FILE = "add17.iml";
    private static final String IML_TEST_CODE_FILE = "doSomeMath.iml";
    public static void main(String[] args) throws LexicalError, GrammarError, FileNotFoundException {

        // read iml code sample
        StringBuilder imlCode = readIMLCode();


        ITokenList tokenList;
        Scanner scanner = new Scanner();
        tokenList = scanner.scan(imlCode);

        System.out.println("+-----------------------+");
        System.out.println("| INPUT (IML):          |");
        System.out.println("+-----------------------+");
        System.out.println(imlCode + "\n");

        System.out.println("+-----------------------+");
        System.out.println("| OUTPUT (ITokenList):  |");
        System.out.println("+-----------------------+");
        System.out.println(tokenList.toString());
        System.out.println("-------------------------");
        tokenList.print();
        System.out.println("\n");

        System.out.println("+-----------------------+");
        System.out.println("| OUTPUT (Parser):      |");
        System.out.println("+-----------------------+");
        Parser parser = new Parser(tokenList);
        parser.parse();

        System.out.println("\n");
        System.out.println("+-----------------------+");
        System.out.println("| Concrete Syntax Tree: |");
        System.out.println("+-----------------------+");

        System.out.println("\n");
        System.out.println("+-----------------------+");
        System.out.println("| Abstract Syntax Tree: |");
        System.out.println("+-----------------------+");
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
