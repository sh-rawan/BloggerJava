package com.agrawals.blogger.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "Posts title should have atleast 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Posts title should have atleast 10 characters")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
