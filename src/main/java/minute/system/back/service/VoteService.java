package minute.system.back.service;

import minute.system.back.model.dto.vote.VoteResponseDTO;
import minute.system.back.model.dto.vote.VoteRquestDTO;

public interface VoteService {
    
    public VoteResponseDTO vote(VoteRquestDTO voteRquestDTO);
    
}
