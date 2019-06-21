package Parser.Program;

import Parser.Program.Variables;

//оператор присвоения
public final class AssigmentStatement implements Statement{

    private final String variable;
    private final Expression expression;

    public AssigmentStatement(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute() {
        final Value result = expression.eval();
        Variables.set(variable, result);
    }

    @Override
    public String toString(){
        return String.format("%s = %s", variable, expression);
    }


}
