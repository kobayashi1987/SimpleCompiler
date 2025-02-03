package main.java;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

import main.java.ExprLexer;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create a CharStream that reads from standard input or a file
        CharStream input;
        if (args.length > 0) {
            input = CharStreams.fromFileName(args[0]);
        } else {
            System.out.println("Enter your program (terminate with EOF, i.e., Ctrl+D):");
            input = CharStreams.fromStream(System.in);
        }

        // Create a lexer that feeds off of input CharStream
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        ExprParser parser = new ExprParser(tokens);

        // (Optional) Add custom error handling here (see Lesson 7)
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine, String msg, RecognitionException e) {
                System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
            }
        });

        // Begin parsing at rule 'prog'
        ParseTree tree = parser.prog();

        // Create a visitor and evaluate the parse tree
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }
}
