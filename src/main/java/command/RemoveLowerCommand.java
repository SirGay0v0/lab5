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
 * Класс для команды удаления всех элементов, меньших, чем указанный элемент.
 * Пользователь вводит данные для сравнения, и элементы, меньшие по значению, удаляются из коллекции.
 */
public class RemoveLowerCommand implements Command {
    private MusicBandManager manager;
    private Scanner scanner;

    public RemoveLowerCommand(MusicBandManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        
        System.out.println("Удаление всех элементов, которые меньше заданного...");

        String name = promptString("Введите имя группы:");
        Float x = promptFloat("Введите координаты (x):");
        Long y = promptLong("Введите координаты (y):", -490L, Long.MAX_VALUE);
        Coordinates coordinates = new Coordinates(x, y);
        long participants = promptLong("Введите количество участников:", 1, Long.MAX_VALUE);
        MusicGenre genre = promptEnum("Выберите жанр:", MusicGenre.class, null);
        String frontManName = promptString("Введите имя фронтмена:");
        Integer frontManHeight = promptInteger("Введите рост фронтмена:", 1, Integer.MAX_VALUE);
        Color hairColor = promptEnum("Выберите цвет волос фронтмена:", Color.class, null);
        Country nationality = promptEnum("Выберите национальность фронтмена:", Country.class, null);
        Float locX = promptFloat("Введите местоположение фронтмена (x):");
        Float locY = promptFloat("Введите местоположение фронтмена (y):");
        Float locZ = promptFloat("Введите местоположение фронтмена (z):");
        String locName = promptString("Введите название местоположения фронтмена:");
        Location location = new Location(locX, locY, locZ, locName);
        Person frontMan = new Person(frontManName, frontManHeight, hairColor, nationality, location);

        
        long id = manager.generateId(); 
        java.time.LocalDate creationDate = java.time.LocalDate.now();

        
        MusicBand newBand = new MusicBand(id, name, coordinates, creationDate, participants, genre, frontMan);

        
        int removedCount = manager.removeLower(newBand);
        System.out.println("Удалено элементов: " + removedCount);
    }

    
    private String promptString(String message) {
        System.out.println(message);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Поле не может быть пустым. Повторите ввод.");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private Long promptLong(String message, long min, long max) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
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

    private Float promptFloat(String message) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private Integer promptInteger(String message, int min, int max) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().trim();
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

    private <T extends Enum<T>> T promptEnum(String message, Class<T> enumClass, T defaultValue) {
        while (true) {
            System.out.println(message);
            T[] enumConstants = enumClass.getEnumConstants();
            for (T constant : enumConstants) {
                System.out.print(constant.name() + " ");
            }
            System.out.println();
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return defaultValue;  
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: введите одно из предложенных значений.");
            }
        }
    }
}
