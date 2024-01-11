package com.backserver.backserver.admin.controller;

import com.backserver.backserver.admin.Dto.VoteDTO;
import com.backserver.backserver.admin.Dto.VoteRequestDTO;
import com.backserver.backserver.admin.service.BadRequestException;
import com.backserver.backserver.admin.service.NotFoundException;
import com.backserver.backserver.admin.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @Operation(summary = "Create or update a vote", description = "Creates a new vote or updates an existing one based on the provided VoteDTO.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created or updated the vote", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            })
    @PostMapping
    public ResponseEntity<VoteDTO> createOrUpdateVote(@Parameter(description = "We will provide the VoteId and the OptionId", required = true) @RequestBody VoteDTO voteDTO) {
        VoteDTO createdVote = voteService.createVote(voteDTO);
        return new ResponseEntity<>(createdVote, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all votes", description = "Retrieves a list of all votes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all votes", content = @Content(mediaType = "application/json"))
            })
    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotes() {
        List<VoteDTO> allVotes = voteService.getAllVotes();
        return ResponseEntity.ok(allVotes);
    }

    @Operation(summary = "Vote for an option", description = "Allows voting for a specific option.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully voted for an option"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters"),
                    @ApiResponse(responseCode = "404", description = "Vote or option not found")
            })
    @PostMapping("/vote")
    public ResponseEntity<?> voteForOption(@Parameter(description = "the option ID and the vote ID", required = true) @RequestBody VoteRequestDTO voteRequestDTO) {
        Long voteId = voteRequestDTO.getVoteId();
        Long optionId = voteRequestDTO.getOptionId();

        if (voteId != null && optionId != null) {
            try {
                voteService.voteForOption(voteId, optionId);
                return ResponseEntity.ok().build();
            } catch (BadRequestException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
