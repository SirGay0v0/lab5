package itmo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Location {
    private float x;
    @NotNull(message = "y cannot be null")
    private Float y; //Поле не может быть null
    @NotNull(message = "z cannot be null")
    private Float z; //Поле не может быть null
    @NotNull(message = "Name cannot be null")
    private String name; //Поле не может быть null
}
