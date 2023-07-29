package minute.system.back.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.Minute.MinuteRequestDTO;


@RestController
@RequestMapping("/v1/minute")
public class MinuteController {
    
    @PostMapping("/")
    public ApiResponseDTO<MinuteRequestDTO> createMinute(@RequestBody MinuteRequestDTO minuteRequestDTO) {
        
        return new ApiResponseDTO<>(minuteRequestDTO, null);
    }

    
}
