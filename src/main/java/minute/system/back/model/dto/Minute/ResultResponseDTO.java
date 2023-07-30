package minute.system.back.model.dto.minute;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultResponseDTO {
    private Long minuteId;
    private String description;
    private String createAt;
    private String endAt;
    private Integer total;
    private Integer yes;
    private Integer no;
}
