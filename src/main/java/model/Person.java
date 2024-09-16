package model;

public class Person {
    private String name;
    private Integer height;
    private Color hairColor;
    private Country nationality;
    private Location location;

    public Person(String name, Integer height, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }
}

