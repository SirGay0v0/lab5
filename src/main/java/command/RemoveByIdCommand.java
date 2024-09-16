package command;

import manager.MusicBandManager;

import java.util.Scanner;
/**
 * Класс для команды удаления элемента коллекции по его id.
 * Пользователь вводит id элемента, который нужно удалить.
 */
public class RemoveByIdCommand implements Command {
    private MusicBandManager manager;
    private Scanner scanner;

    public RemoveByIdCommand(MusicBandManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введите id элемента, который нужно удалить: ");
        long id = promptLong();

        boolean success = manager.removeById(id);
        if (success) {
            System.out.println("Элемент с id " + id + " был успешно удалён.");
        } else {
            System.out.println("Элемент с id " + id + " не найден.");
        }
    }

    private long promptLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }
}