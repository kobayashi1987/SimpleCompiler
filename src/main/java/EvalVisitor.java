package main.java;

import java.util.HashMap;
import java.util.Map;

// Extend the generated base visitor with return type Integer
public class EvalVisitor extends ExprBaseVisitor<Integer> {
    // A simple memory to store variable values
    Map<String, Integer> memory = new HashMap<>();

    // Visit a print statement (the 'printExpr' alternative)
    @Override
    public Integer visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    // Visit an assignment statement
    @Override
    public Integer visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();  // variable name
        Integer value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    // Visit a multiplication/division expression
    @Override
    public Integer visitMulDiv(ExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        // Use getText() to distinguish the operator
        if (ctx.op.getText().equals("*")) {
            return left * right;
        } else { // division
            return left / right;
        }
    }

    // Visit an addition/subtraction expression
    @Override
    public Integer visitAddSub(ExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getText().equals("+")) {
            return left + right;
        } else { // subtraction
            return left - right;
        }
    }

    // Visit an integer literal
    @Override
    public Integer visitInt(ExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    // Visit an identifier (variable)
    @Override
    public Integer visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        System.err.println("Undefined variable: " + id);
        return 0;
    }

    // Visit parentheses (just return the inner expression’s value)
    @Override
    public Integer visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}