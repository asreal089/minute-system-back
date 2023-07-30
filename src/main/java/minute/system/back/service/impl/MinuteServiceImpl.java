package minute.system.back.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minute.system.back.model.dto.error.exception.ApiNotFoundException;
import minute.system.back.model.dto.minute.MinuteRequestDTO;
import minute.system.back.model.dto.minute.MinuteResponseDTO;
import minute.system.back.model.dto.minute.ResultResponseDTO;
import minute.system.back.model.entity.Minute;
import minute.system.back.repository.MinuteRepository;
import minute.system.back.service.MinuteService;

@Service
public class MinuteServiceImpl implements MinuteService {

    @Autowired
    private MinuteRepository repository;

    @Override
    public MinuteResponseDTO create(MinuteRequestDTO minuteRequestDTO) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endAt = now
                .plusMinutes(minuteRequestDTO.getDuration() == null ? 1 : minuteRequestDTO.getDuration());

        Minute minute = Minute.builder()
                .description(minuteRequestDTO.getDescription())
                .createdAt(now)
                .endAt(endAt)
                .build();

        Minute savedMinute = repository.save(minute);

        return MinuteResponseDTO.builder()
                .id(savedMinute.getId())
                .description(savedMinute.getDescription())
                .createdAt(savedMinute.getCreatedAt().toString())
                .endAt(savedMinute.getEndAt().toString())
                .build();
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
            throw new ApiNotFoundException("Minute not found", e.getCause());
        }
    }

    @Override
    public ResultResponseDTO getResult(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }


}
