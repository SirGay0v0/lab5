package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class MusicBand implements Comparable<MusicBand> {
    private Long id; // Поле не может быть null, должно быть уникальным и генерироваться автоматически
    private String name; // Поле не может быть null и не может быть пустым
    private Coordinates coordinates; // Поле не может быть null
    private final LocalDateTime creationDate; // Поле не может быть null, генерируется автоматически
    private Integer numberOfParticipants; // Может быть null, значение должно быть больше 0
    private long singlesCount; // Значение должно быть больше 0
    private MusicGenre genre; // Поле не может быть null
    private Studio studio; // Поле не может быть null

    // Конструктор класса MusicBand
    public MusicBand(Long id, String name, Coordinates coordinates, LocalDateTime creationDate,
                     Integer numberOfParticipants, long singlesCount, MusicGenre genre, Studio studio) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.genre = genre;
        this.studio = studio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public long getSinglesCount() {
        return singlesCount;
    }

    public void setSinglesCount(long singlesCount) {
        this.singlesCount = singlesCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @Override
    public int compareTo(MusicBand other) {
        return this.creationDate.compareTo(other.creationDate); // Сортировка по дате создания
    }

    @Override
    public String toString() {
        return "MusicBand{id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singlesCount=" + singlesCount +
                ", genre=" + genre +
                ", studio=" + studio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return Objects.equals(id, musicBand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}