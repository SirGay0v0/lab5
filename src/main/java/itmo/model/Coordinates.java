package itmo.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Coordinates {
    //    @NotNull(message = "x cannot be null")
    private Float x; //Поле не может быть null
    //    @NotNull(message = "y cannot be null")
//    @Min(value = -489, message = "y must be more then -490")
    private Long y; //Значение поля должно быть больше -490, Поле не может быть null


}
