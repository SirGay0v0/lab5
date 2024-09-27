package command;

import manager.MusicBandManager;
import model.MusicBand;

import java.util.Scanner;

/**
 * Команда для вывода элементов коллекции, значение поля name которых содержит заданную подстроку.
 */
public class FilterContainsNameCommand implements Command {
    private final MusicBandManager manager;
    private final Scanner scanner;

    /**
     * Конструктор команды filter_contains_name.
     *
     * @param manager объект для управления коллекцией
     * @param scanner объект для считывания ввода
     */
    public FilterContainsNameCommand(MusicBandManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    /**
     * Метод для выполнения команды filter_contains_name.
     * Выводит все элементы коллекции, значение поля name которых содержит заданную подстроку.
     */
    @Override
    public void execute() {
        System.out.print("Введите подстроку для поиска в поле name: ");
        String substring = scanner.nextLine().trim();

        if (substring.isEmpty()) {
            System.out.println("Подстрока не может быть пустой.");
            return;
        }

        boolean found = false;
        for (MusicBand band : manager.getBands()) {
            if (band.getName().contains(substring)) {
                System.out.println(band);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Элементы с такой подстрокой в поле name не найдены.");
        }
    }
}