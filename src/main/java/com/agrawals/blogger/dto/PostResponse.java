package com.agrawals.blogger.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {
    private List<PostDto> posts;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean lastPage;
}
