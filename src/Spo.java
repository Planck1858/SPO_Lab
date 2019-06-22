import spo.Lexer1.Lexer;
import spo.Parser.Parser1;
import spo.Lexer1.Token;
import spo.RPN.RPN;
import spo.StackMachine.StackMachine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Spo{

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        ArrayList<Token> tokens;
        final String input = new String(Files.readAllBytes(Paths.get("D:\\My\\Универ-МИРЭА\\6 семестр\\Системное ПО\\gach\\SPO_Lab\\src\\spo", "programm.txt")),"UTF-8");
        Lexer lexer = new Lexer();
        tokens = lexer.getTokenList(input);
        Parser1 parser = new Parser1(tokens);
        parser.parse();
        RPN rpn = new RPN(tokens);
        rpn.toRPN();
        StackMachine stackMachine = new StackMachine(rpn.getOutput());
        stackMachine.calculation();
        long finish = System.nanoTime();
        System.out.println("\n\n"+"Execution time = "+((finish-start)/Math.pow(10,6))+" ms");
        //System.out.println(lexer.getTokenList(input));
    }
}