package command;

import manager.MusicBandManager;
/**
 * Команда для вывода информации о коллекции.
 */
public class InfoCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Конструктор команды info.
     *
     * @param manager объект для управления коллекцией
     */
    public InfoCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    /**
     * Метод для выполнения команды info.
     * Выводит тип коллекции, дату инициализации и количество элементов.
     */
    @Override
    public void execute() {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: " + manager.getBands().getClass().getSimpleName());
        System.out.println("Дата инициализации: " + manager.getInitializationDate());
        System.out.println("Количество элементов: " + manager.getBands().size());
    }
}
