package com.personalproject.socialbloggingplatform.controller;

import com.personalproject.socialbloggingplatform.dto.CommunityDto;
import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/community")
@AllArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping
    public List<CommunityDto> getAllCommunities() {
        return communityService.getAll();
    }

    @GetMapping("/{id}")
    public CommunityDto getCommunity(@PathVariable Long id) {
        CommunityDto communityDto = communityService.getCommunity(id);
        if (communityDto == null)
            throw new RecordNotFoundException("Community not found with id: " + id);
        return communityDto;
    }

    @PostMapping
    public CommunityDto create(@RequestBody @Valid CommunityDto communityDto) {
        return communityService.save(communityDto);
    }
}
