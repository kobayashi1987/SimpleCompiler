package main.java;

public class RPNVisitor extends ExprBaseVisitor<String> {
    @Override
    public String visitPrintExpr(ExprParser.PrintExprContext ctx) {
        String rpn = visit(ctx.expr());
        System.out.println(rpn);
        return rpn;
    }

    @Override
    public String visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        String exprRPN = visit(ctx.expr());
        String result = exprRPN + " " + id + " =";
        System.out.println(result);
        return result;
    }

    @Override
    public String visitMulDiv(ExprParser.MulDivContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        return left + " " + right + " " + ctx.op.getText();
    }

    @Override
    public String visitAddSub(ExprParser.AddSubContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        return left + " " + right + " " + ctx.op.getText();
    }

    @Override
    public String visitInt(ExprParser.IntContext ctx) {
        return ctx.INT().getText();
    }

    @Override
    public String visitId(ExprParser.IdContext ctx) {
        return ctx.ID().getText();
    }

    @Override
    public String visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}