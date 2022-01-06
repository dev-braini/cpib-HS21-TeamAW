// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

/**
 * sdfsdf
 * sdfsdf
 */

package ch.fhnw.cpib.VM;

public class Data
{
    interface IBaseData
    {
        IBaseData copy();
    }

    static class IntData implements IBaseData
    {
        private final int i;
        IntData(int i) { this.i= i; }
        int getData() { return i; }
        public IntData copy() { return intCopy(this); }
    }

    static IntData intNew(int i)
    {
        return new IntData(i);
    }

    static int intGet(IBaseData a)
    {
        return ((IntData)a).getData();
    }

    static IntData intCopy(IBaseData a)
    {
        return intNew(intGet(a));
    }

    // coding booleans as integers
    static IntData boolNew(boolean b)
    {
        return intNew(b ? 1 : 0);
    }

    // coding booleans as integers
    static boolean boolGet(IBaseData a)
    {
        return ((IntData)a).getData() != 0;
    }

    static class FloatData implements IBaseData
    {
        private final float f;
        FloatData(float f) { this.f= f; }
        float getData() { return f; }
        public FloatData copy() { return floatCopy(this); }
    }

    static FloatData floatNew(float f)
    {
        return new FloatData(f);
    }

    static float floatGet(IBaseData a)
    {
        return ((FloatData)a).getData();
    }

    static FloatData floatCopy(IBaseData a)
    {
        return floatNew(floatGet(a));
    }

    static IntData intInv(IBaseData a)
    {
        return intNew(-intGet(a));
    }

    // added by TeamAW
    static IntData boolInv(IBaseData a)
    {
        return boolNew(intGet(a) != 1);
    }

    static FloatData floatInv(IBaseData a)
    {
        return floatNew(-floatGet(a));
    }

    static IntData intAdd(IBaseData a, IBaseData b)
    {
        return intNew(intGet(a) + intGet(b));
    }

    static IntData intSub(IBaseData a, IBaseData b)
    {
        return intNew(intGet(a) - intGet(b));
    }

    static IntData intMult(IBaseData a, IBaseData b)
    {
        return intNew(intGet(a) * intGet(b));
    }

    static IntData intDivTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            return intNew(intGet(a) / intGet(b));
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer division by zero.");
        }
    }

    static IntData intModTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            return intNew(intGet(a) % intGet(b));
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer remainder by zero.");
        }
    }

    // added by TeamAW
    static IntData intDivFloor(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            return intNew(Math.floorDiv(intGet(a), intGet(b)));
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer division by zero.");
        }
    }

    // added by TeamAW
    static IntData intModFloor(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            return intNew(Math.floorMod(intGet(a), intGet(b)));
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer remainder by zero.");
        }
    }

    // added by TeamAW
    static IntData intDivEucl(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            int r = intGet(a) / intGet(b);
            if (intGet(a) < 0 && r * intGet(b) != intGet(a)) {
                r -= java.lang.Math.signum(intGet(b));
            }
            return intNew(r);
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer division by zero.");
        }
    }

    // added by TeamAW
    static IntData intModEucl(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError
    {
        try
        {
            int r = intGet(a) - intGet(intDivEucl(a, b)) * intGet(b);
            return intNew(r);
        }
        catch (ArithmeticException e)
        {
            throw new VirtualMachine.ExecutionError("Integer remainder by zero.");
        }
    }

    static IntData intEQ(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) == intGet(b));
    }

    static IntData intNE(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) != intGet(b));
    }

    static IntData intGT(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) > intGet(b));
    }

    static IntData intLT(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) < intGet(b));
    }

    static IntData intGE(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) >= intGet(b));
    }

    static IntData intLE(IBaseData a, IBaseData b)
    {
        return boolNew(intGet(a) <= intGet(b));
    }

    // added by TeamAW
    static IntData boolAnd(IBaseData a, IBaseData b)
    {
        return boolNew(boolGet(a) & boolGet(b));
    }

    // added by TeamAW
    static IntData boolOr(IBaseData a, IBaseData b)
    {
        return boolNew(boolGet(a) | boolGet(b));
    }

    // added by TeamAW
    static IntData boolCAnd(IBaseData a, IBaseData b)
    {
        return boolNew(boolGet(a) && boolGet(b));
    }

    // added by TeamAW
    static IntData boolCOr(IBaseData a, IBaseData b)
    {
        return boolNew(boolGet(a) || boolGet(b));
    }
}
