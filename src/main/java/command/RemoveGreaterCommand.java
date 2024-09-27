package command;

import manager.MusicBandManager;
import model.Coordinates;
import model.MusicBand;
import model.MusicGenre;
import model.Studio;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Команда для удаления всех элементов коллекции, превышающих заданный элемент.
 */
public class RemoveGreaterCommand implements Command {
    private final MusicBandManager manager;
    private final Scanner scanner;

    /**
     * Конструктор команды remove_greater.
     *
     * @param manager объект для управления коллекцией
     * @param scanner объект для считывания ввода
     */
    public RemoveGreaterCommand(MusicBandManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    /**
     * Метод для выполнения команды remove_greater.
     * Удаляет все элементы коллекции, которые превышают заданный элемент.
     */
    public void execute() {
        System.out.println("Удаление всех элементов, превышающих заданный элемент...");

        String name = promptString("Введите название группы: ");
        Coordinates coordinates = promptCoordinates();
        Integer numberOfParticipants = promptInteger("Введите количество участников (больше 0 или оставьте пустым): ", true);
        long singlesCount = promptLong("Введите количество синглов (больше 0): ", 1, Long.MAX_VALUE);
        MusicGenre genre = promptEnum("Выберите жанр: ", MusicGenre.class);
        Studio studio = promptStudio();

        long id = manager.generateId();
        LocalDateTime creationDate = LocalDateTime.now();

        MusicBand bandToCompare = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, genre, studio);

        Iterator<MusicBand> iterator = manager.getBands().iterator();
        boolean anyRemoved = false;
        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            if (band.compareTo(bandToCompare) > 0) {
                iterator.remove();
                anyRemoved = true;
            }
        }

        if (anyRemoved) {
            System.out.println("Элементы были удалены.");
        } else {
            System.out.println("Не найдено элементов, превышающих заданный.");
        }
    }

    // Вспомогательные методы для ввода данных

    private String promptString(String message) {
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Поле не может быть пустым.");
            }
        } while (input.isEmpty());
        return input;
    }

    private Coordinates promptCoordinates() {
        System.out.println("Введите координаты:");
        Double x = promptDouble("Введите координату X (не может быть null): ");
        Long y = promptLong("Введите координату Y (не может быть null): ", Long.MIN_VALUE, Long.MAX_VALUE);
        return new Coordinates(x, y);
    }

    private long promptLong(String message, long min, long max) {
        long value;
        while (true) {
            try {
                System.out.print(message);
                value = Long.parseLong(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.println("Значение должно быть в диапазоне от " + min + " до " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private Integer promptInteger(String message, boolean allowNull) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (allowNull && input.isEmpty()) {
                return null;
            }
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Значение должно быть больше 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private Studio promptStudio() {
        System.out.println("Введите информацию о студии:");
        String name = promptString("Введите название студии (может быть пустым): ");
        String address = promptString("Введите адрес студии (не может быть пустым): ");
        return new Studio(name, address);
    }

    private <T extends Enum<T>> T promptEnum(String message, Class<T> enumClass) {
        while (true) {
            System.out.println(message);
            for (T constant : enumClass.getEnumConstants()) {
                System.out.print(constant.name() + " ");
            }
            System.out.println();
            String input = scanner.nextLine().trim();
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: введите одно из предложенных значений.");
            }
        }
    }

    private Double promptDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }
}
