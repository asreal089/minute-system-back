package minute.system.back.service;

import minute.system.back.model.dto.Vote.VoteResponseDTO;
import minute.system.back.model.dto.Vote.VoteRquestDTO;

public interface VoteService {
    
    public VoteResponseDTO vote(VoteRquestDTO voteRquestDTO);
    
}
