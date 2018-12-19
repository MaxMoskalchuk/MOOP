package ua.univ.lab3.part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Asus on 04.11.2018.
 */
public final class Catalog {
   private final class HumanWithBook
   {
       private String name;
       private int StartRead;
       private int EndRead;

       public HumanWithBook(String name, int StartRead, int EndRead) {
           this.name = name;
           this.StartRead = StartRead;
           this.EndRead = EndRead;
       }

       public int getStartRead()
       {
           return this.StartRead;
       }

       public int getEndRead()
       {
           return this.EndRead;
       }

       @Override
       public String toString() {
           StringBuilder sb = new StringBuilder();
           sb.append("Human:\n\t")
                   .append("Name: ")
                   .append(name + "\n\t")
                   .append("Start read book: ")
                   .append(StartRead + "\n\t")
                   .append("End read book:")
                   .append(EndRead + "\n");
           return sb.toString();
       }
   }

   public final class Book
   {
       private String name;
       private ArrayList<HumanWithBook> humans = new ArrayList<>();

       public Book(String name)
       {
           this.name = name;
       }

       public void setName(String name)
       {
           this.name = name;
       }
       public void AddReader(HumanWithBook human)
       {
           humans.add(human);
       }
       class SortHuman implements Comparator<HumanWithBook> {
           public int compare(HumanWithBook h1, HumanWithBook h2) {
               return h1.getStartRead() - h2.getStartRead();
           }
       }
       public String getHistory()
       {
           Collections.sort(humans,new SortHuman());
            return humans.toString();
       }
   }
}
