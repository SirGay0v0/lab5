package command;

import manager.MusicBandManager;
import model.Coordinates;
import model.MusicBand;
import model.MusicGenre;
import model.Studio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Класс для команды добавления нового музыкального элемента в коллекцию.
 * Пользователь вводит данные для нового элемента, которые затем добавляются в коллекцию.
 */
public class AddCommand implements Command {
    private MusicBandManager manager;
    private Scanner scanner;

    /**
     * Конструктор для команды добавления.
     *
     * @param manager объект для управления коллекцией
     * @param scanner объект для считывания ввода
     */
    public AddCommand(MusicBandManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    /**
     * Метод для выполнения команды добавления.
     */
    public void execute() {
        System.out.println("Добавление нового элемента в коллекцию.");

        String name = promptString("Введите название группы (не может быть пустым): ");
        Coordinates coordinates = promptCoordinates();
        Integer numberOfParticipants = promptInteger("Введите количество участников (больше 0 или оставьте пустым): ", true);
        long singlesCount = promptLong("Введите количество синглов (больше 0): ", 1, Long.MAX_VALUE);
        MusicGenre genre = promptEnum("Выберите жанр: ", MusicGenre.class);
        Studio studio = promptStudio();

        long id = manager.generateId();  // Метод генерации уникального ID
        LocalDateTime creationDate = LocalDateTime.now();

        MusicBand newBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, genre, studio);

        manager.addBand(newBand);
        System.out.println("Элемент успешно добавлен в коллекцию.");
    }

    /**
     * Ввод строки с проверкой на пустоту.
     */
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

    /**
     * Ввод целого числа с проверкой на диапазон.
     */
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

    /**
     * Ввод целого числа с возможностью оставить поле пустым.
     */
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

    /**
     * Ввод объекта Coordinates.
     */
    private Coordinates promptCoordinates() {
        System.out.println("Введите координаты:");
        Double x = promptDouble("Введите координату X (не может быть null): ");
        Long y = promptLong("Введите координату Y (не может быть null): ", Long.MIN_VALUE, Long.MAX_VALUE);
        return new Coordinates(x, y);
    }

    /**
     * Ввод объекта Studio.
     */
    private Studio promptStudio() {
        System.out.println("Введите информацию о студии:");
        String name = promptString("Введите название студии (может быть пустым): ");
        String address = promptString("Введите адрес студии (не может быть пустым): ");
        return new Studio(name, address);
    }

    /**
     * Ввод перечисляемого типа с проверкой корректности.
     */
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

    /**
     * Ввод значения с плавающей точкой (Double).
     */
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

