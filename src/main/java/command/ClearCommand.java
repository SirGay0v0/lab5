package command;

import manager.MusicBandManager;
/**
 * Класс для команды очистки коллекции.
 * Удаляет все элементы из коллекции.
 */
public class ClearCommand implements Command {
    private MusicBandManager manager;

    public ClearCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.clearCollection();
    }
}
