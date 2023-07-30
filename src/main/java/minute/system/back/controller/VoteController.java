package minute.system.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.vote.VoteRequestDTO;
import minute.system.back.model.dto.vote.VoteResponseDTO;
import minute.system.back.service.impl.VoteServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/vote")
public class VoteController {

    @Autowired
    private VoteServiceImpl voteServiceImpl;

    @PostMapping("/{idMinute}")
    public ResponseEntity<ApiResponseDTO<VoteResponseDTO>> postMethodName(@PathVariable("idMinute") Long idMinute, @RequestBody @Valid VoteRequestDTO request) {
        VoteResponseDTO response = voteServiceImpl.vote(request, idMinute);
        return ResponseEntity.ok(new ApiResponseDTO<VoteResponseDTO>(response, null));
    }

}
