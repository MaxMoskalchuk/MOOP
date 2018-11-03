package ua.univ.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskB
{
    public static void main(String[] args)
    {

    }
}

class Point
{
    private  double x,y,z;
    private int t;
    private  double dx,dy,dz;


    Point(double x, double y, double z,int t)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
    }

    Point(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = 0;
    }

    Point (double x, double y)
    {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.t = 0;
    }

    public Point getPoint(int t)
    {
        Point result = new Point(this.x+dx*(t-this.t),this.y+dy*(t-this.t),this.z+dz*(t-this.t),t);
        return result;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public int getT()
    {
        return  t;
    }
    public void setT(int t)
    {
        this.t=t;
    }

    public void setMoving(double dx, double dy, double dz)
    {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

}