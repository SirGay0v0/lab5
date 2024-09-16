package command;

import manager.MusicBandManager;
/**
 * Класс для команды вывода информации о коллекции.
 * Отображает данные о коллекции, такие как тип, количество элементов, дата инициализации и т.д.
 */
public class InfoCommand implements Command {
    private MusicBandManager manager;

    public InfoCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        System.out.println(manager.getInfo());
    }
}
