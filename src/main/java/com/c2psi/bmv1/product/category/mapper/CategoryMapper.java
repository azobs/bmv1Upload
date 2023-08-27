package com.c2psi.bmv1.product.category.mapper;

import com.c2psi.bmv1.dto.CategoryDto;
import com.c2psi.bmv1.product.category.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "catParent.id", target = "catParentId")
    @Mapping(source = "catPos.id", target = "catPosId")
    CategoryDto entityToDto(Category category);
    List<CategoryDto> entityToDto(List<Category> category);
    Category dtoToEntity(CategoryDto categoryDto);
    List<Category> dtoToEntity(List<CategoryDto> categoryDto);
}
