package minute.system.back.service;

import javax.naming.spi.DirStateFactory.Result;

import minute.system.back.model.dto.Minute.MinuteRequestDTO;
import minute.system.back.model.dto.Minute.MinuteResponseDTO;
import minute.system.back.model.dto.Minute.ResultResponseDTO;
import minute.system.back.model.entity.Minute;

public interface MinuteService {

    public MinuteResponseDTO create(MinuteRequestDTO minuteRequestDTO);

    public ResultResponseDTO getResult(Integer qtd);
    
}
