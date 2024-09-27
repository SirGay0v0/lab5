package command;

import manager.MusicBandManager;
import model.MusicBand;

/**
 * Команда для вывода элемента коллекции с максимальным значением поля coordinates.
 */
public class MaxByCoordinatesCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Конструктор команды max_by_coordinates.
     *
     * @param manager объект для управления коллекцией
     */
    public MaxByCoordinatesCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    /**
     * Метод для выполнения команды max_by_coordinates.
     * Выводит элемент с максимальным значением поля coordinates.
     */
    @Override
    public void execute() {
        if (manager.getBands().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            MusicBand maxBand = manager.getBandWithMaxCoordinates();
            System.out.println("Элемент с максимальными координатами: " + maxBand);
        }
    }
}
