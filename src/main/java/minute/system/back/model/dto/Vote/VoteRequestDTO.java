package minute.system.back.model.dto.vote;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Valid
public class VoteRequestDTO {

    @NotNull(message = "vote is required")
    private Boolean vote;

    @NotNull(message = "UserId is required")
    private Long userId;
    
}
