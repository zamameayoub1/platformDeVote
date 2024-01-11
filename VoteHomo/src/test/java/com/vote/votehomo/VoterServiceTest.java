package com.vote.votehomo;

import com.vote.votehomo.service.VoterService;
import org.junit.jupiter.api.Test;
import com.vote.votehomo.dto.VoterDTO;
import com.vote.votehomo.model.Voter;
import com.vote.votehomo.repository.VoterRepository;
import com.vote.votehomo.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VoterServiceTest {

    @Mock
    private VoterRepository voterRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private VoterService voterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterVoter() {
        // Arrange
        VoterDTO voterDTO = mock(VoterDTO.class);
        Voter voter = mock(Voter.class);

        when(mapper.voterDTOToVoter(voterDTO)).thenReturn(voter);
        when(voterRepository.save(voter)).thenReturn(voter);
        when(mapper.voterToVoterDTO(voter)).thenReturn(voterDTO);

        // Act
        VoterDTO result = voterService.registerVoter(voterDTO);

        // Assert
        verify(mapper).voterDTOToVoter(voterDTO);
        verify(voterRepository).save(voter);
        verify(mapper).voterToVoterDTO(voter);
        assertEquals(voterDTO, result);
    }

    @Test
    public void testGetVoter() {
        // Arrange
        Long id = 1L;
        VoterDTO voterDTO = mock(VoterDTO.class);
        Voter voter = mock(Voter.class);

        when(voterRepository.findById(id)).thenReturn(Optional.of(voter));
        when(mapper.voterToVoterDTO(voter)).thenReturn(voterDTO);

        // Act
        VoterDTO result = voterService.getVoter(id);

        // Assert
        verify(voterRepository).findById(id);
        verify(mapper).voterToVoterDTO(voter);
        assertEquals(voterDTO, result);
    }

    @Test
    public void testGetVoter_NotFound() {
        // Arrange
        Long id = 1L;
        when(voterRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            voterService.getVoter(id);
        });

        assertEquals("Voter not found", exception.getMessage());
    }

    @Test
    public void testUpdateVoter() {
        // Arrange
        VoterDTO voterDTO = mock(VoterDTO.class);
        Voter voter = mock(Voter.class);

        when(mapper.voterDTOToVoter(voterDTO)).thenReturn(voter);
        when(voterRepository.save(voter)).thenReturn(voter);
        when(mapper.voterToVoterDTO(voter)).thenReturn(voterDTO);

        // Act
        VoterDTO result = voterService.updateVoter(voterDTO);

        // Assert
        verify(mapper).voterDTOToVoter(voterDTO);
        verify(voterRepository).save(voter);
        verify(mapper).voterToVoterDTO(voter);
        assertEquals(voterDTO, result);
    }
}