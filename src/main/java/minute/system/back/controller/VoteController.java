package minute.system.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.vote.VoteRequestDTO;
import minute.system.back.model.dto.vote.VoteResponseDTO;
import minute.system.back.service.impl.VoteServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Vote", description = "api to vote in a minute")
@RestController
@RequestMapping("/v1/minute")
public class VoteController {

    @Autowired
    private VoteServiceImpl voteServiceImpl;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created"), 
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/{idMinute}/vote")
    public ResponseEntity<ApiResponseDTO<VoteResponseDTO>> postMethodName(@PathVariable("idMinute") Long idMinute, @RequestBody @Valid VoteRequestDTO request) {
        VoteResponseDTO response = voteServiceImpl.vote(request, idMinute);
        return ResponseEntity.ok(new ApiResponseDTO<VoteResponseDTO>(response, null));
    }

}
