package spo.Lexer1;

public class TokenOperator extends Token {
    private int priority;

    public TokenOperator(int priority,String type, String value){
        super(type,value);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
