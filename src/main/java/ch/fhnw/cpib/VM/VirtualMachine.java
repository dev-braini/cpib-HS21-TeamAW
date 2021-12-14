// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package ch.fhnw.cpib.VM;

import ch.fhnw.cpib.VM.IInstructions.*;

public class VirtualMachine implements IVirtualMachine {

    // remove following declaration if use ep
    private static final String SP_OVER_HP=
        "Stack pointer over heap pointer.";

    private static final String EP_OVER_HP=
        "Extreme pointer over heap pointer.";

    // stores the program
    private IExecInstr[] code;

    // stores the data
    // - stack: index 0 upto sp-1
    // - heap: index store.length - 1 downto hp+1
    private Data.IBaseData[] store;

    // program counter
    private int pc;

    // stack pointer
    // - points to the first currently free location on the stack
    // - stack grows from 0 upwards
    private int sp;

    // extreme pointer
    // - points to the first always free location on the stack
    private int ep;

    // heap pointer
    // - points to the first free location on the heap
    // - heap grows from store.length - 1 downwards
    private int hp;

    // frame pointer
    // - provides a reference to each routine incarnation
    private int fp;

    public VirtualMachine(ICodeArray code, int storeSize)
            throws ExecutionError
    {
        loadProgram(code);
        store= new Data.IBaseData[storeSize];
        execute();
    }

    // pre
    // - (forall i | 0 <= i < code.getSize() : code.get(i) != null)
    private void loadProgram(ICodeArray code) {
        this.code= new IExecInstr[code.getSize()];
        for (int i= 0; i < code.getSize(); i++) {
            this.code[i]= code.get(i).toExecInstr(this);
        }
    }

    private void execute() throws ExecutionError
    {
        pc= 0;
        sp= 0;
        ep= 0;
        hp= store.length - 1;
        fp= 0;
        while (pc > -1)
        {
            code[pc].execute();
        }
    }

    // stop instruction
    public class StopExec extends Stop implements IExecInstr {
        public void execute()
        {
            pc= -1;
        }
    }

    // stack instruction
    public class DupExec extends Dup implements IExecInstr {
        public void execute() throws ExecutionError
        {
            // remove following check if use ep
            if (sp > hp) { throw new ExecutionError(SP_OVER_HP); }
            store[sp]= store[sp - 1].copy();
            sp= sp + 1;
            pc= pc + 1;
        }
    }

    // routine operations

    public class AllocBlockExec extends AllocBlock implements IExecInstr {
        public AllocBlockExec(int size) { super(size); }

        public void execute() throws ExecutionError
        {
            sp= sp + size;
            // remove following check if use ep
            if (sp > hp + 1) { throw new ExecutionError(SP_OVER_HP); }
            pc= pc + 1;
        }
    }

    public class AllocStackExec extends AllocStack implements IExecInstr {
        public AllocStackExec(int maxSize) { super(maxSize); }

        public void execute() throws ExecutionError
        {
            ep= sp + maxSize;
            if (ep > hp + 1) { throw new ExecutionError(EP_OVER_HP); }
            pc= pc + 1;
        }
    }

    public class CallExec extends Call implements IExecInstr {
        public CallExec(int routAddress) { super(routAddress); }

        public void execute() throws ExecutionError
        {
            // remove following check if use ep
            if (sp + 2 > hp) { throw new ExecutionError(SP_OVER_HP); }
            store[sp]= Data.intNew(fp);
            store[sp + 1]= Data.intNew(ep);
            store[sp + 2]= Data.intNew(pc);
            fp= sp;
            sp= sp + 3;
            pc= routAddress;
        }
    }

    public class ReturnExec extends Return implements IExecInstr {
        public ReturnExec(int size) { super(size); }

        public void execute() throws ExecutionError
        {
            sp= fp - size;
            pc= Data.intGet(store[fp + 2]) + 1;
            ep= Data.intGet(store[fp + 1]);
            fp= Data.intGet(store[fp]);
            if (ep > hp + 1) { throw new ExecutionError(EP_OVER_HP); }
        }
    }

    // load immediate value (value -> stack)
    public class LoadImIntExec extends LoadImInt implements IExecInstr {
        public LoadImIntExec(int value) { super(value); }

        public void execute() throws ExecutionError
        {
            // remove following check if use ep
            if (sp > hp) { throw new ExecutionError(SP_OVER_HP); }
            store[sp]= Data.intNew(value);
            sp= sp + 1;
            pc= pc + 1;
        }
    }

    // load immediate value (value -> stack)
    // added by TeamAW
    public class LoadImBoolExec extends LoadImBool implements IExecInstr {
        public LoadImBoolExec(boolean value) { super(value); }

        public void execute() throws ExecutionError
        {
            // remove following check if use ep
            if (sp > hp) { throw new ExecutionError(SP_OVER_HP); }
            store[sp]= Data.boolNew(value);
            sp= sp + 1;
            pc= pc + 1;
        }
    }

    // load address relative to frame pointer (address -> stack)
    public class LoadAddrRelExec extends LoadAddrRel implements IExecInstr {
        public LoadAddrRelExec(int relAddress) { super(relAddress); }

        public void execute() throws ExecutionError
        {
            // remove following check if use ep
            if (sp > hp) { throw new ExecutionError(SP_OVER_HP); }
            store[sp]= Data.intNew(fp + relAddress);
            sp= sp + 1;
            pc= pc + 1;
        }
    }


    // load address absolute (address -> stack)
    // added by TeamAW
    public class LoadAddrAbsExec extends LoadAddrAbs implements IExecInstr {
        public LoadAddrAbsExec(int absAddress) { super(absAddress); }

        public void execute() throws ExecutionError
        {
            // remove following check if use ep
            if (sp > hp) { throw new ExecutionError(SP_OVER_HP); }
            store[sp]= Data.intNew(absAddress);
            sp= sp + 1;
            pc= pc + 1;
        }
    }

    // load instruction with address on stack
    // load (inside stack -> top of stack) operation
    public class DerefExec extends Deref implements IExecInstr {
        public void execute()
        {
            int address= Data.intGet(store[sp - 1]);
            store[sp - 1]= store[address];
            pc= pc + 1;
        }
    }

    // store instruction with address on stack
    // store (top of stack -> inside stack) operation
    public class StoreExec extends Store implements IExecInstr {
        public void execute()
        {
            int address= Data.intGet(store[sp - 2]);
            store[address]= store[sp - 1];
            sp= sp - 2;
            pc= pc + 1;
        }
    }

    // monadic instructions

    public class NegIntExec extends NegInt implements IExecInstr {
        public void execute()
        {
            store[sp-1]= Data.intInv(store[sp-1]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class NegBoolExec extends NegBool implements IExecInstr {
        public void execute()
        {
            store[sp-1]= Data.boolInv(store[sp-1]);
            pc= pc + 1;
        }
    }

    // dyadic instructions

    public class AddIntExec extends AddInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intAdd(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class SubIntExec extends SubInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intSub(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class MultIntExec extends MultInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intMult(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class DivTruncIntExec extends DivTruncInt implements IExecInstr {
        public void execute() throws ExecutionError
        {
            sp= sp - 1;
            store[sp-1]= Data.intDivTrunc(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class ModTruncIntExec extends ModTruncInt implements IExecInstr {
        public void execute() throws ExecutionError
        {
            sp= sp - 1;
            store[sp-1]= Data.intModTrunc(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class DivEuclIntExec extends DivEuclInt implements IExecInstr {
        public void execute() throws ExecutionError
        {
            sp= sp - 1;
            store[sp-1]= Data.intDivEucl(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class ModEuclIntExec extends ModEuclInt implements IExecInstr {
        public void execute() throws ExecutionError
        {
            sp= sp - 1;
            store[sp-1]= Data.intModEucl(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class DivFloorIntExec extends DivFloorInt implements IExecInstr {
        public void execute() throws ExecutionError
        {
            sp= sp - 1;
            store[sp-1]= Data.intDivFloor(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class ModFloorIntExec extends ModFloorInt implements IExecInstr {
        public void execute() throws ExecutionError
        {
            sp= sp - 1;
            store[sp-1]= Data.intModFloor(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class EqIntExec extends EqInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intEQ(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class NeIntExec extends NeInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intNE(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class GtIntExec extends GtInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intGT(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class LtIntExec extends LtInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intLT(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class GeIntExec extends GeInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intGE(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    public class LeIntExec extends LeInt implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.intLE(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class AndBoolExec extends AndBool implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.boolAnd(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class OrBoolExec extends OrBool implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.boolOr(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class CAndBoolExec extends CAndBool implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.boolCAnd(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // added by TeamAW
    public class COrBoolExec extends COrBool implements IExecInstr {
        public void execute()
        {
            sp= sp - 1;
            store[sp-1]= Data.boolCOr(store[sp-1], store[sp]);
            pc= pc + 1;
        }
    }

    // jump instructions
    public class UncondJumpExec extends UncondJump implements IExecInstr {
        public UncondJumpExec(int jumpAddr) { super(jumpAddr); }

        public void execute()
        {
            pc= jumpAddr;
        }
    }

    public class CondJumpExec extends CondJump implements IExecInstr {
        public CondJumpExec(int jumpAddr) { super(jumpAddr); }

        public void execute()
        {
            sp= sp - 1;
            pc= (Data.boolGet(store[sp])) ? pc + 1 : jumpAddr;
        }
    }

    // added by TeamAW
    public class RelJumpExec extends RelJump implements IExecInstr {
        public RelJumpExec(int jumpAddr) { super(jumpAddr); }

        public void execute()
        {
            // Read index from store
            int index = Data.intGet(store[sp - 1]);
            sp= sp - 1;
            pc= jumpAddr + index;
        }
    }

    // input (input -> stack) and output (stack -> output) instructions

    public class InputBoolExec extends InputBool implements IExecInstr {
        public InputBoolExec(String indicator) { super(indicator); }

        public void execute() throws ExecutionError
        {
            System.out.print("? " + indicator + " : bool = ");
            boolean input= InputUtility.readBool();
            int address= Data.intGet(store[sp - 1]);
            store[address]= Data.boolNew(input);
            sp= sp - 1;
            pc= pc + 1;
        }
    }

    public class InputIntExec extends InputInt implements IExecInstr {
        public InputIntExec(String indicator) { super(indicator); }

        public void execute() throws ExecutionError
        {
            System.out.print("? " + indicator + " : int = ");
            int input= InputUtility.readInt();
            int address= Data.intGet(store[sp - 1]);
            store[address]= Data.intNew(input);
            sp= sp - 1;
            pc= pc + 1;
        }
    }

    public class OutputBoolExec extends OutputBool implements IExecInstr {
        public OutputBoolExec(String indicator) { super(indicator); }

        public void execute()
        {
            sp= sp - 1;
            boolean output= Data.boolGet(store[sp]);
            System.out.println("! " + indicator + " : bool = " + output);
            pc= pc + 1;
        }
    }

    public class OutputIntExec extends OutputInt implements IExecInstr {
        public OutputIntExec(String indicator) { super(indicator); }

        public void execute()
        {
            sp= sp - 1;
            int output= Data.intGet(store[sp]);
            System.out.println("! " + indicator + " : int = " + output);
            pc= pc + 1;
        }
    }
}
