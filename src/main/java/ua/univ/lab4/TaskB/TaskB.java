package ua.univ.lab4.TaskB;

import ua.univ.lab4.TaskB.Parser.Parser;
import ua.univ.lab4.TaskB.Parser.Tokens.*;
import java.util.List;
import java.util.Collections;

public class TaskB {
    public static void main(String ... args) {
        Parser parser = new Parser("D:\\Univ\\V\\MOOP\\src\\main\\java\\ua\\univ\\lab4\\TaskB\\input.txt");
        List<IToken> tokens = parser.parse();

        if(tokens != null) {
            tokens = TokenUtils.sortFirstCh(tokens);
            System.out.println(tokens.get(0).toString());
            for (int i = 1; i < tokens.size(); ++i) {
                try {
                    Word w = (Word) tokens.get(i);
                    Word w1 = (Word) tokens.get(i - 1);
                    if (w.toString().charAt(0) != w1.toString().charAt(0)) System.out.println();
                    if (!(w.toString().equals(w1.toString()))) System.out.println(w.toString());
                }catch (Exception ex)
                {

                }
            }
        }
    }
}
