package command;

import manager.MusicBandManager;

/**
 * Команда для очистки коллекции.
 */
public class ClearCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Конструктор команды clear.
     *
     * @param manager объект для управления коллекцией
     */
    public ClearCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    /**
     * Метод для выполнения команды clear.
     * Очищает коллекцию, удаляя все элементы.
     */
    @Override
    public void execute() {
        if (manager.getBands().isEmpty()) {
            System.out.println("Коллекция уже пуста.");
        } else {
            manager.clear();
            System.out.println("Коллекция успешно очищена.");
        }
    }
}
