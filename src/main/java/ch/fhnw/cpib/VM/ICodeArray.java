// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package ch.fhnw.cpib.VM;

import ch.fhnw.cpib.VM.IInstructions.IInstr;

public interface ICodeArray {
    // for the COMPILER:
    // a CodeTooSmallError indicates that the code is too small
    // to hold the complete program
    class CodeTooSmallError extends Exception {}
    void put(int loc, IInstr instr) throws CodeTooSmallError;
    void resize();
    String toString();

    // for the VM:
    int getSize();
    IInstr get(int loc);
    void fromString();
}
