package itmo.repository;

import itmo.model.MusicBand;

import java.util.List;

public interface Repository {

    String info();

    List<MusicBand> returnAllElementsAsList();

    void add(MusicBand musicBand);

    void update(MusicBand newElement);

    void deleteElementById(long id);

    void clearCollection();

    void removeElementsWhichParticipantsIsLower(long participants);

}
