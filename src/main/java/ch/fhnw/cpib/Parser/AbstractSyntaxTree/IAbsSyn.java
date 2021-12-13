package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Parser.ConcreteSyntaxTree.TypedIdent;
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

    interface ICmd {

    }

    interface IStoDecl {

    }

    interface IExpr {
        public Type getType();
        public LRVal getLRValue();
    }

    interface IDecl {
        public String getIdentString();
    }

    interface IDefaultCase {

    }

    interface IFactor {

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
