package spo.Parser;

import spo.Lexer1.Token;

import java.util.ArrayList;

public class Parser1 {

    private static final Token EOF = new Token("EOF", ""); //конец парсинга
    private final ArrayList<Token> tokens;
    private int pos; //позиция
    private final int size;

    public Parser1(ArrayList<Token> token) {
        this.tokens = token;
        size = tokens.size();
    }

    private Token get(int relative) {
        final int position = pos + relative;
        if (position >= size) return EOF;
        return tokens.get(position);
    }

    private boolean match(String type) {
        final Token current = get(0);
        if ((type.equals(current.getType()))) { //сравнение приходящего типа
            pos++;
            return true;
        } else {
            return false;
        }
    }

    public void parse() {
        lang();
    }

    private void lang() {
        while (get(0) != EOF) {
            expr();
        }
    }

    private void expr() {
        if (match("VAR")) { //переменные
            if (match("TYPE_OP")) {
                type();
            } else if (match("ASSIGN_OP")) {
                assign();
            } else if(match("METHOD")||match("GET")){
                method();
            } else {
                throw new RuntimeException("-1");
            }
        } else if (match("CYCLE")) { //циклы
            cycle();
        } else if (match("PRINT")) { //вывод
            print();
        } else {
            throw new RuntimeException("-2");
        }
    }

    private void assign() {
        assign_value();
        if (!match("END")) {
            throw new RuntimeException("-3");
        }
    }

    private void assign_value() {
        math_expr();
        while (true) {
            if ((match("OP_DIV_MUL")) || (match("-4"))) {
                math_expr();
            } else {
                break;
            }
        }
    }

    private void math_expr() {
        try {
            add_expr();
        } catch (RuntimeException e) {
            math_br();
        }
    }

    private void add_expr() throws RuntimeException {
        value();
        while (true) {
            if ((match("OP_DIV_MUL")) || (match("OP_ADD_SUB"))) {
                value();
            } else {
                break;
            }
        }
    }

    private void math_br() {
        while (!(match("R_B"))) {
            if (match("L_B")) {
                assign_value();
            } else {
                throw new RuntimeException("-5");
            }
        }
    }

    private void value() throws RuntimeException {
        if (!match("DIGIT") ) {
            if(match("VAR")){
                meth_br();
            }else {
                throw new RuntimeException("-6");
            }
        }
    }

    private void meth_br() {
        if(match("GET")){
            if(match("L_B")) {
                value();
            }
            if(!match("R_B")){
                throw new RuntimeException("-7");
            }
        }
    }

    private void cycle() {
        compare();
        body();
    }

    private void compare() {
        if ((match("L_B")) && (match("VAR")) && (match("COMP_OP"))) {
            value();
        } else {
            throw new RuntimeException("-8");
        }
        if (!match("R_B")) {
            throw new RuntimeException("-9");
        }
    }

    private void body() {
        if (match("L_F_B")) {
            while (!match("R_F_B")) {
                expr();
            }
        } else {
            throw new RuntimeException("-10");
        }
    }

    private void print() {
        if (match("L_B")) {
            value();
            while (!((match("R_B")) && (match("END")))) {
                if (match("COMA")) {
                    value();
                } else {
                    throw new RuntimeException("-11");
                }
            }
        } else {
            throw new RuntimeException("-12");
        }
    }

    private void type() {
        if (match("TYPE")) {
            if (!match("END")) {
                throw new RuntimeException("-13");
            }
        } else {
            throw new RuntimeException("-14");
        }
    }

    private void method() {
        int buff = pos - 1;
        if (match("L_B")) {
            value();
            if(get(buff-pos).getValue().equals(".set")){
                if(!match("COMA")){
                    throw new RuntimeException("-15");
                }else {
                    value();
                }
            } if(!(match("R_B")&&match("END"))){
                throw new RuntimeException("-15");
            }
        } else {
            throw new RuntimeException("-16");
        }
    }
}