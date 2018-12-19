package ua.univ.lab3.part1;

import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Asus on 05.11.2018.
 */
public class TaskA {
    public static void main(String ... args)
    {
        Catalog catalog = new Catalog();

        ArrayList<String> readers = new ArrayList<String>(){{
            for (int i = 0; i <20 ; i++) {
                add("Reader" + i);
            }
        }};
        ArrayList<String> booksTaken = new ArrayList<>();


        for (String reader : readers){

            if ((int) (Math.random()*60) %2 == 0) {
                String currentBook = "Book" + (int) (Math.random()*14);
                String currentBookId = catalog.takeBook(reader, currentBook);
                if (!currentBookId.equals("taken")){
                    booksTaken.add(currentBookId);
                }

            } else {
                if (!booksTaken.isEmpty())
                    catalog.returnBook("Reader" + (int) (Math.random()*40), booksTaken.get(0));
            }

        }

        System.out.println(catalog);




    }
}
