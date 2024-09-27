package command;

import manager.MusicBandManager;
import model.MusicBand;

/**
 * Команда для удаления первого элемента коллекции и вывода его в стандартный поток вывода.
 */
public class RemoveHeadCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Конструктор команды remove_head.
     *
     * @param manager объект для управления коллекцией
     */
    public RemoveHeadCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    /**
     * Метод для выполнения команды remove_head.
     * Удаляет первый элемент коллекции и выводит его на экран.
     */
    @Override
    public void execute() {
        if (manager.getBands().isEmpty()) {
            System.out.println("Коллекция пуста, нечего удалять.");
        } else {
            MusicBand head = manager.getBands().removeFirst(); // Удаляем и получаем первый элемент
            System.out.println("Удалён первый элемент коллекции: " + head);
        }
    }
}
