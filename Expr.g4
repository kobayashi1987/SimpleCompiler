grammar Expr;

// Parser rules
prog:   stat+ ;

stat
    : expr NEWLINE              # printExpr
    | ID '=' expr NEWLINE       # assign
    | NEWLINE                   # blank
    ;

// Expression rules with labeled alternatives
expr
    : expr op=('*'|'/') expr    # MulDiv
    | expr op=('+'|'-') expr    # AddSub
    | INT                      # int
    | ID                       # id
    | '(' expr ')'             # parens
    ;

// Lexer rules
ID      : [a-zA-Z]+ ;
INT     : [0-9]+ ;
NEWLINE : ('\r'? '\n') ;
WS      : [ \t]+ -> skip ;