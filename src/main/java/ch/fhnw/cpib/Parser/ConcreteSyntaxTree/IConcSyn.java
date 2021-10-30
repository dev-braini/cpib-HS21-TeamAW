package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.Token;

import java.util.List;

public interface IConcSyn {

    interface IProduction {
        String toString(String indent);
    }

    interface IOptionalGlobalInits extends IProduction {
        List<Token> toAbsSyn();
    }

    interface IBlockCmd extends IProduction {
        List<IAbsSyn.ICommand> toAbsSyn();
    }

    interface ICmd extends IProduction {
        IAbsSyn.ICommand toAbsSyn();
    }



    interface IDeclaration extends IProduction {
        IAbsSyn.IDeclaration toAbsSyn();
    }

    interface IDeclarations extends IProduction {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IOptionalLocalStorageDeclarations extends IProduction {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IRepeatingOptionalDeclarations extends IProduction {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IRepeatingOptionalStorageDeclarations extends IProduction {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IExpression extends IProduction {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface IExpressionList extends IProduction {
        List<IAbsSyn.IExpression> toAbsSyn();
    }

    interface IFactor extends IProduction {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface IGlobalImport extends IProduction {
        IAbsSyn.IGlobalImport toAbsSyn();
    }

    interface IIdents extends IProduction {
        List<Token> toAbsSyn();
    }

    interface IMonadicOperator extends IProduction {
        Token toAbsSyn();
    }

    interface IOptionalCHANGEMODE extends IProduction {
        Token toAbsSyn();
    }

    interface IOptionalExpressions extends IProduction {
        List<IAbsSyn.IExpression> toAbsSyn();
    }

    interface IOptionalFLOWMODE extends IProduction {
        Token toAbsSyn();
    }

    interface IOptionalGlobalDeclarations extends IProduction {
        List<IAbsSyn.IDeclaration> toAbsSyn();
    }

    interface IOptionalGlobalImports extends IProduction {
        List<IAbsSyn.IGlobalImport> toAbsSyn();
    }

    interface IOptionalIdent extends IProduction {
        IAbsSyn.IExpression toAbsSyn(Token ident);
    }



    interface IOptionalMECHMODE extends IProduction {
        Token toAbsSyn();
    }

    interface IOptionalParameters extends IProduction {
        List<IAbsSyn.IParameter> toAbsSyn();
    }

    interface IOptionalProgramParameters extends IProduction {
        List<IAbsSyn.IProgramParameter> toAbsSyn();
    }

    interface IParameter extends IProduction {
        IAbsSyn.IParameter toAbsSyn();
    }

    interface IParameterList extends IProduction {
        List<IAbsSyn.IParameter> toAbsSyn();
    }

    interface IProgamParameterList  extends IProduction {
        List<IAbsSyn.IProgramParameter> toAbsSyn();
    }

    interface IProgram extends IProduction {
        IAbsSyn.IProgram toAbsSyn();
    }

    interface IRepADDOPRterm3 extends IProduction {
        IAbsSyn.IExpression toAbsSyn(IAbsSyn.IExpression dyadicExpr);
    }

    interface IRepBOOLOPRterm1 extends IProduction {
        IAbsSyn.IExpression toAbsSyn(ITerm1 t1);
    }

    interface IRepeatingOptionalCase extends IProduction {
        List<IAbsSyn.ICase> toAbsSyn();
    }

    interface IRepeatingOptionalCmds extends IProduction {
        List<IAbsSyn.ICommand> toAbsSyn();
    }

    interface IRepeatingOptionalExpressions extends IProduction {
        List<IAbsSyn.IExpression> toAbsSyn();
    }

    interface IRepeatingOptionalGlobalImports extends IProduction {
        List<IAbsSyn.IGlobalImport> toAbsSyn();
    }

    interface IRepeatingOptionalIdents extends IProduction {
        List<Token> toAbsSyn();
    }

    interface IRepeatingOptionalParameters extends IProduction {
        List<IAbsSyn.IParameter> toAbsSyn();
    }

    interface IRepeatingOptionalProgramParameters extends IProduction {
        List<IAbsSyn.IProgramParameter> toAbsSyn();
    }

    interface IRepMULTOPRfactor extends IProduction {
        IAbsSyn.IExpression toAbsSyn(IFactor factor);
    }

    interface IRepRELOPRterm2 extends IProduction{
        IAbsSyn.IExpression toAbsSyn(ITerm2 t2);
    }

    interface ITerm1 extends IProduction {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface ITerm2 extends IProduction {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface ITerm3 extends IProduction {
        IAbsSyn.IExpression toAbsSyn();
    }

    interface ITypeDeclaration extends IProduction {
        IAbsSyn.ITypeDeclaration toAbsSyn();
    }

    interface ITypedIdent extends IProduction {
        IAbsSyn.ITypedIdent toAbsSyn();
    }
}
