package ch.fhnw.cpib.Parser;

import ch.fhnw.cpib.Enums.Terminals;
import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.ConcreteSyntaxTree.*;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.ITokenList;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * I'm a parser
 */

public class Parser implements IParser {
    private final ITokenList tokenList;
    private IToken token;
    private Terminals terminal;

    public Parser(ITokenList tokenList){
        this.tokenList = tokenList;
        this.tokenList.reset();
        token = tokenList.nextToken();
        terminal = token.getTerminal();
    }

    private IToken consume(Terminals expectedTerminal) throws GrammarError {
        if (terminal == expectedTerminal) {
            IToken consumedToken = token;
            if (terminal != Terminals.SENTINEL) {
                token = tokenList.nextToken();
                terminal = token.getTerminal();
            }
            return consumedToken;
        } else {
            throw GrammarException(expectedTerminal);
        }
    }

    public AbsSyn parse() throws GrammarError {
        IConcSyn.IProgram program = program();

        // take the last bite
        consume(Terminals.SENTINEL);

        // TODO: Static Analysis

        // TODO: MANDATORY
        // TODO: scope checking
        // TODO: types

        // TODO: OPTIONAL - LUXUS :)
        // TODO: initialization
        // TODO: constants and variables
        // TODO: flow analysis
        // TODO: aliasing analysis

        System.out.println("\n");
        System.out.println("+-----------------------+");
        System.out.println("| Concrete Syntax Tree: |");
        System.out.println("+-----------------------+");
        System.out.println(program.toString(""));

        System.out.println("+-----------------------+");
        System.out.println("| Abstract Syntax Tree: |");
        System.out.println("+-----------------------+");
        AbsSyn absSyn = new AbsSyn(program);
        System.out.println(absSyn);

        return absSyn;
    }

    private GrammarError GrammarException(Terminals... expectedTerminals) {
        String terminals =  Arrays.stream(expectedTerminals).map(Object::toString).collect(Collectors.joining(", "));

        // TODO: Add linenumbers?
        return new GrammarError("Terminal found: " + token.getTerminal() + ", Terminal(s) expected: " + terminals);
    }
    
    /**
     * All "Non Terminal Symbol" parser methods
     */
    // program ::= PROGRAM IDENT globalNTS DO cpsCmd ENDPROGRAM
    private IConcSyn.IProgram program() throws GrammarError {
        if (terminal == Terminals.PROGRAM) {
            System.out.println("program ::= PROGRAM IDENT <globalNTS> DO <cpsCmd> ENDPROGRAM");
            IToken program = consume(Terminals.PROGRAM);
            IToken ident = consume(Terminals.IDENT);
            IConcSyn.IGlobalNTS globalNTS = globalNTS();
            IToken do_ = consume(Terminals.DO);
            IConcSyn.ICpsCmd cpsCmd = cpsCmd();
            IToken endprogram = consume(Terminals.ENDPROGRAM);
            return new Program(program, ident, globalNTS, do_, cpsCmd, endprogram);
        } else {
            throw GrammarException(Terminals.PROGRAM);
        }
    }

    // globalNTS ::= GLOBAL cpsDecl
    // globalNTS ::= ε
    private IConcSyn.IGlobalNTS globalNTS() throws GrammarError {
        switch (terminal) {
            case GLOBAL:
                System.out.println("globalNTS ::= GLOBAL <cpsDecl>");
                IToken global = consume(Terminals.GLOBAL);
                IConcSyn.ICpsDecl cpsDecl = cpsDecl();
                return new GlobalNTSGlobal(global, cpsDecl);
            case DO:
                System.out.println("globalNTS ::= ε");
                return new GlobalNTSEpsilon();
            default:
                throw GrammarException(Terminals.GLOBAL, Terminals.DO);
        }
    }

    // cpsDecl ::= decl cpsDeclNTS
    private IConcSyn.ICpsDecl cpsDecl() throws GrammarError {
        switch (terminal) {
            case PROC:
            case FUN:
            case IDENT:
            case CHANGEMODE:
                System.out.println("cpsDecl ::= <decl> <cpsDeclNTS>");
                IConcSyn.IDecl decl = decl();
                IConcSyn.ICpsDeclNTS cpsDeclNTS = cpsDeclNTS();
                return new CpsDecl(decl, cpsDeclNTS);
            default:
                throw GrammarException(Terminals.PROC, Terminals.FUN, Terminals.CHANGEMODE, Terminals.IDENT);
        }
    }

    // cpsDeclNTS ::= SEMICOLON decl cpsDeclNTS
    // cpsDeclNTS ::= ε
    private IConcSyn.ICpsDeclNTS cpsDeclNTS() throws GrammarError {
        switch (terminal) {
            case SEMICOLON:
                System.out.println("cpsDeclNTS ::= SEMICOLON <decl> <cpsDeclNTS>");
                IToken semicolon = consume(Terminals.SEMICOLON);
                IConcSyn.IDecl decl = decl();
                IConcSyn.ICpsDeclNTS cpsDeclNTS = cpsDeclNTS();
                return new CpsDeclNTSSemicolon(semicolon, decl, cpsDeclNTS);
            case DO:
                System.out.println("cpsDeclNTS ::= ε");
                return new CpsDeclNTSEpsilon();
            default:
                throw GrammarException(Terminals.SEMICOLON, Terminals.DO);
        }
    }
    // decl ::= stoDecl
    // decl ::= funDecl
    // decl ::= procDecl
    private IConcSyn.IDecl decl() throws GrammarError {
        switch (terminal) {
            case CHANGEMODE:
            case IDENT:
                System.out.println("decl ::= <stoDecl>");
                IConcSyn.IStoDecl stoDecl = stoDecl();
                return new DeclSto(stoDecl);
            case FUN:
                System.out.println("decl ::= <funDecl>");
                IConcSyn.IFunDecl funDecl = funDecl();
                return new DeclFun(funDecl);
            case PROC:
                System.out.println("decl ::= <procDecl>");
                IConcSyn.IProcDecl procDecl = procDecl();
                return new DeclProc(procDecl);
            default:
                throw GrammarException(Terminals.CHANGEMODE, Terminals.IDENT, Terminals.FUN, Terminals.PROC);
        }
    }

    // stoDecl ::= typedIdent
    // stoDecl ::= CHANGEMODE typedIdent
    private IConcSyn.IStoDecl stoDecl() throws GrammarError {
        switch (terminal) {
            case IDENT:
                System.out.println("stoDecl ::= <typedIdent>");
                IConcSyn.ITypedIdent typedIdent = typedIdent();
                return new StoDeclTypedIdent(typedIdent);
            case CHANGEMODE:
                System.out.println("stoDecl ::= CHANGEMODE <typedIdent>");
                IToken changemode = consume(Terminals.CHANGEMODE);
                IConcSyn.ITypedIdent typedIdent_ = typedIdent();
                return new StoDeclChangemode(changemode, typedIdent_);
            default:
                throw GrammarException(Terminals.IDENT, Terminals.CHANGEMODE);
        }
    }

    // typedIdent ::= IDENT COLON TYPE
    private IConcSyn.ITypedIdent typedIdent() throws GrammarError {
        if (terminal == Terminals.IDENT) {
            System.out.println("typedIdent ::= IDENT COLON TYPE");
            IToken ident = consume(Terminals.IDENT);
            IToken colon = consume(Terminals.COLON);
            IToken type = consume(Terminals.TYPE);
            return new TypedIdent(ident, colon, type);
        } else {
            throw GrammarException(Terminals.IDENT);
        }
    }

    // funDecl ::= FUN IDENT paramList RETURNS stoDecl funDeclNTS DO cpsCmd ENDFUN
    private IConcSyn.IFunDecl funDecl() throws GrammarError {
        if (terminal == Terminals.FUN) {
            System.out.println("funDecl ::= FUN IDENT <paramList> RETURNS <stoDecl> <funDeclNTS> DO <cpsCmd> ENDFUN");
            IToken fun = consume(Terminals.FUN);
            IToken ident = consume(Terminals.IDENT);
            IConcSyn.IParamList paramList = paramList();
            IToken returns = consume(Terminals.RETURNS);
            IConcSyn.IStoDecl stoDecl = stoDecl();
            IConcSyn.IFunDeclNTS funDeclNTS = funDeclNTS();
            IToken do_ = consume(Terminals.DO);
            IConcSyn.ICpsCmd cpsCmd = cpsCmd();
            IToken endfun = consume(Terminals.ENDFUN);
            return new FunDecl(fun, ident, paramList, returns, stoDecl, funDeclNTS, do_, cpsCmd, endfun);
        } else {
            throw GrammarException(Terminals.FUN);
        }
    }

    // funDeclNTS ::= LOCAL cpsStoDecl
    // funDeclNTS ::= ε
    private IConcSyn.IFunDeclNTS funDeclNTS() throws GrammarError {
        switch (terminal) {
            case LOCAL:
                System.out.println("funDeclNTS := LOCAL <cpsStoDecl>");
                IToken local = consume(Terminals.LOCAL);
                IConcSyn.ICpsStoDecl cpsStoDecl = cpsStoDecl();
                return new FunDeclNTSLocal(local, cpsStoDecl);
            case DO:
                System.out.println("funDeclNTS := ε");
                return new FunDeclNTSEpsilon();
            default:
                throw GrammarException(Terminals.LOCAL, Terminals.DO);
        }
    }

    // paramList ::= LPAREN paramListNTS RPAREN
    private IConcSyn.IParamList paramList() throws GrammarError {
        if (terminal == Terminals.LPAREN) {
            System.out.println("paramList ::= LPAREN <paramListNTS> RPAREN");
            IToken lparen = consume(Terminals.LPAREN);
            IConcSyn.IParamListNTS paramListNTS = paramListNTS();
            IToken rparen = consume(Terminals.RPAREN);
            return new ParamList(lparen, paramListNTS, rparen);
        } else {
            throw GrammarException(Terminals.LPAREN);
        }
    }

    // paramListNTS ::= param paramNTS
    // paramListNTS ::= ε	
    private IConcSyn.IParamListNTS paramListNTS() throws GrammarError {
        switch (terminal) {
            case IDENT:
            case MECHMODE:
            case CHANGEMODE:
                System.out.println("paramListNTS ::= <param> <paramNTS>");
                IConcSyn.IParam param = param();
                IConcSyn.IParamNTS paramNTS = paramNTS();
                return new ParamListNTSParam(param, paramNTS);
            case RPAREN:
                System.out.println("paramListNTS ::= ε");
                return new ParamListNTSEpsilon();
            default:
                throw GrammarException(Terminals.IDENT, Terminals.MECHMODE, Terminals.CHANGEMODE, Terminals.RPAREN);
        }
    }

    // paramNTS ::= COMMA param paramNTS
    // paramNTS ::= ε	
    private IConcSyn.IParamNTS paramNTS() throws GrammarError {
        switch (terminal) {
            case COMMA:
                System.out.println("paramNTS ::= COMMA <param> <paramNTS>");
                IToken comma = consume(Terminals.COMMA);
                IConcSyn.IParam param = param();
                IConcSyn.IParamNTS paramNTS = paramNTS();
                return new ParamNTSComma(comma, param, paramNTS);
            case RPAREN:
                System.out.println("paramNTS ::= ε");
                return new ParamNTSEpsilon();
            default:
                throw GrammarException(Terminals.COMMA, Terminals.RPAREN);
        }
    }

    // param ::= mechModeNTS changeModeNTS typedIdent
    private IConcSyn.IParam param() throws GrammarError {
        switch (terminal) {
            case IDENT:
            case CHANGEMODE:
            case MECHMODE:
                System.out.println("param ::= <mechModeNTS> <changeModeNTS> <typedIdent>");
                IConcSyn.IMechModeNTS mechModeNTS = mechModeNTS();
                IConcSyn.IChangeModeNTS changeModeNTS = changeModeNTS();
                IConcSyn.ITypedIdent typedIdent = typedIdent();
                return new Param(mechModeNTS, changeModeNTS, typedIdent);
            default:
                throw GrammarException(Terminals.IDENT, Terminals.CHANGEMODE, Terminals.MECHMODE);
        }
    }

    // changeModeNTS ::= CHANGEMODE
    // changeModeNTS::= ε
    private IConcSyn.IChangeModeNTS changeModeNTS() throws GrammarError {
        switch (terminal) {
            case CHANGEMODE:
                System.out.println("changeModeNTS ::= CHANGEMODE");
                IToken changemode = consume(Terminals.CHANGEMODE);
                return new ChangeModeNTSChangeMode(changemode);
            case IDENT:
            case MECHMODE:
                System.out.println("changeModeNTS ::= ε");
                return new ChangeModeNTSEpsilon();
            default:
                throw GrammarException(Terminals.CHANGEMODE, Terminals.IDENT, Terminals.MECHMODE);
        }
    }

    // mechModeNTS ::= MECHMODE
    // mechModeNTS::= ε	
    private IConcSyn.IMechModeNTS mechModeNTS() throws GrammarError {
        switch (terminal) {
            case MECHMODE:
                System.out.println("mechModeNTS ::= MECHMODE");
                IToken mechmode = consume(Terminals.MECHMODE);
                return new MechModeNTSMechMode(mechmode);
            case IDENT:
                System.out.println("mechModeNTS ::= ε");
                return new MechModeNTSEpsilon();
            default:
                throw GrammarException(Terminals.MECHMODE, Terminals.IDENT);
        }
    }

    // cpsCmd ::= cmd cpsCmdNTS
    private IConcSyn.ICpsCmd cpsCmd() throws GrammarError {
        switch (terminal) {
            case DEBUGOUT:
            case DEBUGIN:
            case CALL:
            case SWITCH:
            case WHILE:
            case IF:
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
            case SKIP:
                System.out.println("cpsCMD ::= <cmd> <cpsCmdNTS>");
                IConcSyn.ICmd cmd = cmd();
                IConcSyn.ICpsCmdNTS cpsCmdNTS = cpsCmdNTS();
                return new CpsCmd(cmd, cpsCmdNTS);
            default:
                throw GrammarException(Terminals.DEBUGOUT, Terminals.DEBUGIN, Terminals.CALL, Terminals.SWITCH,
                        Terminals.WHILE, Terminals.IF, Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR,
                        Terminals.IDENT, Terminals.LITERAL, Terminals.SKIP);
        }
    }

    // cpsCmdNTS ::= SEMICOLON cmd cpsCmdNTS
    // cpsCmdNTS ::= ε	
    private IConcSyn.ICpsCmdNTS cpsCmdNTS() throws GrammarError {
        switch (terminal) {
            case SEMICOLON:
                System.out.println("cpsCmdNTS ::= SEMICOLON <cmd> <cpsCmdNTS>");
                IToken semicolon = consume(Terminals.SEMICOLON);
                IConcSyn.ICmd cmd = cmd();
                IConcSyn.ICpsCmdNTS cpsCmdNTS = cpsCmdNTS();
                return new CpsCmdNTSSemicolon(semicolon, cmd, cpsCmdNTS);
            case ENDPROC:
            case ENDCASE:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFUN:
            case ENDPROGRAM:
                System.out.println("cpsCmdNTS ::= ε");
                return new CpsCmdNTSEpsilon();
            default:
                throw GrammarException(Terminals.SEMICOLON, Terminals.ENDPROC, Terminals.ENDCASE, Terminals.ENDWHILE,
                        Terminals.ENDIF, Terminals.ELSE, Terminals.ENDFUN, Terminals.ENDPROGRAM);
        }
    }

    // cmd ::= SKIP
    // cmd ::= expr BECOMES expr
    // cmd ::= IF expr THEN cpsCmd ifelseNTS ENDIF
    // cmd ::= WHILE expr DO cpsCmd ENDWHILE	
    // cmd ::= SWITCH expr caseNTS defaultCaseNTS ENDSWITCH		
    // cmd ::= CALL IDENT exprList
    // cmd ::= DEBUGIN expr
    // cmd ::= DEBUGOUT expr						
    private IConcSyn.ICmd cmd() throws GrammarError {
        IConcSyn.IExpr expr;

        switch (terminal) {
            case SKIP:
                System.out.println("cmd ::= SKIP");
                IToken skip = consume(Terminals.SKIP);
                return new CmdSkip(skip);
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
                System.out.println("cmd ::= <expr> BECOMES <expr>");
               expr = expr();
                IToken becomes = consume(Terminals.BECOMES);
                IConcSyn.IExpr expr2 = expr();
                return new CmdExpr(expr, becomes, expr2);
            case IF:
                System.out.println("cmd ::= IF <expr> THEN <cpsCmd> <ifelseNTS> ENDIF");
                IToken if_ = consume(Terminals.IF);
                expr = expr();
                IToken then = consume(Terminals.THEN);
                IConcSyn.ICpsCmd cpsCmd = cpsCmd();
                IConcSyn.IIfelseNTS ifelseNTS = ifelseNTS();
                IToken endif = consume(Terminals.ENDIF);
                return new CmdIf(if_, expr, then, cpsCmd, ifelseNTS, endif);
            case WHILE:
                System.out.println("cmd ::= WHILE <expr> DO <cpsCmd> ENDWHILE");
                IToken while_ = consume(Terminals.WHILE);
                expr = expr();
                IToken do_ = consume(Terminals.DO);
                IConcSyn.ICpsCmd cpsCmd_ = cpsCmd();
                IToken endwhile = consume(Terminals.ENDWHILE);
                return new CmdWhile(while_, expr, do_, cpsCmd_, endwhile);
            case SWITCH:
                System.out.println("cmd ::= SWITCH <expr> <caseNTS> <defaultCaseNTS> ENDSWITCH");
                IToken switch_ = consume(Terminals.SWITCH);
                expr = expr();
                IConcSyn.ICaseNTS caseNTS = caseNTS();
                IConcSyn.IDefaultCaseNTS defaultCaseNTS = defaultCaseNTS();
                IToken endswitch = consume(Terminals.ENDSWITCH);
                return new CmdSwitch(switch_, expr, caseNTS, defaultCaseNTS, endswitch);
            case CALL:
                System.out.println("cmd ::= CALL IDENT <exprList>");
                IToken call = consume(Terminals.CALL);
                IToken ident = consume(Terminals.IDENT);
                IConcSyn.IExprList exprList = exprList();
                return new CmdCall(call, ident, exprList);
            case DEBUGIN:
                System.out.println("cmd ::= DEBUGIN <expr>");
                IToken debugin = consume(Terminals.DEBUGIN);
                expr = expr();
                return new CmdDebugin(debugin, expr);
            case DEBUGOUT:
                System.out.println("cmd ::= DEBUGOUT <expr>");
                IToken debugout = consume(Terminals.DEBUGOUT);
                expr = expr();
                return new CmdDebugout(debugout, expr);
            default:
                throw GrammarException(Terminals.SKIP, Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR,
                        Terminals.IDENT, Terminals.LITERAL, Terminals.IF, Terminals.WHILE, Terminals.SWITCH,
                        Terminals.CALL, Terminals.DEBUGIN, Terminals.DEBUGOUT);
        }
    }

    // ifelseNTS ::= ELSE cpsCmd
    // ifelseNTS ::= ε 	
    private IConcSyn.IIfelseNTS ifelseNTS() throws GrammarError {
        switch (terminal) {
            case ELSE:
                System.out.println("ifelseNTS ::= ELSE <cpsCmd>");
                IToken else_ = consume(Terminals.ELSE);
                IConcSyn.ICpsCmd cpsCmd = cpsCmd();
                return new IfelseNTSElse(else_, cpsCmd);
            case ENDIF:
                System.out.println("ifelseNTS ::= ε");
                return new IfelseNTSEpsilon();
            default:
                throw GrammarException(Terminals.ELSE, Terminals.ENDIF);
        }
    }

    // caseNTS::= CASE LITERAL COLON cpsCmd ENDCASE caseNTS
    // caseNTS::= ε	
    private IConcSyn.ICaseNTS caseNTS() throws GrammarError {
        switch (terminal) {
            case CASE:
                System.out.println("caseNTS ::= CASE LITERAL COLON <cpsCmd> ENDCASE <caseNTS>");
                IToken case_ = consume(Terminals.CASE);
                IToken literal = consume(Terminals.LITERAL);
                IToken colon = consume(Terminals.COLON);
                IConcSyn.ICpsCmd cpsCmd = cpsCmd();
                IToken endcase = consume(Terminals.ENDCASE);
                IConcSyn.ICaseNTS caseNTS = caseNTS();
                return new CaseNTSCase(case_, literal, colon, cpsCmd, endcase, caseNTS);
            case ENDSWITCH:
            case DEFAULTCASE:
                System.out.println("caseNTS ::= ε");
                return new CaseNTSEpsilon();
            default:
                throw GrammarException(Terminals.CASE, Terminals.ENDSWITCH, Terminals.DEFAULTCASE);
        }
    }

    // defaultCaseNTS ::= DEFAULTCASE COLON cpsCmd ENDCASE
    // defaultCaseNTS ::= ε	
    private IConcSyn.IDefaultCaseNTS defaultCaseNTS() throws GrammarError {
        switch (terminal) {
            case DEFAULTCASE:
                System.out.println("defaultCaseNTS ::= DEFAULTCASE COLON <cpsCmd> ENDCASE");
                IToken defaultcase = consume(Terminals.DEFAULTCASE);
                IToken colon = consume(Terminals.COLON);
                IConcSyn.ICpsCmd cpsCmd = cpsCmd();
                IToken endcase = consume(Terminals.ENDCASE);
                return new DefaultCaseNTSDefaultcase(defaultcase, colon, cpsCmd, endcase);
            case ENDSWITCH:
                System.out.println("defaultCaseNTS ::= ε");
                return new DefaultCaseNTSEpsilon();
            default:
                throw GrammarException(Terminals.DEFAULTCASE, Terminals.ENDSWITCH);
        }
    }

    // expr ::= term0 condExprNTS
    private IConcSyn.IExpr expr() throws GrammarError {
        switch (terminal) {
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
                System.out.println("expr ::= <term0> <condExprNTS>");
                IConcSyn.ITerm0 term0 = term0();
                IConcSyn.ICondExprNTS condExprNTS = condExprNTS();
                return new Expr(term0, condExprNTS);
            default:
                throw GrammarException(Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR,
                        Terminals.IDENT, Terminals.LITERAL);
        }
    }

    // condExprNTS ::= QUESTIONMARK expr COLON expr
    // condExprNTS ::= ε 	
    private IConcSyn.ICondExprNTS condExprNTS() throws GrammarError {
        switch (terminal) {
            case QUESTIONMARK:
                System.out.println("condExprNTS ::= QUESTIONMARK <expr> COLON <expr>");
                IToken questionmark = consume(Terminals.QUESTIONMARK);
                IConcSyn.IExpr expr1 = expr();
                IToken colon = consume(Terminals.COLON);
                IConcSyn.IExpr expr2 = expr();
                return new CondExprNTSQuestionmark(questionmark, expr1, colon, expr2);
            case COMMA:
            case RPAREN:
            case COLON:
            case ENDSWITCH:
            case DEFAULTCASE:
            case CASE:
            case DO:
            case THEN:
            case ENDPROC:
            case ENDCASE:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
                System.out.println("condExprNTS ::= ε");
                return new CondExprNTSEpsilon();
            default:
                throw GrammarException(Terminals.QUESTIONMARK, Terminals.COMMA, Terminals.RPAREN, Terminals.COLON,
                        Terminals.ENDSWITCH, Terminals.DEFAULTCASE, Terminals.CASE, Terminals.DO, Terminals.THEN,
                        Terminals.ENDPROC, Terminals.ENDCASE, Terminals.ENDWHILE, Terminals.ENDIF, Terminals.ELSE,
                        Terminals.ENDFUN, Terminals.ENDPROGRAM, Terminals.SEMICOLON, Terminals.BECOMES);
        }
    }

    // term0 ::= term1 term0NTS
    private IConcSyn.ITerm0 term0() throws GrammarError {
        switch (terminal) {
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
                System.out.println("term0 ::= <term1> <term0NTS>");
                IConcSyn.ITerm1 term1 = term1();
                IConcSyn.ITerm0NTS term0NTS = term0NTS();
                return new Term0(term1, term0NTS);
            default:
                throw GrammarException(Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR, Terminals.IDENT, Terminals.LITERAL);
        }
    }

    // term0NTS ::= BOOLOPR term1 term0NTS
    // term0NTS ::= ε	
    private IConcSyn.ITerm0NTS term0NTS() throws GrammarError {
        switch (terminal) {
            case BOOLOPR:
                System.out.println("term0NTS ::= BOOLOPR <term1> <term0NTS>");
                IToken boolopr = consume(Terminals.BOOLOPR);
                IConcSyn.ITerm1 term1 = term1();
                IConcSyn.ITerm0NTS term0NTS = term0NTS();
                return new Term0NTSBoolopr(boolopr, term1, term0NTS);
            case COMMA:
            case RPAREN:
            case COLON:
            case ENDSWITCH:
            case DEFAULTCASE:
            case CASE:
            case DO:
            case THEN:
            case ENDPROC:
            case ENDCASE:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case QUESTIONMARK:
                System.out.println("term0NTS ::= ε");
                return new Term0NTSEpsilon();
            default:
                throw GrammarException(Terminals.BOOLOPR, Terminals.COMMA, Terminals.RPAREN, Terminals.COLON,
                        Terminals.ENDSWITCH, Terminals.DEFAULTCASE, Terminals.CASE, Terminals.DO, Terminals.THEN,
                        Terminals.ENDPROC, Terminals.ENDCASE, Terminals.ENDWHILE, Terminals.ENDIF, Terminals.ELSE,
                        Terminals.ENDFUN, Terminals.ENDPROGRAM, Terminals.SEMICOLON, Terminals.BECOMES,
                        Terminals.QUESTIONMARK);
        }
    }

    // term1 ::= term2 term1NTS
    private IConcSyn.ITerm1 term1() throws GrammarError {
        switch (terminal) {
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
                System.out.println("term1 ::= <term2> <term1NTS>");
                IConcSyn.ITerm2 term2 = term2();
                IConcSyn.ITerm1NTS term1NTS = term1NTS();
                return new Term1(term2, term1NTS);
            default:
                throw GrammarException(Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR,
                        Terminals.IDENT, Terminals.LITERAL);
        }
    }

    // term1NTS::= RELOPR term2
    // term1NTS::= ε	
    private IConcSyn.ITerm1NTS term1NTS() throws GrammarError {
        switch (terminal) {
            case RELOPR:
                System.out.println("term1NTS ::= RELOPR <term2>");
                IToken relopr = consume(Terminals.RELOPR);
                IConcSyn.ITerm2 term2 = term2();
                return new Term1NTSRelopr(relopr, term2);
            case COMMA:
            case RPAREN:
            case COLON:
            case ENDSWITCH:
            case DEFAULTCASE:
            case CASE:
            case DO:
            case THEN:
            case ENDPROC:
            case ENDCASE:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case QUESTIONMARK:
            case BOOLOPR:
                System.out.println("term1NTS ::= ε");
                return new Term1NTSEpsilon();
            default:
                throw GrammarException(Terminals.RELOPR, Terminals.COMMA, Terminals.RPAREN, Terminals.COLON,
                        Terminals.ENDSWITCH, Terminals.DEFAULTCASE, Terminals.CASE, Terminals.DO, Terminals.THEN,
                        Terminals.ENDPROC, Terminals.ENDCASE, Terminals.ENDWHILE, Terminals.ENDIF, Terminals.ELSE,
                        Terminals.ENDFUN, Terminals.ENDPROGRAM, Terminals.SEMICOLON, Terminals.BECOMES,
                        Terminals.QUESTIONMARK, Terminals.BOOLOPR);
        }
    }

    // term2 ::= term3 term2NTS
    private IConcSyn.ITerm2 term2() throws GrammarError {
        switch (terminal) {
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
                System.out.println("term2 ::= <term3> <term2NTS>");
                IConcSyn.ITerm3 term3 = term3();
                IConcSyn.ITerm2NTS term2NTS = term2NTS();
                return new Term2(term3, term2NTS);
            default:
                throw GrammarException(Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR,
                        Terminals.IDENT, Terminals.LITERAL);
        }
    }

    // term2NTS ::= ADDOPR term3 term2NTS
    // term2NTS ::= ε	
    private IConcSyn.ITerm2NTS term2NTS() throws GrammarError {
        switch (terminal) {
            case ADDOPR:
                System.out.println("term2NTS ::= ADDOPR <term3> <term2NTS>");
                IToken addopr = consume(Terminals.ADDOPR);
                IConcSyn.ITerm3 term3 = term3();
                IConcSyn.ITerm2NTS term2NTS = term2NTS();
                return new Term2NTSAddopr(addopr, term3, term2NTS);
            case COMMA:
            case RPAREN:
            case COLON:
            case ENDSWITCH:
            case DEFAULTCASE:
            case CASE:
            case DO:
            case THEN:
            case ENDPROC:
            case ENDCASE:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case QUESTIONMARK:
            case BOOLOPR:
            case RELOPR:
                System.out.println("term2NTS ::= ε");
                return new Term2NTSEpsilon();
            default:
                throw GrammarException(Terminals.ADDOPR, Terminals.COMMA, Terminals.RPAREN, Terminals.COLON,
                        Terminals.ENDSWITCH, Terminals.DEFAULTCASE, Terminals.CASE, Terminals.DO, Terminals.THEN,
                        Terminals.ENDPROC, Terminals.ENDCASE, Terminals.ENDWHILE, Terminals.ENDIF, Terminals.ELSE,
                        Terminals.ENDFUN, Terminals.ENDPROGRAM, Terminals.SEMICOLON, Terminals.BECOMES,
                        Terminals.QUESTIONMARK, Terminals.BOOLOPR, Terminals.RELOPR);
        }
    }

    // term3 ::= factor term3NTS
    private IConcSyn.ITerm3 term3() throws GrammarError {
        switch (terminal) {
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
                System.out.println("term3 ::= <factor> <term3NTS>");
                IConcSyn.IFactor factor = factor();
                IConcSyn.ITerm3NTS term3NTS = term3NTS();
                return new Term3(factor, term3NTS);
            default:
                throw GrammarException(Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR,
                        Terminals.IDENT, Terminals.LITERAL);
        }
    }

    // term3NTS ::= MULTOPR factor term3NTS
    // term3NTS ::= ε	
    private IConcSyn.ITerm3NTS term3NTS() throws GrammarError {
        switch (terminal) {
            case MULTOPR:
                System.out.println("term3NTS ::= MULTOPR <factor> <term3NTS>");
                IToken multopr = consume(Terminals.MULTOPR);
                IConcSyn.IFactor factor = factor();
                IConcSyn.ITerm3NTS term3NTS = term3NTS();
                return new Term3NTSMultopr(multopr, factor, term3NTS);
            case COMMA:
            case RPAREN:
            case COLON:
            case ENDSWITCH:
            case DEFAULTCASE:
            case CASE:
            case DO:
            case THEN:
            case ENDPROC:
            case ENDCASE:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case QUESTIONMARK:
            case BOOLOPR:
            case RELOPR:
            case ADDOPR:
                System.out.println("term3NTS ::= ε");
                return new Term3NTSEpsilon();
            default:
                throw GrammarException(Terminals.MULTOPR, Terminals.COMMA, Terminals.RPAREN, Terminals.COLON,
                        Terminals.ENDSWITCH, Terminals.DEFAULTCASE, Terminals.CASE, Terminals.DO, Terminals.THEN,
                        Terminals.ENDPROC, Terminals.ENDCASE, Terminals.ENDWHILE, Terminals.ENDIF, Terminals.ELSE,
                        Terminals.ENDFUN, Terminals.ENDPROGRAM, Terminals.SEMICOLON, Terminals.BECOMES,
                        Terminals.QUESTIONMARK, Terminals.BOOLOPR, Terminals.RELOPR, Terminals.ADDOPR);
        }
    }

    // factor ::= LITERAL
    // factor ::= IDENT factorNTS	
    // factor ::= monadicOpr factor
    // factor ::= LPAREN expr RPAREN	
    private IConcSyn.IFactor factor() throws GrammarError {
        switch (terminal) {
            case LITERAL:
                System.out.println("factor ::= LITERAL");
                IToken literal = consume(Terminals.LITERAL);
                return new FactorLiteral(literal);
            case IDENT:
                System.out.println("factor ::= IDENT <factorNTS>");
                IToken ident = consume(Terminals.IDENT);
                IConcSyn.IFactorNTS factorNTS = factorNTS();
                return new FactorIdent(ident, factorNTS);
            case ADDOPR:
            case NOTOPR:
                System.out.println("factor ::= <monadicOpr> <factor>");
                IConcSyn.IMonadicOpr monadicOpr = monadicOpr();
                IConcSyn.IFactor factor = factor();
                return new FactorMonadicopr(monadicOpr, factor);
            case LPAREN:
                System.out.println("factor ::= LPAREN <expr> RPAREN");
                IToken lparen = consume(Terminals.LPAREN);
                IConcSyn.IExpr expr = expr();
                IToken rparen = consume(Terminals.RPAREN);
                return new FactorLParen(lparen, expr, rparen);
            default:
                throw GrammarException(Terminals.LITERAL, Terminals.IDENT, Terminals.ADDOPR,
                        Terminals.NOTOPR, Terminals.LPAREN);
        }
    }

    // factorNTS ::= INIT
    // factorNTS ::= exprList
    // factorNTS ::= ε	
    private IConcSyn.IFactorNTS factorNTS() throws GrammarError {
        switch (terminal) {
            case INIT:
                System.out.println("factorNTS ::= INIT");
                IToken init = consume(Terminals.INIT);
                return new FactorNTSInit(init);
            case LPAREN:
                System.out.println("factorNTS ::= <exprList>");
                IConcSyn.IExprList exprList = exprList();
                return new FactorNTSExprList(exprList);
            case COMMA:
            case RPAREN:
            case COLON:
            case ENDSWITCH:
            case DEFAULTCASE:
            case CASE:
            case DO:
            case THEN:
            case ENDPROC:
            case ENDCASE:
            case ENDWHILE:
            case ENDIF:
            case ELSE:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case QUESTIONMARK:
            case BOOLOPR:
            case RELOPR:
            case ADDOPR:
            case MULTOPR:
                System.out.println("factorNTS ::= ε");
                return new FactorNTSEpsilon();
            default:
                throw GrammarException(Terminals.INIT, Terminals.LPAREN, Terminals.COMMA, Terminals.RPAREN,
                        Terminals.COLON, Terminals.ENDSWITCH, Terminals.DEFAULTCASE, Terminals.CASE, Terminals.DO,
                        Terminals.THEN, Terminals.ENDPROC, Terminals.ENDCASE, Terminals.ENDWHILE, Terminals.ENDIF,
                        Terminals.ELSE, Terminals.ENDFUN, Terminals.ENDPROGRAM, Terminals.SEMICOLON, Terminals.BECOMES,
                        Terminals.QUESTIONMARK, Terminals.BOOLOPR, Terminals.RELOPR, Terminals.ADDOPR, Terminals.MULTOPR);
        }
    }

    // exprList ::= LPAREN exprListLparenNTS RPAREN
    private IConcSyn.IExprList exprList() throws GrammarError {
        if (terminal == Terminals.LPAREN) {
            System.out.println("exprList ::= LPAREN <exprListLparenNTS> RPAREN");
            IToken lparen = consume(Terminals.LPAREN);
            IConcSyn.IExprListLparenNTS exprListLparenNTS = exprListLparenNTS();
            IToken rparen = consume(Terminals.RPAREN);
            return new ExprList(lparen, exprListLparenNTS, rparen);
        } else {
            throw GrammarException(Terminals.LPAREN);
        }
    }

    // exprListLparenNTS ::= expr exprListNTS
    // exprListLparenNTS ::= ε	
    private IConcSyn.IExprListLparenNTS exprListLparenNTS() throws GrammarError {
        switch (terminal) {
            case LPAREN:
            case ADDOPR:
            case NOTOPR:
            case IDENT:
            case LITERAL:
                System.out.println("exprListLparenNTS ::= <expr> <exprListNTS>");
                IConcSyn.IExpr expr = expr();
                IConcSyn.IExprListNTS exprListNTS = exprListNTS();
                return new ExprListLparenNTSExpr(expr, exprListNTS);
            case RPAREN:
                System.out.println("exprListLparenNTS ::= ε");
                return new ExprListLparenNTSEpsilon();
            default:
                throw GrammarException(Terminals.LPAREN, Terminals.ADDOPR, Terminals.NOTOPR, Terminals.IDENT,
                        Terminals.LITERAL, Terminals.RPAREN);
        }
    }

    // exprListNTS ::= COMMA expr exprListNTS
    // exprListNTS ::= ε	
    private IConcSyn.IExprListNTS exprListNTS() throws GrammarError {
        switch (terminal) {
            case COMMA:
                System.out.println("exprListNTS ::= COMMA <expr> <exprListNTS>");
                IToken comma = consume(Terminals.COMMA);
                IConcSyn.IExpr expr = expr();
                IConcSyn.IExprListNTS exprListNTS = exprListNTS();
                return new ExprListNTSComma(comma, expr, exprListNTS);
            case RPAREN:
                System.out.println("exprListNTS ::= ε");
                return new ExprListNTSEpsilon();
            default:
                throw GrammarException(Terminals.COMMA, Terminals.RPAREN);
        }
    }

    // monadicOpr ::= NOT
    // monadicOpr ::= ADDOPR	
    private IConcSyn.IMonadicOpr monadicOpr() throws GrammarError {
        switch (terminal) {
            case NOTOPR:
                System.out.println("monadicOpr ::= NOT");
                IToken not = consume(Terminals.NOTOPR);
                return new MonadicOprNot(not);
            case ADDOPR:
                System.out.println("monadicOpr ::= ADDOPR");
                IToken addopr = consume(Terminals.ADDOPR);
                return new MonadicOprAddopr(addopr);
            default:
                throw GrammarException(Terminals.NOTOPR, Terminals.ADDOPR);
        }
    }

    // cpsStoDecl ::= stoDecl cpsStoDeclNTS
    private IConcSyn.ICpsStoDecl cpsStoDecl() throws GrammarError {
        switch (terminal) {
            case CHANGEMODE:
            case IDENT:
                System.out.println("cpsStoDecl ::= <stoDecl> <cpsStoDeclNTS>");
                IConcSyn.IStoDecl stoDecl = stoDecl();
                IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS = cpsStoDeclNTS();
                return new CpsStoDecl(stoDecl, cpsStoDeclNTS);
            default:
                throw GrammarException(Terminals.CHANGEMODE, Terminals.IDENT);
        }
    }

    // cpsStoDeclNTS ::= SEMICOLON stoDecl cpsStoDeclNTS
    // cpsStoDeclNTS ::= ε	
    private IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS() throws GrammarError {
        switch (terminal) {
            case SEMICOLON:
                System.out.println("cpsStoDeclNTS ::= SEMICOLON <stoDecl> <cpsStoDeclNTS>");
                IToken semicolon = consume(Terminals.SEMICOLON);
                IConcSyn.IStoDecl stoDecl = stoDecl();
                IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS = cpsStoDeclNTS();
                return new CpsStoDeclNTSSemicolon(semicolon, stoDecl, cpsStoDeclNTS);
            case DO:
                System.out.println("cpsStoDeclNTS ::= ε");
                return new CpsStoDeclNTSEpsilon();
            default:
                throw GrammarException(Terminals.SEMICOLON, Terminals.DO);
        }
    }

    // procDecl ::= PROC IDENT paramList procDeclNTS DO cpsCmd ENDPROC
    private IConcSyn.IProcDecl procDecl() throws GrammarError {
        if (terminal == Terminals.PROC) {
            System.out.println("procDecl ::= PROC IDENT <paramList> <procDeclNTS> DO <cpsCmd> ENDPROC");
            IToken proc = consume(Terminals.PROC);
            IToken ident = consume(Terminals.IDENT);
            IConcSyn.IParamList paramList = paramList();
            IConcSyn.IProcDeclNTS procDeclNTS = procDeclNTS();
            IToken do_ = consume(Terminals.DO);
            IConcSyn.ICpsCmd cpsCmd = cpsCmd();
            IToken endproc = consume(Terminals.ENDPROC);
            return new ProcDecl(proc, ident, paramList, procDeclNTS, do_, cpsCmd, endproc);
        } else {
            throw GrammarException(Terminals.PROC);
        }
    }

    // procDeclNTS ::= LOCAL cpsStoDecl
    // procDeclNTS ::= ε	
    private IConcSyn.IProcDeclNTS procDeclNTS() throws GrammarError {
        switch (terminal) {
            case LOCAL:
                System.out.println("procDeclNTS ::= LOCAL <cpsStoDecl>");
                IToken local = consume(Terminals.LOCAL);
                IConcSyn.ICpsStoDecl cpsStoDecl = cpsStoDecl();
                return new ProcDeclNTSLocal(local, cpsStoDecl);
            case DO:
                System.out.println("procDeclNTS ::= ε");
                return new ProcDeclNTSEpsilon();
            default:
                throw GrammarException(Terminals.LOCAL, Terminals.DO);
        }
    }

}
