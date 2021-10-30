package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.Ident;

import java.util.ArrayList;

public interface IConcSyn {

    interface IProduction {
        String toString(String indent);
    }

    interface IProgram extends IProduction {
        IAbsSyn.IProgram toAbsSyn();
    }

    interface ICaseNTS extends IProduction {
        ArrayList<IAbsSyn.ICase> toAbsSyn(ArrayList<IAbsSyn.ICase> tmp);
    }

    interface ICmd extends IProduction {
        IAbsSyn.ICmd toAbsSyn();
    }

    /* interface IChangeModeNTS extends IProduction {
        Changemode toAbsSyn();
    } */

    interface IStoDecl extends IProduction{
        IAbsSyn.IStoDecl toAbsSyn();
    }

    interface ITerm0NTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface ITerm0 extends IProduction {
        IAbsSyn.IExpr toAbsSyn();
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
    
    interface IProcDeclNTS extends IProduction {
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn();
    }
    interface ITypedIdent extends IProduction {
        IAbsSyn.ITypedIdent toAbsSyn();
    }

    interface ICpsStoDecl extends IProduction {
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn();
    }

    interface ICpsDeclNTS extends IProduction {
        ArrayList<IAbsSyn.IDecl> toAbsSyn(ArrayList<IAbsSyn.IDecl> tmp);
    }

    interface ICpsDecl extends IProduction {
        ArrayList<IAbsSyn.IDecl> toAbsSyn();
    }

    interface ICpsCmdNTS extends IProduction {
        ArrayList<IAbsSyn.ICmd> toAbsSyn(ArrayList<IAbsSyn.ICmd> tmp);
    }

    /* interface ICpsCmd extends IProduction {
        CpsCmd toAbsSyn();
    } */

    interface ICondExprNTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface IExprListLparenNTS extends IProduction {
        ArrayList<IAbsSyn.IExpr> toAbsSyn();
    }
    interface IExprList extends IProduction {
        ArrayList<IAbsSyn.IExpr> toAbsSyn();
    }

    interface IExpr extends IProduction {
        IAbsSyn.IExpr toAbsSyn();
    }

    interface IDefaultCaseNTS extends IProduction {
        IAbsSyn.IDefaultCase toAbsSyn();
    }

    interface IDecl extends IProduction {
        IAbsSyn.IDecl toAbsSyn();
    }

    interface ICpsStoDeclNTS extends IProduction {
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn(ArrayList<IAbsSyn.IStoDecl> tmp);
    }

    /* interface IMechModeNTS extends IProduction {
        Mechmode toAbsSyn();
    } */

    interface IIfelseNTS extends IProduction {
        IAbsSyn.ICpsCmd toAbsSyn();
    }

    interface IGlobalNTS extends IProduction {
        ArrayList<IAbsSyn.IDecl> toAbsSyn();
    }

    interface IFunDeclNTS extends IProduction {
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn();
    }

    interface IFactorNTS extends IProduction {
        IAbsSyn.IFactor toAbsSyn(Ident ident);
    }
    interface IFactor extends IProduction {
        IAbsSyn.IFactor toAbsSyn();
    }

    interface IExprListNTS extends IProduction {
        ArrayList<IAbsSyn.IExpr> toAbsSyn(ArrayList<IAbsSyn.IExpr> tmp);
    }

    /* interface IMonadicOpr extends IProduction {
        Monadicopr toAbsSyn();
    } */

    interface IParam extends IProduction {
        IAbsSyn.IParam toAbsSyn();
    }
    
    interface IParamListNTS extends IProduction {
        ArrayList<IAbsSyn.IParam> toAbsSyn();
    }

    interface IParamList extends IProduction {
        ArrayList<IAbsSyn.IParam> toAbsSyn();
    }
    
    interface IParamNTS extends IProduction {
        ArrayList<IAbsSyn.IParam> toAbsSyn(ArrayList<IAbsSyn.IParam> tmp);
    }

    interface IProcDecl extends IProduction {
        IAbsSyn.IDecl toAbsSyn();
    }

    interface IRepMULTOPRfactor {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr exprLeft);
    }
}
