package command;

import manager.MusicBandManager;
import model.MusicBand;
import model.Person;

import java.util.HashSet;
import java.util.Set;
/**
 * Класс для команды вывода уникальных значений поля frontMan всех элементов коллекции.
 */
public class PrintUniqueFrontManCommand implements Command {
    private MusicBandManager manager;

    public PrintUniqueFrontManCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        if (manager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            Set<Person> uniqueFrontMen = new HashSet<>();
            for (MusicBand band : manager.getCollection()) {
                uniqueFrontMen.add(band.getFrontMan());  
            }

            if (uniqueFrontMen.isEmpty()) {
                System.out.println("Уникальных фронтменов нет.");
            } else {
                System.out.println("Уникальные фронтмены:");
                for (Person frontMan : uniqueFrontMen) {
                    System.out.println(frontMan);
                }
            }
        }
    }
}