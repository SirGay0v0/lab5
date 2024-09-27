package command;

import manager.MusicBandManager;
import model.MusicBand;

/**
 * Команда для вывода всех элементов коллекции в строковом представлении.
 */
public class ShowCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Конструктор команды show.
     *
     * @param manager объект для управления коллекцией
     */
    public ShowCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    /**
     * Метод для выполнения команды show.
     * Выводит все элементы коллекции в строковом представлении.
     */
    @Override
    public void execute() {
        if (manager.getBands().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println("Элементы коллекции:");
            for (MusicBand band : manager.getBands()) {
                System.out.println(band); // Используется метод toString() класса MusicBand
            }
        }
    }
}