package com.anmol420.blog_app.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "Category name must be provided!")
    @Size(min = 2, max = 50, message = "Category must be between {min} and {max} characters!")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Category name must only contain letters and space!")
    private String name;

}
