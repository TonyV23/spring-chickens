package com.rungroup.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto {
    private int id;
    @NotEmpty(message = "title should not be empty")
    private String title;
    @NotEmpty(message = "content should not be empty")
    private String content;
    @NotEmpty(message = "photoUrl should not be empty")
    private String photoUrl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
