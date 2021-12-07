// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package ch.fhnw.cpib.VM;

import ch.fhnw.cpib.VM.IInstructions.IInstr;

public class CodeArray implements ICodeArray {
    private IInstr[] code;
    private int size;

    public CodeArray(int size) {
        this.code= new IInstr[size];
        this.size= size;
    }

    public int getSize() {
        return size;
    }

    public void put(int loc, IInstr instr) throws CodeTooSmallError {
        if (loc < code.length) {
            code[loc]= instr;
        }
        else {
            throw new CodeTooSmallError();
        }
    }

    public IInstr get(int loc) {
        return code[loc];
    }

    public void resize() {
        int s= 0;
        while (s < code.length && code[s] != null) {
            s++;
        }
        IInstr[] c= new IInstr[s];
        for (int i= 0; i < s; i++) {
            c[i]= code[i];
        }
        code= c;
        size= s;
    }

    public String toString()
    {
        StringBuffer b= new StringBuffer();
        for (int i= 0; i < code.length; i++)
        {
            if (code[i] != null)
            {
                b.append(i + ": " + code[i] + "\n");
            }
        }
        return b.toString();
    }

    public void fromString() {
        // to be implemented
    }
}
