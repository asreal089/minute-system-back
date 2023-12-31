package minute.system.back.service;

import minute.system.back.model.dto.minute.MinuteRequestDTO;
import minute.system.back.model.dto.minute.MinuteResponseDTO;
import minute.system.back.model.dto.minute.ResultResponseDTO;

public interface MinuteService {

    public MinuteResponseDTO create(MinuteRequestDTO minuteRequestDTO);

    public MinuteResponseDTO get(Long id);

    public ResultResponseDTO getResult(Long id);
    
}
