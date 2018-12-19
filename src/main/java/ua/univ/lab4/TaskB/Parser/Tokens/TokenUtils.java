package ua.univ.lab4.TaskB.Parser.Tokens;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class TokenUtils {

    public static List<IToken> sortFirstCh(List<IToken> tokens) {
        Collections.sort(tokens, (t1 , t2) -> {
            boolean isT1Word = t1 instanceof Word;
            boolean isT2Word = t2 instanceof Word;
            if(!(isT1Word || isT2Word)) return 0;
            if(isT1Word && !isT2Word) return -1;
            if(isT2Word && !isT1Word) return 1;
            Word w1 = (Word)t1;
            Word w2 = (Word)t2;
            return search(w1.toString(),w2.toString());
        });


        return tokens;
    }
    public static int search(String s1, String s2)
    {
        for(int i=0; i<Math.min(s1.length(),s2.length()); i++)
        {
            if (s1.charAt(i)!=s2.charAt(i)) return Character.compare(s1.charAt(i),s2.charAt(i));
        }
        return Integer.compare(s1.length(),s2.length());
    }
}
