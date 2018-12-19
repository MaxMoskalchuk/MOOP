package ua.univ.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    6. House : id,  Номер  квартиры,  Площадь,  Этаж,  Количество  комнат,  Улица, Тип здания, Срок эксплуатации.
    Создать массив объектов. Вывести:
        a)список квартир, имеющих заданное число комнат;
        b)список  квартир,  имеющих  заданное  число  комнат  и расположенных на этаже, который находится в заданном промежутке;
        c) список квартир, имеющих площадь, превосходящую заданную.
*/
public class TaskA
{
    public static void main(String[] args)
    {
        ArrayOfHouses houses = new ArrayOfHouses();

        houses.addHouses(
                new House(13, 25.2, 1, 1, "Gagarina", "high-rise building", 10),

                new House(125, 26., 5, 1, "Gagarina", "high-rise building", 10),

                new House(15, 50., 3, 3, "Glushkova", "high-rise building"),

                new House(21, 35., 10, 2, "Glushkova", "high-rise building", 15)
        );
        System.out.println("Cписок квартир, имеющих заданное число комнат 1");
        ArrayList<House> ListApartmentWithNumberOfRooms = houses.getApartmentWithGivenNumberOfRooms(1);
        for(House house : ListApartmentWithNumberOfRooms)
        {
            System.out.println(house);
        }

        System.out.println("Cписок квартир, имеющих заданное число комнат (1)  и расположенных на этаже, который находится в заданном промежутке[1;3];");
        ArrayList<House> ListApartmentWithNumberOfRoomsAndFloor = houses.getApartmentWithGivenNumberOfRoomsAndLocatedOnTheFloorWithTheInterval(1,1,3);
        for(House house : ListApartmentWithNumberOfRoomsAndFloor)
        {
            System.out.println(house);
        }

        System.out.println("список квартир, имеющих площадь, превосходящую заданную(30.). ");
        ArrayList<House> ListApartmentWithLargerArea = houses.getApartmentWithAreaLargerThanGiven(30.);
        for(House house : ListApartmentWithLargerArea)
        {
            System.out.println(house);
        }
    }

}

class ArrayOfHouses
{
    private ArrayList<House> houses = new ArrayList<>();

    public void addHouse(House house) {
        houses.add(house);
    }

    public void addHouses(House... houses) {
        for(House house : houses) {
            addHouse(house);
        }
    }

    public ArrayList<House> getApartmentWithGivenNumberOfRooms(int givenNumberOfRooms)
    {
        ArrayList<House> result = new ArrayList<>();
        for(House house : houses)
        {
            if (house.getNumberOfRooms()==givenNumberOfRooms)
            {
                result.add(house);
            }
        }
        return result;
    }

    public ArrayList<House> getApartmentWithGivenNumberOfRoomsAndLocatedOnTheFloorWithTheInterval(int givenNumberOfRooms, int minFloor, int maxFloor )
    {
        ArrayList<House> result = new ArrayList<>();
        ArrayList<House> ApartmentWithGivenNumberOfRooms = getApartmentWithGivenNumberOfRooms(givenNumberOfRooms);
        for(House house : ApartmentWithGivenNumberOfRooms)
        {
            if (house.getFloor()>=minFloor && house.getFloor()<=maxFloor)
            {
                result.add(house);
            }
        }
        return result;
    }

    public ArrayList<House> getApartmentWithAreaLargerThanGiven(double givenArea)
    {
        ArrayList<House> result = new ArrayList<>();
        for(House house : houses)
        {
            if (house.getArea() - givenArea > 0.0)
            {
                result.add(house);
            }
        }
        return  result;
    }
}
class House
{
    private static int housesCounter = 0;
    private final int id = housesCounter++;

    private int apartmentNumber;
    private double area;
    private int floor;
    private int numberOfRooms;
    private String street;
    private String typeOfBuilding;
    private int lifetime;

    House(int apartmentNumber, double area, int floor, int numberOfRooms, String street, String typeOfBuilding, int lifetime)
    {
        this.apartmentNumber = apartmentNumber;
        this.area = area;
        this.floor = floor;
        this.numberOfRooms = numberOfRooms;
        this.street = street;
        this.typeOfBuilding = typeOfBuilding;
        this.lifetime = lifetime;
    }

    House(int apartmentNumber, double area, int floor, int numberOfRooms, String street, String typeOfBuilding)
    {
        this.apartmentNumber = apartmentNumber;
        this.area = area;
        this.floor = floor;
        this.numberOfRooms = numberOfRooms;
        this.street = street;
        this.typeOfBuilding = typeOfBuilding;
        this.lifetime = 0;
    }

    public int getId()
    {
        return id;
    }

    public int getApartmentNumber()
    {

        return apartmentNumber;
    }

    public double getArea()
    {
        return area;
    }

    public int getFloor()
    {
        return floor;
    }

    public int getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public String getStreet()
    {
        return  street;
    }

    public String getTypeOfBuilding()
    {
        return typeOfBuilding;
    }

    public int getLifetime()
    {
        return  lifetime;
    }

    public void  setApartmentNumber(int apartmentNumber)
    {
        this.apartmentNumber=apartmentNumber;
    }

    public void setArea(double area)
    {
        this.area = area;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public void setNumberOfRooms(int numberOfRooms)
    {
        this.numberOfRooms = numberOfRooms;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTypeOfBuilding(String typeOfBuilding)
    {
        this.typeOfBuilding = typeOfBuilding;
    }

    public void setLifetime(int lifetime)
    {
        this.lifetime = lifetime;
    }

    @Override
    public String toString() {
        return String.format("House #%d: \n\tStreet: %s\n\t" +
                        "Apartment Number: %d\n\tFloor: %d\n\tNumber of rooms: %d\n\t" +
                        "Area : %f sq\n\tType of building: %s\n\tLifetime: %d years.",
                id,street,apartmentNumber,floor,numberOfRooms,area,typeOfBuilding,lifetime);
    }
}