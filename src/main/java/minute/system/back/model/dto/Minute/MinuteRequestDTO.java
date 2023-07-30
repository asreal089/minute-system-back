package minute.system.back.model.dto.minute;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Validated
public class MinuteRequestDTO {
    @NotEmpty(message = "description is required")
    @Size(min=5)
    private String description;
}