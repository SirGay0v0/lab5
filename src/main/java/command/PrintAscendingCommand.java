package command;

import manager.MusicBandManager;
import model.MusicBand;
/**
 * Класс для команды вывода элементов коллекции в порядке возрастания.
 */
public class PrintAscendingCommand implements Command {
    private MusicBandManager manager;

    public PrintAscendingCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        if (manager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println("Элементы коллекции в порядке возрастания:");
            for (MusicBand band : manager.getCollection()) {
                System.out.println(band);
            }
        }
    }
}