package ua.univ.lab2.TaskB;

/**
 * Created by Asus on 04.11.2018.
 */

import javafx.util.Pair;
import ua.univ.lab2.TaskB.Stones.*;
import ua.univ.lab2.TaskB.Necklace.*;

import java.util.Scanner;

public class TaskB {
    public static GemStones parsePreciousStone(Scanner sc) {
        PreciousStoneBuilder pb = new PreciousStoneBuilder();
        int set;
        boolean done = false;
        while(!done) {
            System.out.println("What do you want to set? (1 - trasparency, 2 - name , 3 - price , 4 - weight , 5 - manufacturer , 6 - color , 7 - hardness, 8 - finish)");
            set = sc.nextInt();
            switch (set) {
                case 1:
                    System.out.print("Enter trasparency: ");
                    int tp = sc.nextInt();
                    pb.setTransparency(tp);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String nm = sc.next().trim();
                    pb.setName(nm);
                    break;
                case 3:
                    System.out.print("Enter price: ");
                    int pr = sc.nextInt();
                    pb.setPrice(pr);
                    break;
                case 4:
                    System.out.print("Enter weight: ");
                    double we = sc.nextDouble();
                    pb.setWeight(we);
                    break;
                case 5:
                    System.out.print("Enter manufacturer: ");
                    String ma = sc.next().trim();
                    pb.setManufacturer(ma);
                    break;
                case 6:
                    System.out.print("Enter color: ");
                    String cl = sc.next().trim();
                    pb.setColor(cl);
                    break;
                case 7:
                    System.out.print("Enter hardness: ");
                    int hn = sc.nextInt();
                    pb.setHardness(hn);
                    break;
                case 8:
                    done = true;
                    break;
                default:
                    System.out.println("Wrong input.");
            }
        }
        return pb.build();
    }
    public static GemStones parseHalfPreciousStone(Scanner sc) {
        HalfPreciousStoneBuilder hpb = new HalfPreciousStoneBuilder();
        int set;
        boolean done = false;
        while(!done) {
            System.out.println("What do you want to set? (1 - trasparency, 2 - name , 3 - price , 4 - weight , 5 - manufacturer , 6 - color , 7  - finish)");
            set = sc.nextInt();
            switch (set) {
                case 1:
                    System.out.print("Enter trasparency: ");
                    int tp = sc.nextInt();
                    hpb.setTransparency(tp);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String nm = sc.next().trim();
                    hpb.setName(nm);
                    break;
                case 3:
                    System.out.print("Enter price: ");
                    int pr = sc.nextInt();
                    hpb.setPrice(pr);
                    break;
                case 4:
                    System.out.print("Enter weight: ");
                    double we = sc.nextDouble();
                    hpb.setWeight(we);
                    break;
                case 5:
                    System.out.print("Enter manufacturer: ");
                    String ma = sc.next().trim();
                    hpb.setManufacturer(ma);
                    break;
                case 6:
                    System.out.print("Enter color: ");
                    String cl = sc.next().trim();
                    hpb.setColor(cl);
                    break;
                case 7:
                    done = true;
                    break;
                default:
                    System.out.println("Wrong input.");
            }
        }
        return hpb.build();
    }
    public static void main(String [] args){
        Necklace necklace = new Necklace("Necklace");
        Scanner sc = new Scanner(System.in);
        int set = 0;
        boolean finish = false;
        do {

            try {
                System.out.println("1 - Add PreciousStone, 2 - Add HalfPreciousStone, 3 - sort, 4 - price&weigth, 5 - trasparency, 6 - necklace, 7 - finish ");
                set = sc.nextInt();
                switch (set) {
                    case 1:
                        GemStones s1 = parsePreciousStone(sc);
                        necklace.addStone(s1);
                        break;
                    case 2:
                        GemStones s2 = parseHalfPreciousStone(sc);
                        necklace.addStone(s2);
                        break;
                    case 3:
                        NecklaceUtils.sortStones(necklace);
                        break;
                    case 4:
                        Pair<Integer,Double> pr = NecklaceUtils.getTotalPrice(necklace);
                        System.out.println("Price: " + pr.getKey().toString() + " , Weight: " + pr.getValue().toString() );
                        break;
                    case 5:
                        System.out.print("Enter the bounds: ");
                        int lowerBound = sc.nextInt();
                        int upperBound = sc.nextInt();
                        if (lowerBound > upperBound) {
                            lowerBound ^= upperBound;
                            upperBound ^= lowerBound;
                            lowerBound ^= upperBound;
                            System.out.println("Assuming that you want [" + lowerBound + "; " + upperBound + "]");
                        }
                        System.out.println(NecklaceUtils.getStones(necklace, lowerBound, upperBound));
                        sc.nextLine();
                        break;
                    case 6:
                        System.out.println("Necklace: " + necklace.toString());

                        break;
                    case 7:
                        finish = true;
                        break;
                    default:
                        System.out.println("Wrong input.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!finish);
    }
}
