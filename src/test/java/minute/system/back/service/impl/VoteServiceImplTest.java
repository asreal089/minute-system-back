package minute.system.back.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import minute.system.back.model.dto.error.exception.ApiBadRequestException;
import minute.system.back.model.dto.vote.VoteRequestDTO;
import minute.system.back.model.dto.vote.VoteResponseDTO;
import minute.system.back.model.entity.Minute;
import minute.system.back.model.entity.Vote;
import minute.system.back.repository.MinuteRepository;
import minute.system.back.repository.VoteRepository;

public class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl service;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private MinuteRepository minuteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVote_success() {
        Long idMinute = 1L;

        Minute minute = new Minute();
        minute.setId(idMinute);
        minute.setDescription("Test minute");
        minute.setEndAt(LocalDateTime.now().plusHours(1));

        when(minuteRepository.findById(idMinute)).thenReturn(minute);

        VoteRequestDTO request = new VoteRequestDTO();
        request.setUserId(1L);
        request.setVote(true);

        Vote vote = new Vote();
        vote.setId(1L);
        vote.setMinute(minute);
        vote.setCreatedAt(LocalDateTime.now());
        vote.setUserId(request.getUserId());
        vote.setVote(request.getVote());

        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        VoteResponseDTO response = service.vote(request, idMinute);

        assertNotNull(response);
        assertEquals(vote.getId().toString(), response.getId());
        assertEquals(vote.getVote(), response.getVote());
    }

    @Test
    public void testVote_minuteNotFound() {
        Long idMinute = 1L;
        when(minuteRepository.findById(idMinute)).thenReturn(null);

        VoteRequestDTO request = new VoteRequestDTO();
        request.setUserId(1L);
        request.setVote(true);

        Exception exception = assertThrows(ApiBadRequestException.class, () -> {
            service.vote(request, idMinute);
        });

        assertEquals("Minute not found exception", exception.getMessage());
    }

    @Test
    public void testVote_minuteClosed() {
        Long idMinute = 1L;

        Minute minute = new Minute();
        minute.setId(idMinute);
        minute.setDescription("Test minute");
        minute.setEndAt(LocalDateTime.now().minusMinutes(10));

        when(minuteRepository.findById(idMinute)).thenReturn(minute);

        VoteRequestDTO request = new VoteRequestDTO();
        request.setUserId(1L);
        request.setVote(true);

        Exception exception = assertThrows(ApiBadRequestException.class, () -> {
            service.vote(request, idMinute);
        });

        assertEquals("Minute is closed", exception.getMessage());
    }

    @Test
    public void testVote_userAlreadyVoted() {
        Long idMinute = 1L;

        Minute minute = new Minute();
        minute.setId(idMinute);
        minute.setDescription("Test minute");
        minute.setEndAt(LocalDateTime.now().plusHours(1));

        when(minuteRepository.findById(idMinute)).thenReturn(minute);

        VoteRequestDTO request = new VoteRequestDTO();
        request.setUserId(1L);
        request.setVote(true);

        when(voteRepository.save(any(Vote.class))).thenThrow(DataIntegrityViolationException.class);

        Exception exception = assertThrows(ApiBadRequestException.class, () -> {
            service.vote(request, idMinute);
        });

        assertEquals("User already voted", exception.getMessage());
    }

}

