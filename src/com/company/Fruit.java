package com.company;

public class Fruit implements Collidable{
    private Coordinates coordinates;

    Fruit(Coordinates coordinates) {
        this.coordinates = new Coordinates(coordinates.x, coordinates.y);
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates.x = coordinates.x;
        this.coordinates.y = coordinates.y;
    }

    public String getName() {
        return "Fruit";
    }
}
