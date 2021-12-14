package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.*;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.CpsCmd;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Param;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Program;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.TypedIdent;
import ch.fhnw.cpib.Token.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

public interface IConcSyn {
    interface IProduction {
        default String toString(String indent) {
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

    interface ICaseNTS extends IProduction {
        ArrayList<Case> toAbsSyn(ArrayList<Case> tmp);
    }

    interface ICmd extends IProduction {
        IAbsSyn.ICmd toAbsSyn();
    }

    interface ICondExprNTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface ICpsCmd extends IProduction {
        CpsCmd toAbsSyn();
    }

    interface ICpsCmdNTS extends IProduction {
        ArrayList<IAbsSyn.ICmd> toAbsSyn(ArrayList<IAbsSyn.ICmd> tmp);
    }

    interface ICpsDecl extends IProduction {
        ArrayList<IAbsSyn.IDecl> toAbsSyn();
    }

    interface ICpsDeclNTS extends IProduction {
        ArrayList<IAbsSyn.IDecl> toAbsSyn(ArrayList<IAbsSyn.IDecl> tmp);
    }

    interface ICpsStoDecl extends IProduction {
        ArrayList<StoDecl> toAbsSyn();
    }

    interface ICpsStoDeclNTS extends IProduction {
        ArrayList<StoDecl> toAbsSyn(ArrayList<StoDecl> tmp);
    }

    interface IDecl extends IProduction {
        IAbsSyn.IDecl toAbsSyn();
    }

    interface IDefaultCaseNTS extends IProduction {
        DefaultCase toAbsSyn();
    }

    interface IExpr extends IProduction {
        IAbsSyn.IExpr toAbsSyn();
    }

    interface IExprList extends IProduction {
        ArrayList<IAbsSyn.IExpr> toAbsSyn();
    }

    interface IExprListLparenNTS extends IProduction {
        ArrayList<IAbsSyn.IExpr> toAbsSyn();
    }

    interface IExprListNTS extends IProduction {
        ArrayList<IAbsSyn.IExpr> toAbsSyn(ArrayList<IAbsSyn.IExpr> tmp);
    }

    interface IFactor extends IProduction {
        IAbsSyn.IFactor toAbsSyn();
    }

    interface IFactorNTS extends IProduction {
        IAbsSyn.IFactor toAbsSyn(Ident ident);
    }

    interface IFunDecl extends IProduction {
        IAbsSyn.IDecl toAbsSyn();
    }

    interface IFunDeclNTS extends IProduction {
        ArrayList<StoDecl> toAbsSyn();
    }

    interface IGlobalNTS extends IProduction {
        ArrayList<IAbsSyn.IDecl> toAbsSyn();
    }

    interface IIfelseNTS extends IProduction {
        CpsCmd toAbsSyn();
    }

    interface IParam extends IProduction {
        Param toAbsSyn();
    }

    interface IParamNTS extends IProduction {
        ArrayList<Param> toAbsSyn(ArrayList<Param> tmp);
    }

    interface IParamList extends IProduction {
        ArrayList<Param> toAbsSyn();
    }

    interface IParamListNTS extends IProduction {
        ArrayList<Param> toAbsSyn();
    }

    interface IProcDecl extends IProduction {
        IAbsSyn.IDecl toAbsSyn();
    }

    interface IProcDeclNTS extends IProduction {
        ArrayList<StoDecl> toAbsSyn();
    }

    interface IProgram extends IProduction {
        Program toAbsSyn();
    }

    interface IStoDecl extends IProduction {
        StoDecl toAbsSyn();
    }
    interface ITerm0 extends IProduction {
        IAbsSyn.IExpr toAbsSyn();
    }

    interface ITerm0NTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface ITerm1 extends IProduction {
        IAbsSyn.IExpr toAbsSyn();
    }

    interface ITerm1NTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface ITerm2 extends IProduction {
        IAbsSyn.IExpr toAbsSyn();
    }

    interface ITerm2NTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface ITerm3 extends IProduction {
        IAbsSyn.IExpr toAbsSyn();
    }

    interface ITerm3NTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface ITypedIdent extends IProduction {
        TypedIdent toAbsSyn();
    }


    interface IChangeModeNTS extends IProduction {
        ChangeMode toAbsSyn();
    }

    interface IMechModeNTS extends IProduction {
        MechMode toAbsSyn();
    }

    interface IMonadicOpr extends IProduction {
        MonadicOpr toAbsSyn();
    }
}
