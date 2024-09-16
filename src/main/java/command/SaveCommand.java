package command;

import manager.MusicBandManager;
/**
 * Класс для команды сохранения текущей коллекции в файл.
 * Сохраняет все элементы коллекции в файл в формате JSON.
 */
public class SaveCommand implements Command {
    private MusicBandManager manager;

    public SaveCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.saveCollection();
    }
}

