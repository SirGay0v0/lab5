package command;

import manager.MusicBandManager;
import model.Coordinates;
import model.MusicBand;
import model.MusicGenre;
import model.Studio;

import java.util.Scanner;


/**
 * Команда для обновления значения элемента коллекции по id.
 */
public class UpdateCommand implements Command {
    private final MusicBandManager manager;
    private final Scanner scanner;

    /**
     * Конструктор команды update.
     *
     * @param manager объект для управления коллекцией
     * @param scanner объект для считывания ввода
     */
    public UpdateCommand(MusicBandManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    /**
     * Метод для выполнения команды update.
     * Обновляет элемент коллекции с указанным id.
     */
    @Override
    public void execute() {
        System.out.print("Введите ID элемента для обновления: ");
        try {
            long id = Long.parseLong(scanner.nextLine().trim());

            MusicBand band = manager.getBandById(id);
            if (band == null) {
                System.out.println("Элемент с таким ID не найден.");
                return;
            }


            System.out.println("Обновление элемента с ID: " + id);

            String name = promptString("Введите новое название группы: ");
            Coordinates coordinates = promptCoordinates();
            Integer numberOfParticipants = promptInteger("Введите новое количество участников (или оставьте пустым): ", true);
            long singlesCount = promptLong("Введите новое количество синглов (больше 0): ", 1, Long.MAX_VALUE);
            MusicGenre genre = promptEnum("Выберите новый жанр: ", MusicGenre.class);
            Studio studio = promptStudio();

            // Обновляем поля объекта
            band.setName(name);
            band.setCoordinates(coordinates);
            band.setNumberOfParticipants(numberOfParticipants);
            band.setSinglesCount(singlesCount);
            band.setGenre(genre);
            band.setStudio(studio);

            System.out.println("Элемент успешно обновлен.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный формат ID.");
        }
    }

    // Вспомогательные методы для ввода данных

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
     * Ввод координат (объект Coordinates).
     */
    private Coordinates promptCoordinates() {
        System.out.println("Введите координаты:");
        Double x = promptDouble("Введите координату X (не может быть null): ");
        Long y = promptLong("Введите координату Y (не может быть null): ", Long.MIN_VALUE, Long.MAX_VALUE);
        return new Coordinates(x, y);
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
     * Ввод студии (объект Studio).
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
