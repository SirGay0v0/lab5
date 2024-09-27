package command;

import manager.MusicBandManager;

import java.util.Scanner;
public class RemoveByIdCommand implements Command {
    private final MusicBandManager manager;
    private final Scanner scanner;

    /**
     * Конструктор команды remove_by_id.
     *
     * @param manager объект для управления коллекцией
     * @param scanner объект для считывания ввода
     */
    public RemoveByIdCommand(MusicBandManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    /**
     * Метод для выполнения команды remove_by_id.
     * Удаляет элемент коллекции с указанным id.
     */
    @Override
    public void execute() {
        System.out.print("Введите ID элемента для удаления: ");
        try {
            long id = Long.parseLong(scanner.nextLine().trim());

            boolean removed = manager.removeById(id);

            if (removed) {
                System.out.println("Элемент с ID " + id + " успешно удален.");
            } else {
                System.out.println("Элемент с таким ID не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный формат ID.");
        }
    }
}