package command;

import manager.MusicBandManager;
import model.Color;
import model.Coordinates;
import model.Country;
import model.Location;
import model.MusicBand;
import model.MusicGenre;
import model.Person;

import java.util.Scanner;

/**
 * Класс для команды обновления элемента коллекции по его id.
 * Пользователь вводит id элемента и новые данные для обновления.
 */
public class UpdateCommand implements Command {
    private MusicBandManager manager;
    private long id;
    private Scanner scanner;

    public UpdateCommand(MusicBandManager manager, long id, Scanner scanner) {
        this.manager = manager;
        this.id = id;
        this.scanner = scanner;
    }

    @Override
    public void execute() {

        MusicBand band = manager.findById(id);
        if (band == null) {
            System.out.println("Элемент с id " + id + " не найден.");
            return;
        }

        String newName = promptString("Введите новое имя группы (текущее: " + band.getName() + "):", band.getName());

        Coordinates coordinates = band.getCoordinates();
        Float newX = promptFloat("Введите новые координаты (x) (текущее значение: " + coordinates.getX() + "):", coordinates.getX());
        Long newY = promptLong("Введите новые координаты (y) (текущее значение: " + coordinates.getY() + "):", coordinates.getY(), -490L, Long.MAX_VALUE);

        Coordinates newCoordinates = new Coordinates(newX, newY);

        long newParticipants = promptLong("Введите новое количество участников (текущее: " + band.getNumberOfParticipants() + "):", band.getNumberOfParticipants(), 1, Long.MAX_VALUE);

        MusicGenre newGenre = promptEnum("Текущий жанр: " + (band.getGenre() == null ? "не задан" : band.getGenre()), MusicGenre.class, band.getGenre());

        Person frontMan = band.getFrontMan();
        String newFrontManName = promptString("Введите новое имя фронтмена (текущее: " + frontMan.getName() + "):", frontMan.getName());
        Integer newHeight = promptInteger("Введите новый рост фронтмена (текущее значение: " + frontMan.getHeight() + "):", frontMan.getHeight(), 1, Integer.MAX_VALUE);

        Color newHairColor = promptEnum("Текущий цвет волос: " + frontMan.getHairColor(), Color.class, frontMan.getHairColor());

        Country newNationality = promptEnum("Текущая национальность: " + frontMan.getNationality(), Country.class, frontMan.getNationality());

        Location location = frontMan.getLocation();
        Float locX = promptFloat("Введите новое местоположение фронтмена (x) (текущее значение: " + location.getX() + "):", location.getX());
        Float locY = promptFloat("Введите новое местоположение фронтмена (y) (текущее значение: " + location.getY() + "):", location.getY());
        Float locZ = promptFloat("Введите новое местоположение фронтмена (z) (текущее значение: " + location.getZ() + "):", location.getZ());
        String locName = promptString("Введите новое название местоположения (текущее значение: " + location.getName() + "):", location.getName());

        Location newLocation = new Location(locX, locY, locZ, locName);

        Person newFrontMan = new Person(newFrontManName, newHeight, newHairColor, newNationality, newLocation);

        MusicBand updatedBand = new MusicBand(band.getId(), newName, newCoordinates, band.getCreationDate(), newParticipants, newGenre, newFrontMan);

        manager.updateElement(id, updatedBand);
    }

    private String promptString(String message, String currentValue) {
        System.out.println(message);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentValue : input;
    }

    private Long promptLong(String message, Long currentValue, long min, long max) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return currentValue;
                long value = Long.parseLong(input);
                if (value < min || value > max) {
                    System.out.println("Значение должно быть в пределах от " + min + " до " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private Float promptFloat(String message, Float currentValue) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return currentValue;
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private Integer promptInteger(String message, Integer currentValue, int min, int max) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return currentValue;
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Значение должно быть в пределах от " + min + " до " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private <T extends Enum<T>> T promptEnum(String message, Class<T> enumClass, T currentValue) {
        while (true) {
            System.out.println(message);

            T[] enumConstants = enumClass.getEnumConstants();
            System.out.println("Доступные значения: ");
            for (T constant : enumConstants) {
                System.out.print(constant.name() + " ");
            }
            System.out.println();


            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return currentValue;
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: введите одно из предложенных значений.");
            }
        }
    }
}
