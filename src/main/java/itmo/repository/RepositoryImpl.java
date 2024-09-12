package itmo.repository;

import itmo.model.MusicBand;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RepositoryImpl implements Repository {

    private final LocalDateTime initializationTime;
    private final Set<MusicBand> storage = new TreeSet<>(Comparator.comparingInt(o -> Math.toIntExact(o.getId())));

    public RepositoryImpl() {
        this.initializationTime = LocalDateTime.now();
    }

    @Override
    public String info() {
        return "Type - TreeSet<MusicBand>. \n" +
                "Date of initialization - " + initializationTime + ".\n" +
                "Collection has " + storage.size() + " elements.";
    }

    @Override
    public List<MusicBand> returnAllElementsAsList() {
        return new ArrayList<>(storage);
    }

    @Override
    public void add(MusicBand musicBand) {
        storage.add(musicBand);
    }

    @Override
    public void update(MusicBand newElement) {
        MusicBand oldElement = storage.stream().filter(band -> band.getId() == newElement.getId()).findFirst().orElse(null);
        storage.remove(oldElement);
        storage.add(newElement);
    }

    @Override
    public void deleteElementById(long id) {
        MusicBand oldElement = storage.stream().filter(band -> band.getId() == id).findFirst().orElse(null);
        storage.remove(oldElement);
    }

    @Override
    public void clearCollection() {
        storage.clear();
    }

    @Override
    public void removeElementsWhichParticipantsIsLower(long participants) {
        storage.stream()
                .filter(musicBand -> musicBand.getNumberOfParticipants() < participants)
                .forEach(storage::remove);
    }
}
