package itmo.creator;

import itmo.model.Location;

public class LocationCreator {

    public Location createLocation() {
        Location location = new Location();

        location.setName(createName())
                .setX(createX())
                .setY(createYZ())
                .setZ(createYZ());

        return location;
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

    private float createX() {
        float x;
        try {
            System.out.print("Введите X: ");
            x = Float.parseFloat(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("X must be a number. Try again.");
            x = createX();
        }
        return x;
    }

    private Float createYZ() {
        Float y;
        try {
            System.out.print("Введите: ");
            y = Float.valueOf(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Number must be a number. Try again.");
            y = createX();
        }
        return y;
    }
}
