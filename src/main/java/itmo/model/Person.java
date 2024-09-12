package itmo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Person {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull(message = "Height cannot be null")
    @Min(value = 1, message = "Height must be more then 0")
    private Integer height; //Поле не может быть null, Значение поля должно быть больше 0
    @NotNull(message = "HairColor cannot be null")
    private Color hairColor; //Поле не может быть null
    @NotNull(message = "Nationality cannot be null")
    private Country nationality; //Поле не может быть null
    @NotNull(message = "Location cannot be null")
    private Location location; //Поле не может быть null
}

