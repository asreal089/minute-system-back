package minute.system.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.minute.MinuteRequestDTO;
import minute.system.back.model.dto.minute.MinuteResponseDTO;
import minute.system.back.model.dto.minute.ResultResponseDTO;
import minute.system.back.service.impl.MinuteServiceImpl;

@Tag(name = "Minute", description = "api to create and get minute, and get minute result")
@RestController
@RequestMapping("/v1/minute")
public class MinuteController {

    @Autowired
    private MinuteServiceImpl minuteService;
    
    @Operation(summary = "Create a new Minute to be voted", description = "Returns the created Minute")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created"), 
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO<MinuteResponseDTO>> createMinute(@RequestBody @Valid MinuteRequestDTO minuteRequestDTO) {

        MinuteResponseDTO response =  minuteService.create(minuteRequestDTO);
        
        return ResponseEntity.ok(new ApiResponseDTO<MinuteResponseDTO>(response, null));
    }

    @Operation(summary = "Get minute by id", description = "Returns the Minute")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<MinuteResponseDTO>> getMinute(@PathVariable("id") Long id) {

        MinuteResponseDTO response =  minuteService.get(id);
        
        return ResponseEntity.ok(new ApiResponseDTO<MinuteResponseDTO>(response, null));
    }

    @Operation(summary = "Get results of a minute", description = "Returns the Minute results")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping("/{id}/result")
    public ResponseEntity<ApiResponseDTO<ResultResponseDTO>> getMinuteResults(@PathVariable("id") Long id) {

        ResultResponseDTO response =  minuteService.getResult(id);
        
        return ResponseEntity.ok(new ApiResponseDTO<ResultResponseDTO>(response, null));
    }
    
}
