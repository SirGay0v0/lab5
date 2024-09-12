package itmo.creator.updater;

import itmo.model.Coordinates;

public class CoordinatesUpdater {

    public Coordinates create() {
        Float x = createX();
        Long y = createY();

        return new Coordinates(x, y);
    }

    private Float createX() {
        Float x;
        try {
            System.out.print("Введите X: ");
            x = Float.valueOf(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("X must be a number. Try again.");
            x = createX();
        }
        return x;
    }

    private Long createY() {
        Long y;
        try {
            System.out.print("Введите Y: ");
            y = Long.valueOf(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Y must be a number. Try again.");
            y = createY();
        }
        return y;
    }
}
