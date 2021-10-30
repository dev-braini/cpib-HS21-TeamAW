package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Enums.MechModes;
import ch.fhnw.cpib.Enums.ChangeModes;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.Token.MonadicOpr;

import java.util.ArrayList;

public interface IConcSyn {
    interface ICaseNTS extends IProduction {
        ArrayList<IAbsSyn.ICase> toAbsSyn(ArrayList<IAbsSyn.ICase> tmp);
    }

    interface ICmd extends IProduction {
        IAbsSyn.ICmd toAbsSyn();
    }

    interface ICondExprNTS extends IProduction {
        IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr);
    }

    interface ICpsCmd extends IProduction {
        IAbsSyn.ICpsCmd toAbsSyn();
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
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn();
    }

    interface ICpsStoDeclNTS extends IProduction {
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn(ArrayList<IAbsSyn.IStoDecl> tmp);
    }

    interface IDecl extends IProduction {
        IAbsSyn.IDecl toAbsSyn();
    }

    interface IDefaultCaseNTS extends IProduction {
        IAbsSyn.IDefaultCase toAbsSyn();
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
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn();
    }

    interface IGlobalNTS extends IProduction {
        ArrayList<IAbsSyn.IDecl> toAbsSyn();
    }

    interface IIfelseNTS extends IProduction {
        IAbsSyn.ICpsCmd toAbsSyn();
    }

    interface IParam extends IProduction {
        IAbsSyn.IParam toAbsSyn();
    }

    interface IParamNTS extends IProduction {
        ArrayList<IAbsSyn.IParam> toAbsSyn(ArrayList<IAbsSyn.IParam> tmp);
    }

    interface IParamList extends IProduction {
        ArrayList<IAbsSyn.IParam> toAbsSyn();
    }

    interface IParamListNTS extends IProduction {
        ArrayList<IAbsSyn.IParam> toAbsSyn();
    }

    interface IProcDecl extends IProduction {
        IAbsSyn.IDecl toAbsSyn();
    }

    interface IProcDeclNTS extends IProduction {
        ArrayList<IAbsSyn.IStoDecl> toAbsSyn();
    }

    interface IProduction {
        String toString(String indent);
    }

    interface IProgram extends IProduction {
        IAbsSyn.IProgram toAbsSyn();
    }

    interface IStoDecl extends IProduction{
        IAbsSyn.IStoDecl toAbsSyn();
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
        IAbsSyn.ITypedIdent toAbsSyn();
    }


    interface IChangeModeNTS extends IProduction {
        ChangeModes toAbsSyn();
    }

    interface IMechModeNTS extends IProduction {
        MechModes toAbsSyn();
    }

    interface IMonadicOpr extends IProduction {
        MonadicOpr toAbsSyn();
    }
}
