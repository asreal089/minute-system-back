package minute.system.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.Minute.MinuteRequestDTO;
import minute.system.back.model.dto.Minute.MinuteResponseDTO;
import minute.system.back.service.impl.MinuteServiceImpl;


@RestController
@RequestMapping("/v1/minute")
public class MinuteController {

    @Autowired
    private MinuteServiceImpl minuteService;
    
    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO<MinuteResponseDTO>> createMinute(@RequestBody MinuteRequestDTO minuteRequestDTO) {

        MinuteResponseDTO response =  minuteService.create(minuteRequestDTO);
        
        return ResponseEntity.ok(new ApiResponseDTO<MinuteResponseDTO>(response, null));
    }

    
}
