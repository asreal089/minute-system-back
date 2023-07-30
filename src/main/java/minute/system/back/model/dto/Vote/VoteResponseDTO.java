package minute.system.back.model.dto.vote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteResponseDTO {
    String id;
    String description;
    String time;
    Boolean vote;
}
