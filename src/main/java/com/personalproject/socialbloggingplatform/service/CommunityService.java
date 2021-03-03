package com.personalproject.socialbloggingplatform.service;

import com.personalproject.socialbloggingplatform.dto.CommunityDto;
import com.personalproject.socialbloggingplatform.model.Community;
import com.personalproject.socialbloggingplatform.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<CommunityDto> getAll() {
        return communityRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional
    public CommunityDto save(CommunityDto communityDto) {
        Community community = communityRepository.save(mapToCommunity(communityDto));
        communityDto.setId(community.getCommunityId());
        communityDto.setName("/community/" + community.getCommunityName());
        return communityDto;
    }

    @Transactional(readOnly = true)
    public CommunityDto getCommunity(Long id) {
        Optional<Community> community = communityRepository.findById(id);
        if (community.isPresent())
            return mapToDto(community.get());
        else
           return null;
    }

    private CommunityDto mapToDto(Community community) {
        return CommunityDto.builder().name("/community/" + community.getCommunityName())
                .id(community.getCommunityId())
                .postCount(community.getPosts().size())
                .description(community.getDescription())
                .build();
    }

    private Community mapToCommunity(CommunityDto communityDto) {
        return Community.builder().communityName(communityDto.getName())
                .description(communityDto.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(Instant.now()).build();
    }
}
