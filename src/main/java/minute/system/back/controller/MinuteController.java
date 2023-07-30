package minute.system.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.minute.MinuteRequestDTO;
import minute.system.back.model.dto.minute.MinuteResponseDTO;
import minute.system.back.service.impl.MinuteServiceImpl;


@RestController
@RequestMapping("/v1/minute")
public class MinuteController {

    @Autowired
    private MinuteServiceImpl minuteService;
    
    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO<MinuteResponseDTO>> createMinute(@RequestBody @Valid MinuteRequestDTO minuteRequestDTO) {

        MinuteResponseDTO response =  minuteService.create(minuteRequestDTO);
        
        return ResponseEntity.ok(new ApiResponseDTO<MinuteResponseDTO>(response, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<MinuteResponseDTO>> getMinute(@PathVariable("id") Long id) {

        MinuteResponseDTO response =  minuteService.get(id);
        
        return ResponseEntity.ok(new ApiResponseDTO<MinuteResponseDTO>(response, null));
    }

    
}
