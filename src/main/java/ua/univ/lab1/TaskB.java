package ua.univ.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/*
    Определить  класс Точка   на плоскости  (в  пространстве)  и во  времени.
    Задать  движение  точки  в определенном  направлении.  Создать  методы по
    определению скорости и ускорения точки. Проверить для двух точек возможность
    пересечения траекторий. Определить расстояние между двумя точками в заданный момент времени.
 */
public class TaskB
{
    public static void main(String[] args)
    {
        Point p1 = new Point(0,0,0,0);
        p1.setMoving(1,1,1);
        Point p2 = new Point (1,-1,1,0);
        p2.setMoving(-1,-1,1);

        System.out.println(p1.Check(p2));

        System.out.println(p1.getSpeed(2));
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

    public Point getMoving()
    {
        return new Point(this.dx,this.dy,this.dz);
    }

    public double Distance(Point otherPoint)
    {
        return Math.sqrt((this.x-otherPoint.x)*(this.x-otherPoint.x) + (this.y-otherPoint.y)*(this.y-otherPoint.y) + (this.z-otherPoint.z)*(this.z-otherPoint.z));
    }
    public double getSpeed(int t)
    {
        if (t==0) return 0;
        return this.Distance(this.getPoint(t))/t;
    }
    public double getA(int t)
    {
        if (t==0) return 0;
        return this.Distance(this.getPoint(t))/t;
    }

    public boolean Check(Point otherPoint)
    {
        double s = (otherPoint.getX()-this.getX())*(this.getMoving().getY()*otherPoint.getMoving().getZ() - this.getMoving().getZ()*otherPoint.getMoving().getY());
        double s1 = this.getMoving().getX()*((otherPoint.getY() - this.getY())*otherPoint.getMoving().getZ() - (otherPoint.getZ() - this.getZ())*otherPoint.getMoving().getY());
        double s2 = otherPoint.getMoving().getX()*((otherPoint.getY() - this.getY())*this.getMoving().getZ() - (otherPoint.getZ() - this.getZ())*this.getMoving().getY());
        /*
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        */
        if ((s-s1+s2)==0) return true;

        return false;
    }



}