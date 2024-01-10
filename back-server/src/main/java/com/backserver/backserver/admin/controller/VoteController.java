package com.backserver.backserver.admin.controller;

import com.backserver.backserver.admin.Dto.VoteDTO;
import com.backserver.backserver.admin.Dto.VoteRequestDTO;
import com.backserver.backserver.admin.service.BadRequestException;
import com.backserver.backserver.admin.service.NotFoundException;
import com.backserver.backserver.admin.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "VoteController", description = "Controller for managing votes {this is simple version without homomorphic encryption}")
@RestController
@RequestMapping("/api/votes")
public class VoteController {
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @ApiOperation(value = "Create or update a vote", response = VoteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created or updated the vote"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<VoteDTO> createOrUpdateVote(@RequestBody VoteDTO voteDTO) {
        VoteDTO createdVote = voteService.createVote(voteDTO);
        return new ResponseEntity<>(createdVote, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all votes", response = VoteDTO.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotes() {
        List<VoteDTO> allVotes = voteService.getAllVotes();
        return ResponseEntity.ok(allVotes);
    }

    @ApiOperation(value = "Vote for an option")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully voted for an option"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 404, message = "Vote or option not found")
    })
    @PostMapping("/vote")
    public ResponseEntity<?> voteForOption(@RequestBody VoteRequestDTO voteRequestDTO) {
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
