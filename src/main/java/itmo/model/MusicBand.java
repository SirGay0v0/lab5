package itmo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@ToString
public class MusicBand implements Comparable<MusicBand> {

    private static long idCounter = 0;

    @EqualsAndHashCode.Include
    private final long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull(message = "Coordinates cannot be null")
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Min(value = 1, message = "numberOfParticipants must be more then 0")
    private long numberOfParticipants; //Значение поля должно быть больше 0
    @NotNull(message = "Genre cannot be null")
    private MusicGenre genre; //Поле может быть null
    @NotNull(message = "FrontMan cannot be null")
    private Person frontMan; //Поле не может быть null

    public MusicBand(String name, Coordinates coordinates, long numberOfParticipants, MusicGenre genre, Person frontMan) {
        this.id = idCounter++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public MusicBand() {
        this.creationDate = LocalDate.now();
        this.id = idCounter++;
    }

    @Override
    public int compareTo(MusicBand o) {
        return Long.compare(this.id, o.id);
    }
}
