package com.anmol420.blog_app.mappers;

import com.anmol420.blog_app.domain.PostStatus;
import com.anmol420.blog_app.domain.dtos.CategoryDTO;
import com.anmol420.blog_app.domain.dtos.CreateCategoryRequest;
import com.anmol420.blog_app.domain.entities.Category;
import com.anmol420.blog_app.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDTO toDTO(Category category);

    Category fromDTO(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (null == posts) {
            return 0;
        }
        return posts
                .stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
