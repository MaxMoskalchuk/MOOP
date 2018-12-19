package ua.univ.lab4.TaskC;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskC {
    public static void main(String ... args)
    {
        String s1;
        String s2;
        Scanner scanner = new Scanner(System.in);
        s1=scanner.nextLine();
        s2=scanner.nextLine();

        String s3 = "";

        for(int i=0;i<s2.length(); i++)
        {
            if (s2.charAt(i) == '*') {
                s3 += ".+";
            } else
                if(s2.charAt(i) == '?') s3+="."; else s3+=s2.charAt(i);
        }
        Pattern p = Pattern.compile("^"+s3+"$");
        Matcher m = p.matcher(s1);
        System.out.println(m.matches());
    }
}
