package com.c2psi.bmv1.product.product.mapper;

import com.c2psi.bmv1.dto.ProductDto;
import com.c2psi.bmv1.product.product.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "prodCat.id", target = "prodCatId")
    ProductDto entityToDto(Product product);
    List<ProductDto> entityToDto(List<Product> product);
    Product dtoToEntity(ProductDto productDto);
    List<Product> dtoToEntity(List<ProductDto> productDto);
}
