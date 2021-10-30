package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.Token;

import java.util.List;

public interface IConcSyn {

    interface IOptionalGlobalInits extends IConcSyn {
        List<Token> toAbsSyn();
    }

    interface IBlockCmd extends IConcSyn {
        List<IAbsSyn.ICommand> toAbsSyn();
    }

    interface ICmd extends IConcSyn {
        IAbsSyn.ICommand toAbsSyn();
    }



    interface IDeclaration extends IConcSyn {
        IAbsSyn.IDeclaration toAbsSyn();
    }

    interface IDeclarations extends IConcSyn {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IOptionalLocalStorageDeclarations extends IConcSyn {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IRepeatingOptionalDeclarations extends IConcSyn {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IRepeatingOptionalStorageDeclarations extends IConcSyn {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IExpression extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface IExpressionList extends IConcSyn {
        List<IAbsSyn.IExpression> toAbsSyn();
    }

    interface IFactor extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface IGlobalImport extends IConcSyn {
        IAbsSyn.IGlobalImport toAbsSyn();
    }

    interface IIdents extends IConcSyn {
        List<Token> toAbsSyn();
    }

    interface IMonadicOperator extends IConcSyn {
        Token toAbsSyn();
    }

    interface IOptionalCHANGEMODE extends IConcSyn {
        Token toAbsSyn();
    }

    interface IOptionalExpressions extends IConcSyn {
        List<IAbsSyn.IExpression> toAbsSyn();
    }

    interface IOptionalFLOWMODE extends IConcSyn {
        Token toAbsSyn();
    }

    interface IOptionalGlobalDeclarations extends IConcSyn {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IOptionalGlobalImports extends IConcSyn {
        List<IAbsSyn.IGlobalImport> toAbsSyn();
    }

    interface IOptionalIdent extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn(Token ident);
    }



    interface IOptionalMECHMODE extends IConcSyn {
        Token toAbsSyn();
    }

    interface IOptionalParameters extends IConcSyn {
        List<IAbsSyn.IParameter> toAbsSyn();
    }

    interface IOptionalProgramParameters extends IConcSyn {
        List<IAbsSyn.IProgramParameter> toAbsSyn();
    }

    interface IParameter extends IConcSyn {
        IAbsSyn.IParameter toAbsSyn();
    }

    interface IParameterList extends IConcSyn {
        List<IAbsSyn.IParameter> toAbsSyn();
    }

    interface IProgamParameterList  extends IConcSyn{
        List<IAbsSyn.IProgramParameter> toAbsSyn();
    }

    interface IProgram extends IConcSyn {
        IAbsSyn.IProgram toAbsSyn();
    }

    interface IRepADDOPRterm3 extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn(IAbsSyn.IExpression dyadicExpr);
    }

    interface IRepBOOLOPRterm1 extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn(ITerm1 t1);
    }

    interface IRepeatingOptionalCase extends IConcSyn {
        List<IAbsSyn.ICase> toAbsSyn();
    }

    interface IRepeatingOptionalCmds extends IConcSyn {
        List<IAbsSyn.ICommand> toAbsSyn();
    }

    interface IRepeatingOptionalExpressions extends IConcSyn {
        List<IAbsSyn.IExpression> toAbsSyn();
    }

    interface IRepeatingOptionalGlobalImports extends IConcSyn {
        List<IAbsSyn.IGlobalImport> toAbsSyn();
    }

    interface IRepeatingOptionalIdents extends IConcSyn {
        List<Token> toAbsSyn();
    }

    interface IRepeatingOptionalParameters extends IConcSyn {
        List<IAbsSyn.IParameter> toAbsSyn();
    }

    interface IRepeatingOptionalProgramParameters extends IConcSyn {
        List<IAbsSyn.IProgramParameter> toAbsSyn();
    }

    interface IRepMULTOPRfactor extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn(IFactor factor);
    }

    interface IRepRELOPRterm2 extends IConcSyn{
        IAbsSyn.IExpression toAbsSyn(ITerm2 t2);
    }

    interface ITerm1 extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface ITerm2 extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface ITerm3 extends IConcSyn {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface ITypeDeclaration extends IConcSyn {
        IAbsSyn.ITypeDeclaration toAbsSyn();
    }

    interface ITypedIdent extends IConcSyn {
        IAbsSyn.ITypedIdent toAbsSyn();
    }
}
