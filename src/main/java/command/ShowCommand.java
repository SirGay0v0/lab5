package command;

import manager.MusicBandManager;
/**
 * Класс для команды вывода всех элементов коллекции в строковом представлении.
 * Отображает каждый элемент коллекции в порядке добавления.
 */
public class ShowCommand implements Command {
    private MusicBandManager manager;

    public ShowCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.showCollection();
    }
}