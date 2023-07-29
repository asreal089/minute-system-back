package minute.system.back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private T data;
    private List<ApiError> errors;
}
