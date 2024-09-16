package model;

import java.time.LocalDate;

public class MusicBand implements Comparable<MusicBand> {
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private long numberOfParticipants;
    private MusicGenre genre;
    private Person frontMan;

    public MusicBand(long id, String name, Coordinates coordinates, LocalDate creationDate, long numberOfParticipants, MusicGenre genre, Person frontMan) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    @Override
    public int compareTo(MusicBand other) {
        return Long.compare(this.numberOfParticipants, other.numberOfParticipants);
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", genre=" + genre +
                ", frontMan=" + frontMan +
                '}';
    }
}
