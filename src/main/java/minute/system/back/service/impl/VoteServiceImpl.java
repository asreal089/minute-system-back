package minute.system.back.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import minute.system.back.model.dto.error.ApiException;
import minute.system.back.model.dto.vote.VoteRequestDTO;
import minute.system.back.model.dto.vote.VoteResponseDTO;
import minute.system.back.model.entity.Minute;
import minute.system.back.repository.MinuteRepository;
import minute.system.back.repository.VoteRepository;
import minute.system.back.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MinuteRepository minuteRepository;

    @Override
    public VoteResponseDTO vote(VoteRequestDTO voteRequestDTO, Long idMinute) throws RuntimeException {
        Minute minute = minuteRepository.findById(idMinute);
        if(minute == null){
            throw new ApiException("Minute not found exception");
        }
        
        LocalDateTime now = LocalDateTime.now();

        if(minute.getEndAt().isBefore(now)){
            throw new ApiException("Minute is closed");
        }
        return null;
    }
    
}
