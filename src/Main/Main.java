package Main;

import Parser.Lexer;
import Parser.Parser;
import Parser.Program.Statement;
import Parser.Program.Variables;
import Parser.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Main {
    public static void main(String[] args) throws IOException {
        final String input1 =new String(Files.readAllBytes(Paths.get("Program.txt")), "UTF-8");
         List<Token> tokens = new Lexer(input1).tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }
        final Statement program = new Parser(tokens).parse();
        System.out.println(program.toString());
        program.execute();
    }
}
