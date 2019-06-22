package spo.RPN;

import spo.Lexer1.TokenOperand;
import spo.Lexer1.TokenOperator;
import spo.Lexer1.LexemType;
import spo.Lexer1.Token;
import java.util.*;

public class RPN { //Обратная польская запись
    private static Stack<TokenOperator> stack = new Stack<>();
    private static int i;
    private ArrayList<Token> output = new ArrayList<>();
    private ArrayList<Token> input;


    public RPN(ArrayList<Token> tokens){
        this.input=tokens;
    }

    private void exprHandle(){
        if(input.get(i) instanceof TokenOperand){
            output.add(input.get(i));
        }
        else if(input.get(i) instanceof TokenOperator){
            OperatorHandle();
        }
        else if(input.get(i).getType().equals("END")){
            while(!stack.empty()){
                output.add(stack.pop());
            }
        }
    }

    private void OperatorHandle() {
        TokenOperator tokenOperator = (TokenOperator) input.get(i);
        if (tokenOperator.getType().equals("L_B")) {
            stack.push(tokenOperator);
        } else if (tokenOperator.getType().equals("R_B")) {
            while (!stack.peek().getType().equals("L_B")) {
                output.add(stack.pop());
            }
            stack.pop();
        } else if (!stack.empty() && tokenOperator.getPriority() <= stack.peek().getPriority()) {
            while (!stack.empty() && tokenOperator.getPriority() <= stack.peek().getPriority()) {
                output.add(stack.pop());
            }
            stack.push(tokenOperator);
        } else {
            stack.push(tokenOperator);
        }
    }

    private void cycleRPN(){
        int JmpToStart = output.size();
        int buff;
        while(!input.get(i).getType().equals("L_F_B")){
            exprHandle();
            i++;
        }
        buff = output.size();
        output.add(new TokenOperand("VAR"," "));
        output.add(new TokenOperator(0,LexemType.J_C.getType(),"!F"));
        i++;
        while(!input.get(i).getType().equals("R_F_B")){
            switch (input.get(i).getType()) {
                case "CYCLE":
                    cycleRPN();
                    break;
                case "PRINT":
                    printHandle();
                    break;
                default:
                    exprHandle();
                    break;
            }
            i++;
        }
        output.add(new TokenOperand("DIGIT",Integer.toString(JmpToStart)));
        output.add(new TokenOperator(0,LexemType.JMP.getType(),"!"));
        output.set(buff,new TokenOperand("DIGIT",Integer.toString(output.size())));
    }

    private void printHandle(){
        i++;
        while(!input.get(i).getType().equals("END")){
            if(input.get(i) instanceof TokenOperand){
                output.add(input.get(i));
                output.add(new TokenOperator(0,"PRINT","print"));
            }
            i++;
        }
    }

    public void toRPN() {
        for(i = 0; i<input.size(); i++){
            switch (input.get(i).getType()) {
                case "CYCLE":
                    cycleRPN();
                    break;
                case "PRINT":
                    printHandle();
                    break;
                default:
                    exprHandle();
                    break;
            }
        }
        output.add(new TokenOperand("VAR","end point"));
    }

    public ArrayList<Token> getOutput(){
        return output;
    }
}
