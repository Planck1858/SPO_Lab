package Parser;

import Parser.Program.*;

import java.util.List;

public final class  Parser {
    private static final Token EOF = new Token(TokenType.EOF, "");
    private final List<Token> tokens;
    private final int size;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public Statement parse() {
        final  BlockStatement result = new BlockStatement();
        while (!match(TokenType.EOF)) {
            result.add(statement());
        }
        return result;
    }

    private Statement statemetOrBlock(){
        if(get(0).getType() == TokenType.LBRACE) return block();
        return statement();
    }

    private Statement block(){
        final BlockStatement block = new BlockStatement();
        consume(TokenType.LBRACE);
        while (!match(TokenType.RBRACE)) {
            block.add(statement());
        }
        return block;
    }


    private Statement statement() {
        if(match(TokenType.PRINT)) {
            return new PrintStatement(expression());
        }
        if(match(TokenType.IF)){
            return IfElse();
        }if(match(TokenType.WHILE)){
            return whileStatement();}
        if(match(TokenType.BREAK)) {
            return new BreakStatement();
        }if(match(TokenType.CONTINUE)){
            return new ContinueStatement();
        }if(match(TokenType.FOR)){
            return forStatement();
        }
        return assigmentStatement();
    }

    private Statement assigmentStatement(){
        //WORD EQ
        final Token current = get(0);
        if (match(TokenType.WORD) && get(0).getType() == TokenType.EQ){
            final String variable = current.getText();
            consume(TokenType.EQ);
            return new AssigmentStatement(variable, expression());
        }
        throw new RuntimeException("Unknown statement!");
    }

    private Statement IfElse(){
        final Expression condition = expression();
        final Statement ifStatement = statemetOrBlock();
        final Statement elseStatement;
        if(match(TokenType.ELSE)){
            elseStatement = statemetOrBlock();
        }else{
            elseStatement = null;
        }
        //вызываем новое IF утверждение
        return new IfStatement(condition, ifStatement, elseStatement);

    }

    private Statement whileStatement(){
        final Expression condition = expression();
        final Statement statement = statemetOrBlock();
        return new WhileStatement(condition, statement);
    }

    private Statement forStatement(){
        final Statement initialization = assigmentStatement();
        consume(TokenType.COMMA);
        final Expression termination = expression();
        consume(TokenType.COMMA);
        final Statement increment = assigmentStatement();
        final Statement statement = statemetOrBlock();
        return new ForStatement(initialization, termination, increment, statement);
    }


    private Expression expression() {
        return logicalOr();
    }

    private Expression logicalOr() {
        Expression result = logicalAnd();
        while (true) {
            if (match(TokenType.BARBAR)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.OR, result, logicalAnd());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression logicalAnd(){
        Expression result = equality();
        while (true) {
            if (match(TokenType.AMPAMP)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.AND, result, logicalAnd());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression equality() {
        Expression result = conditioanl();
        while (true) {
            if (match(TokenType.EQEQ)) {
                return new ConditionalExpression(ConditionalExpression.Operator.EQUALS, result, conditioanl());
            }
            if (match(TokenType.EXCLEQ)) {
                return new ConditionalExpression(ConditionalExpression.Operator.NOT_EQUALS, result, conditioanl());
            }
            return result;
        }
    }

    private Expression conditioanl(){
            Expression result = additive();
        while (true) {
            if (match(TokenType.LT)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.LT, result, additive());
                continue;
            }
            if (match(TokenType.LTEQ)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.LTEQ, result, additive());
                continue;
            }
            if (match(TokenType.GT)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.GT, result, additive());
                continue;
            }
            if (match(TokenType.GTEQ)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.GTEQ, result, additive());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression additive() {
        Expression result = multiplicative();
        while (true) {
            if (match(TokenType.PLUS)) {
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)) {
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression multiplicative() {
        Expression result = unary();
        while (true) {
            // 2 * 6 / 3
            if (match(TokenType.STAR)) {
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenType.SLASH)) {
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression unary() {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        if (match(TokenType.PLUS)) {
            return primary();
        }
        return primary();
    }

    private Expression primary() {
        final Token current = get(0);
        if (match(TokenType.NUMBER)) {
            return new ValueExpression(Double.parseDouble(current.getText()));
        }
        if (match(TokenType.HEX_NUMBER)) {
            return new ValueExpression(Long.parseLong(current.getText(), 16));
        }
        if (match(TokenType.WORD)) {
            return new VariableExpression(current.getText());
        }
        if (match(TokenType.TEXT)) {
            return new ValueExpression(current.getText());
        }
        if (match(TokenType.LPAREN)) {
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }
        throw new RuntimeException("Unknown expression");
    }

    private Token consume(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) throw new RuntimeException("Token " + current + " doesn't match " + type);
        pos++;
        return current;
    }

    private boolean match(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) return false;
        pos++;
        return true;
    }

    private Token get(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= size) return EOF;
        return tokens.get(position);
    }
}
