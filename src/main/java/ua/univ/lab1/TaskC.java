package ua.univ.lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TaskC
{
    public static void main(String[] args)
    {
        try {
            Circle Circles[] = new Circle[7];
            Circles[0] = new Circle(new RationalNumber(1, 2),
                    new RationalNumber(2, 2), new RationalNumber(3, 2));

            Circles[1] = (new Circle(new RationalNumber(2, 2),
                    new RationalNumber(3, 2), new RationalNumber(4, 2)));

            Circles[2] = (new Circle(new RationalNumber(3, 2),
                    new RationalNumber(4, 2), new RationalNumber(5, 2)));

            Circles[3] = (new Circle(new RationalNumber(5, 2),
                    new RationalNumber(6, 2), new RationalNumber(6, 2)));

            Circles[4] = (new Circle(new RationalNumber(3, 2),
                    new RationalNumber(8, 2), new RationalNumber(7, 2)));

            Circles[5] = (new Circle(new RationalNumber(1, 2),
                    new RationalNumber(10, 2), new RationalNumber(8, 2)));

            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    for (int k = j + 1; k < 6; k++) {
                        if (onSameLine(Circles[i], Circles[j], Circles[k])) {
                            System.out.println(Circles[i]);
                            System.out.println(Circles[j]);
                            System.out.println(Circles[k]);
                            System.out.println();
                        }
                    }
                }

            }
            System.out.println("Окружность с максимальной площадью");
            System.out.println(maxArea(Circles));
        }
        catch (ArithmeticException ex) {
            ex.printStackTrace();
        }
    }
    public static Circle maxArea(Circle[] Circles)
    {
        double maxArea = 0;
        Circle maxCircle = Circles[0];
        for(int i=0; i<6; i++)
        {
            if (Circles[i].getArea().toDouble()>maxArea)
            {
                maxCircle=Circles[i];
            }
        }
        return maxCircle;
    }
    public static double distance(Circle c1, Circle c2)
    {
        return Math.sqrt(c1.x.subtract(c2.x).pow(2).add(c1.y.subtract(c2.y).pow(2)).toDouble());
    }
    public static boolean onSameLine(Circle c1, Circle c2, Circle c3)
    {
        double distances[] =
                {
                        distance(c1,c2),
                        distance(c2,c3),
                        distance(c3,c1)
                };
        Arrays.sort(distances);
        return Math.abs(distances[2]-(distances[0]+distances[1]))<0.0001;
    }
}


class Circle
{
    private final RationalNumber PI = new RationalNumber(355,113);
    RationalNumber x,y,r;
    Circle(RationalNumber x, RationalNumber y, RationalNumber r)
    {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public RationalNumber getPerimeter()
    {
        RationalNumber result = new RationalNumber(2,1);
        return result.multiply(r.multiply(PI));
    }
    public RationalNumber getArea()
    {
        RationalNumber result = new RationalNumber(1,1);
        return result.multiply(PI.multiply(r.pow(2)));
    }
    @Override
    public String toString()
    {
        return String.format("(%s, %s, %s)", x, y, r);
    }
}
class RationalNumber{
    private int numerator;
    private int denominator;

    RationalNumber(int numerator, int denominator) throws ArithmeticException{
        if(denominator == 0) throw new ArithmeticException("division by zero");
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    private int gcd(int a , int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    public void simplify() {
        int g = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= g;
        denominator /= g;
        if(denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    public RationalNumber multiply(RationalNumber other) {
        RationalNumber number = new RationalNumber(numerator, denominator);
        number.numerator *= other.numerator;
        number.denominator *= other.denominator;
        number.simplify();
        return number;
    }

    public RationalNumber add(RationalNumber other) {
        RationalNumber number = new RationalNumber(numerator, denominator);
        number.numerator = numerator * other.denominator + other.numerator * denominator;
        number.denominator *= other.denominator;
        number.simplify();
        return number;
    }

    public RationalNumber subtract(RationalNumber other) {
        RationalNumber number = new RationalNumber(numerator, denominator);
        return number.add(new RationalNumber(-other.numerator, other.denominator));
    }

    public RationalNumber divide(RationalNumber other) throws ArithmeticException {
        if(other.numerator == 0) throw new ArithmeticException("division by zero");
        RationalNumber number = new RationalNumber(numerator, denominator);
        return number.multiply(new RationalNumber(other.denominator, other.numerator));
    }

    public RationalNumber pow(int n) {
        return new RationalNumber((int)Math.pow(numerator, n), (int)Math.pow(denominator, n));
    }

    public double toDouble() {
        return 1.0 * numerator / denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + (denominator != 1 ? "/" + denominator : "");
    }
}