package ch.fhnw.cpib;

import ch.fhnw.cpib.Errors.LexicalError;
import ch.fhnw.cpib.Scanner.Scanner;

public class Compiler {
    public Compiler() {

    }

    public static void main(String[] args) throws LexicalError {
        System.out.println("Hello Manuel, Hi Markus!");

        Scanner scanner = new Scanner();
        scanner.scan("");
    }
}
