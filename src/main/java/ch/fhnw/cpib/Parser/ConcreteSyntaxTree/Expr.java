package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.lang.reflect.Field;

// expr ::= term0 condExprNTS
public class Expr implements IConcSyn.IExpr {
    private final IConcSyn.ITerm0 term0;
    private final IConcSyn.ICondExprNTS condExprNTS;

    public Expr(final IConcSyn.ITerm0 term0, final IConcSyn.ICondExprNTS condExprNTS) {
        this.term0 = term0;
        this.condExprNTS = condExprNTS;
    }

    @Override
    public IAbsSyn.IExpr toAbsSyn() {
        return condExprNTS.toAbsSyn(term0.toAbsSyn());
    }

    @Override
    public String toString(String indent) {
        String subindent = indent + " ";
        String s = "";
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if(field.getType() == IToken.class) {
                    s += indent + field.get(this) + "\n";
                } else if (field.get(this) instanceof IConcSyn.IProduction) {
                    s += ((IConcSyn.IProduction)field.get(this)).toString(subindent);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return s;
    }
}
