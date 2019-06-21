package Parser.Program;

public final class ForStatement implements Statement{
    private final Statement initialization;
    private final Expression termination;
    private final Statement increment;
    private final Statement block;


    public ForStatement(Statement initialization, Expression termination, Statement increment, Statement block) {
        this.initialization = initialization;
        this.termination = termination;
        this.increment = increment;
        this.block = block;
    }

    @Override
    public void execute() {
        for (initialization.execute(); termination.eval().asDouble() !=0; increment.execute()){
            try{
                block.execute();
            }catch (BreakStatement bs){
                break;
            }catch (ContinueStatement cs) {
                //continue;
            }
        }

    }

    @Override
    public String toString() {
        return "for " + initialization + ", " + termination + ", " + increment + " " + block;
    }
}
