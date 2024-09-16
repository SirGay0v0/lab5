package command;

import manager.MusicBandManager;
import model.MusicBand;
import model.MusicGenre;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
/**
 * Класс для команды вывода значений поля жанра (genre) всех элементов в порядке убывания.
 */
public class PrintFieldDescendingGenreCommand implements Command {
    private MusicBandManager manager;

    public PrintFieldDescendingGenreCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        if (manager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            Set<MusicGenre> genres = new TreeSet<>(Comparator.reverseOrder());

            for (MusicBand band : manager.getCollection()) {
                if (band.getGenre() != null) {
                    genres.add(band.getGenre());
                }
            }

            if (genres.isEmpty()) {
                System.out.println("Ни у одного элемента нет значения поля genre.");
            } else {
                System.out.println("Жанры в порядке убывания:");
                for (MusicGenre genre : genres) {
                    System.out.println(genre);
                }
            }
        }
    }
}
