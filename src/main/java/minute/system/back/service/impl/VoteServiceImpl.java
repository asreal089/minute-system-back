package minute.system.back.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import minute.system.back.model.dto.error.exception.ApiBackEndException;
import minute.system.back.model.dto.error.exception.ApiBadRequestException;
import minute.system.back.model.dto.vote.VoteRequestDTO;
import minute.system.back.model.dto.vote.VoteResponseDTO;
import minute.system.back.model.entity.Minute;
import minute.system.back.model.entity.Vote;
import minute.system.back.repository.MinuteRepository;
import minute.system.back.repository.VoteRepository;
import minute.system.back.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MinuteRepository minuteRepository;

    @Override
    public VoteResponseDTO vote(VoteRequestDTO voteRequestDTO, Long idMinute) throws RuntimeException {
        Minute minute = minuteRepository.findById(idMinute);
        if (minute == null) {
            throw new ApiBadRequestException("Minute not found exception");
        }

        LocalDateTime now = LocalDateTime.now();

        if (minute.getEndAt().isBefore(now)) {
            throw new ApiBadRequestException("Minute is closed");
        }

        Vote vote = Vote.builder()
                .minute(minute)
                .createdAt(now)
                .userId(voteRequestDTO.getUserId())
                .vote(voteRequestDTO.getVote())
                .build();
        try {
            Vote savedVote = voteRepository.save(vote);

            return VoteResponseDTO.builder()
                    .id(savedVote.getId().toString())
                    .description(savedVote.getMinute().getDescription())
                    .time(savedVote.getMinute().getEndAt().toString())
                    .vote(savedVote.getVote())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ApiBadRequestException("User already voted");
        } catch (Exception e){
            throw new ApiBackEndException("Error while saving vote: {}" + e.getMessage());
        }

    }

}
