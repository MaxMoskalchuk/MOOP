package ua.univ.lab4.TaskA;

import java.util.Scanner;

public class TaskA {
    public static void main(String ... args) {
        String string;
        Scanner scan = new Scanner(System.in);
        StringBuilder text = new StringBuilder();
        text.append(scan.nextLine());
        char ch = scan.next().charAt(0);
        boolean fl = scan.nextBoolean();
        if (fl==false)
        {
            for(int i=0;i<text.length(); i++)
                if (text.charAt(i) != ch) System.out.print(text.charAt(i));
             System.out.println();
        } else
        {
            int k = scan.nextInt();
            for(int i=0;i<text.length(); i++)
            {
                System.out.print(text.charAt(i));
                if (i%k == k-1) System.out.print(ch);
            }
        }
    }

}
