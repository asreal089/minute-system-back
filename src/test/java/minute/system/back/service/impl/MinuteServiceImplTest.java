package minute.system.back.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import minute.system.back.model.dto.minute.MinuteRequestDTO;
import minute.system.back.model.dto.minute.MinuteResponseDTO;
import minute.system.back.model.dto.minute.ResultResponseDTO;
import minute.system.back.model.entity.Minute;
import minute.system.back.model.entity.MinuteResult;
import minute.system.back.repository.MinuteRepository;

public class MinuteServiceImplTest {

    @InjectMocks
    private MinuteServiceImpl service;

    @Mock
    private MinuteRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        MinuteRequestDTO request = new MinuteRequestDTO();
        request.setDescription("Test minute");
        request.setDuration(10);

        Minute minute = new Minute();
        minute.setId(1L);
        minute.setDescription(request.getDescription());
        minute.setCreatedAt(LocalDateTime.now());
        minute.setEndAt(LocalDateTime.now().plusMinutes(request.getDuration()));

        when(repository.save(any(Minute.class))).thenReturn(minute);

        MinuteResponseDTO response = service.create(request);

        assertNotNull(response);
        assertEquals(minute.getId(), response.getId());
        assertEquals(minute.getDescription(), response.getDescription());
    }

    @Test
    public void testGet() {
        Long id = 1L;
        Minute minute = new Minute();
        minute.setId(id);
        minute.setDescription("Test minute");
        minute.setCreatedAt(LocalDateTime.now());
        minute.setEndAt(LocalDateTime.now().plusMinutes(10));

        when(repository.findById(id)).thenReturn(minute);

        MinuteResponseDTO response = service.get(id);

        assertNotNull(response);
        assertEquals(minute.getId(), response.getId());
        assertEquals(minute.getDescription(), response.getDescription());
    }

    @Test
    public void testGetResult() {
        Long id = 1L;
        Minute minute = new Minute();
        minute.setId(id);
        minute.setDescription("Test minute");
        minute.setCreatedAt(LocalDateTime.now().minusHours(2));
        minute.setEndAt(LocalDateTime.now().minusHours(1));

        MinuteResult result = Mockito.mock(MinuteResult.class);
        when(result.getYes()).thenReturn(1);
        when(result.getNo()).thenReturn(1);
        when(repository.findById(id)).thenReturn(minute);
        when(repository.findResultById(id)).thenReturn(result);

        ResultResponseDTO response = service.getResult(1l);

        assert(response.getYes() == 1);
        assert(response.getNo() == 1);


    }

}

