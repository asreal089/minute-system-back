package minute.system.back.model.dto.minute;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public class MinuteRequestDTO {
    @NotEmpty(message = "description is required")
    @Size(min=5)
    private String description;

    private Integer duration;
}