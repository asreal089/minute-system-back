package minute.system.back.service;

import minute.system.back.model.dto.vote.VoteRequestDTO;
import minute.system.back.model.dto.vote.VoteResponseDTO;

public interface VoteService {
    
    public VoteResponseDTO vote(VoteRequestDTO voteRequestDTO, Long idMinute);
    
}
