package com.personalproject.socialbloggingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private Long postId;
    private String postName;
    private String url;
    private String description;
    private String communityName;
    private String userName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
}
