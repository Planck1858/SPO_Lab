package spo.StackMachine;

import spo.Lexer1.Token;
import spo.MyHashSet.MyHashSet;
import spo.MyLinkedList.MyLinkedList;
import spo.Lexer1.TokenOperand;
import spo.Lexer1.TokenOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class StackMachine {
    private ArrayList<Token> tokens;
    private Stack<Token> stackMachine = new Stack<>();
    private HashMap<String, Variable> variablesTable = new HashMap<>();
    private static int i;
    private static Variable assignTo;
    private MyLinkedList list;
    private MyHashSet set;

    public StackMachine(ArrayList<Token> tokens1) {
        this.tokens = tokens1;
    }

    public void calculation() {
        for (i = 0; i < tokens.size() - 1; i++)
            if (tokens.get(i) instanceof TokenOperand) {
                stackMachine.push(tokens.get(i));
            } else if (tokens.get(i) instanceof TokenOperator) {
                String value = tokens.get(i).getValue();
                if (tokens.get(i).getType().equals("METHOD") || value.equals(".get"))
                    methodOp(value);
                else
                    binaryOp(value);
            } else if (tokens.get(i).getValue().equals("end point")) break;
    }

    private float getOperand() {
        if (stackMachine.peek().getType().equals("VAR")) {
            String var = stackMachine.pop().getValue();
            try {
                assignTo = variablesTable.get(var);
                return assignTo.getValue();
            } catch (NullPointerException e) {
                throw new NullPointerException("Variable " + var + " is not initialized");
            }

        } else {
            return Float.parseFloat(stackMachine.pop().getValue());
        }
    }

    private void switchOp(float a, float b, String value) {
        float result;
        Integer compare;
        switch (value) {
            case "+":
                result = b + a;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case "-":
                result = b - a;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case "/":
                result = b / a;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case "*":
                result = b * a;
                stackMachine.push(new TokenOperand("DIGIT", Float.toString(result)));
                break;
            case ":=":
                assignTo.setValue(a);
                break;
            case "==":
                compare = Boolean.compare(b == a, false);
                stackMachine.push(new TokenOperand("DIGIT", compare.toString()));
                break;
            case ">":
                compare = Boolean.compare(b > a, false);
                stackMachine.push(new TokenOperand("DIGIT", compare.toString()));
                break;
            case "<":
                compare = Boolean.compare(b < a, false);
                stackMachine.push(new TokenOperand("DIGIT", compare.toString()));
                break;
            default:
                break;
        }


    }

    private void binaryOp(String value) {
        switch (value) {
            case "!F":
                int goTo = Integer.parseInt(stackMachine.pop().getValue());
                i = stackMachine.pop().getValue().equals("0") ? goTo - 1 : i;
                break;
            case "!":
                i = Integer.parseInt(stackMachine.pop().getValue()) - 1;
                break;
            case "print":
                Variable hy = variablesTable.get(stackMachine.peek().getValue());
                if (hy == null) {
                    throw new NullPointerException("Variable " + stackMachine.peek().getValue() + " is not exist!!!");
                } else if (hy.getType().equals("List")) {
                    printList(hy.getList(), stackMachine.pop().getValue());
                } else {
                    printVar(stackMachine.pop().getValue());
                }
                break;
            case "type":
                String buff = stackMachine.pop().getValue();
                switch (buff) {
                    case "List":
                        variablesTable.put(stackMachine.pop().getValue(), new Variable(buff, new MyLinkedList()));
                        break;
                    case "HashSet":
                        variablesTable.put(stackMachine.pop().getValue(), new Variable(buff, new MyHashSet()));
                        break;
                    default:
                        variablesTable.put(stackMachine.pop().getValue(), new Variable(buff, 0));
                        break;
                }
                break;

            default:
                switchOp(getOperand(), getOperand(), value);
                break;
        }
    }

    private void printVar(String string) {
        try {
            System.out.println(variablesTable.get(string).getType() + " " + string + " := " + variablesTable.get(string).getValue() + ";");
        } catch (NullPointerException e) {
            throw new NullPointerException("Variable" + " " + string + " " + "is not initialized");
        }
    }

    private void printList(MyLinkedList list, String varName) {
        System.out.print("Values of '" + varName + "': ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("[" + list.get(i) + "]");
        }
        System.out.print(";\n");
    }

    private void methodOp(String value) {
        float arg2 = getOperand();
        int arg1 = 0;
        if (value.equals(".set")) {
            arg1 = Integer.parseInt(stackMachine.pop().getValue());
        }
        String varName = stackMachine.peek().getValue();
        Variable variable = variablesTable.get(varName);
        try {
            if (variable.getList() != null) {
                list = variablesTable.get(stackMachine.pop().getValue()).getList();
            } else if (variable.getSet() != null) {
                set = variablesTable.get(stackMachine.pop().getValue()).getSet();
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("Cannot apply operation " + value + " to variable " + stackMachine.pop().getValue());
        }

        switch (value) {
            case ".add":
                if (variable.getList() != null) {
                    list.add(arg2);
                } else if (variable.getSet() != null) {
                    set.add(arg2);
                }
                break;
            case ".remove":
                if (variable.getList() != null) {
                    list.remove(arg2);
                } else if (variable.getSet() != null) {
                    set.remove(arg2);
                }
                break;
            case ".contains":
                if (variable.getList() != null) {
                    System.out.println("List '" + varName + "' contains '" + arg2 + "' is: " + list.contains(arg2));
                } else if (variable.getSet() != null) {
                    System.out.println("HashSet '" + varName + "' contains '" + arg2 + "' is: " + set.contains(arg2));
                }
                break;
            case ".get":
                if (variable.getList() != null) {
                    TokenOperand token = new TokenOperand("DIGIT", String.valueOf(list.get((int) arg2)));
                    stackMachine.push(token);
                } else if (variable.getSet() != null) {
                    throw new NullPointerException("Cannot apply operation " + value + " to variable type HashSet");
                }
                break;
            case ".set":
                if (variable.getList() != null) {
                    list.set(arg1, arg2);
                } else if (variable.getSet() != null) {
                    throw new NullPointerException("Cannot apply operation " + value + " to variable type HashSet");
                }
                break;
            default:
                break;
        }
    }

}