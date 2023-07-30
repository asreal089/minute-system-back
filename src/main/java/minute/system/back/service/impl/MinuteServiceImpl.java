package minute.system.back.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minute.system.back.model.dto.error.exception.ApiBackEndException;
import minute.system.back.model.dto.error.exception.ApiBadRequestException;
import minute.system.back.model.dto.error.exception.ApiNotFoundException;
import minute.system.back.model.dto.minute.MinuteRequestDTO;
import minute.system.back.model.dto.minute.MinuteResponseDTO;
import minute.system.back.model.dto.minute.ResultResponseDTO;
import minute.system.back.model.entity.Minute;
import minute.system.back.model.entity.MinuteResult;
import minute.system.back.repository.MinuteRepository;
import minute.system.back.service.MinuteService;

@Service
public class MinuteServiceImpl implements MinuteService {

    private static final String VOTING_IS_ONGOING = "Voting is ongoing";
    private static final String ERROR_SAVING_MINUTE = "Error saving minute";
    private static final String MINUTE_NOT_FOUND = "Minute not found";
    private static final int DEFAULT_MINUTE_TIME = 1;


    @Autowired
    private MinuteRepository repository;

    @Override
    public MinuteResponseDTO create(MinuteRequestDTO minuteRequestDTO) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endAt = now
                .plusMinutes(minuteRequestDTO.getDuration() == null ? DEFAULT_MINUTE_TIME : minuteRequestDTO.getDuration());

        Minute minute = Minute.builder()
                .description(minuteRequestDTO.getDescription())
                .createdAt(now)
                .endAt(endAt)
                .build();
        try {
            Minute savedMinute = repository.save(minute);

            return MinuteResponseDTO.builder()
                .id(savedMinute.getId())
                .description(savedMinute.getDescription())
                .createdAt(savedMinute.getCreatedAt().toString())
                .endAt(savedMinute.getEndAt().toString())
                .build();
        } catch (Exception e) {
            throw new ApiBackEndException(ERROR_SAVING_MINUTE, e.getCause());
        }

    }

    @Override
    public MinuteResponseDTO get(Long id) {
        try {
            Minute savedMinute = repository.findById(id);
            return MinuteResponseDTO.builder()
                    .id(savedMinute.getId())
                    .description(savedMinute.getDescription())
                    .createdAt(savedMinute.getCreatedAt().toString())
                    .endAt(savedMinute.getEndAt().toString())
                    .build();
        } catch (Exception e) {
            throw new ApiNotFoundException(MINUTE_NOT_FOUND, e.getCause());
        }
    }

    @Override
    public ResultResponseDTO getResult(Long id) {
        Minute savedMinute =  repository.findById(id);

        if(savedMinute == null){
            throw new ApiNotFoundException(MINUTE_NOT_FOUND, null);
        }

        if(savedMinute.getEndAt().isAfter(LocalDateTime.now())){
            throw new ApiBadRequestException(VOTING_IS_ONGOING, null);
        }
        try {
            MinuteResult result = repository.findResultById(id);
    
            return ResultResponseDTO.builder()
                    .minuteId(savedMinute.getId())
                    .description(savedMinute.getDescription())
                    .createdAt(savedMinute.getCreatedAt().toString())
                    .endAt(savedMinute.getEndAt().toString())
                    .total(result.getTotal())
                    .yes(result.getYes())
                    .no(result.getNo())
                    .build();
        } catch (Exception e) {
            throw new ApiNotFoundException(MINUTE_NOT_FOUND, e.getCause());
        }
    }


}
