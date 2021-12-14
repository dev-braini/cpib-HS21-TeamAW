package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray;

import java.util.HashMap;

public interface IAbsSyn {

    interface IAbsSynTreeNode {
        String toString(String indent);
        void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace) throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError;
        void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError;
        void doTypeChecking() throws TypeCheckError;
        void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError;
        void setInit(TypedIdent ident);
        void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly) throws ICodeArray.CodeTooSmallError;
    }

    interface ICmd extends IAbsSynTreeNode {

    }

    interface IStoDecl extends IDecl{

    }

    interface IExpr extends IAbsSynTreeNode {
        Type getType();
        Types getTypeValue();
        LRVal getLRValue();
    }

    interface IDecl extends IAbsSynTreeNode {
        String getIdentString();
    }

    interface IDefaultCase {

    }

    interface IFactor extends IExpr {

    }

    interface IParam {

    }

    interface IProgram {

    }

    interface ICpsCmd {

    }

    interface ITypedIdent {

    }

    interface ICase {

    }
}
