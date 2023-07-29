package minute.system.back.model.dto.Minute;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MinuteResponseDTO {
    private Long id;
    private String description;
    private String createdAt;
    private String endAt;
}
