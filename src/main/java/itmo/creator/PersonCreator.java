package itmo.creator;

import itmo.model.Color;
import itmo.model.Country;
import itmo.model.Person;

public class PersonCreator {

    public Person createPerson() {
        LocationCreator locationCreator = new LocationCreator();
        Person person = new Person();

        person.setName(createName())
                .setHeight(createHeight())
                .setHairColor(createColor())
                .setNationality(createNationality())
                .setLocation(locationCreator.createLocation());

        return person;
    }

    private String createName() {
        String name;
        System.out.print("Name: ");
        name = System.console().readLine();
        if (name == null || name.isBlank()) {
            System.out.println("Name is blank or null. Try again.");
            name = createName();
        }
        return name;
    }

    private Integer createHeight() {
        Integer height;
        try {
            System.out.print("Height: ");
            height = Integer.parseInt(System.console().readLine());
            if (height == null || height <= 0) {
                System.out.println("Height must be more than 0. Try again.");
                height = createHeight();
            }
        } catch (Exception e) {
            System.out.println("Invalid height. Try again.");
            height = createHeight();
        }
        return height;
    }

    private Color createColor() {
        Color color;

        try {
            System.out.println("Color: ");
            color = Color.valueOf(System.console().readLine());
            if (color == null) {
                System.out.println("Color is blank or null. Try again.");
                color = createColor();
            }
        } catch (Exception e) {
            System.out.println("Invalid color. Try again.");
            color = createColor();
        }
        return color;
    }

    private Country createNationality() {
        Country country;
        try {
            System.out.print("Nationality: ");
            country = Country.valueOf(System.console().readLine());
        } catch (Exception e) {
            System.out.println("Invalid country. Try again.");
            country = createNationality();
        }
        return country;
    }
}
