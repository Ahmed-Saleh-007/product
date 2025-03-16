package com.ejada.product.service.controller;

import com.ejada.product.service.model.filter.ProductFilter;
import com.ejada.product.service.model.request.CreateProductRequest;
import com.ejada.product.service.model.response.CreateProductResponse;
import com.ejada.product.service.model.response.ProductWithPagingResponse;
import com.ejada.product.service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.ejada.product.service.util.Constants.PRODUCT_SOFT_DELETED_SUCCESSFULLY;

@Controller
@RequiredArgsConstructor
public class ProductGraphQLController {

    private final ProductService productService;

    @QueryMapping
    public ProductWithPagingResponse getProducts(
            @Argument List<Long> categoryIds,
            @Argument Double minPrice,
            @Argument Double maxPrice,
            @Argument Boolean isInStock,
            @Argument int pageIndex,
            @Argument int pageSize,
            @Argument String sortOrder,
            @Argument String sortField) {

        ProductFilter productFilter = ProductFilter.builder()
                .categoryIds(categoryIds)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .isInStock(isInStock != null ? isInStock : false)
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .sortOrder(sortOrder)
                .sortField(sortField)
                .build();

        return productService.getProducts(productFilter);
    }

    @MutationMapping
    public CreateProductResponse createProduct(@Argument @Valid CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @MutationMapping
    public String softDelete(@Argument int productId) {
        productService.softDeleteProduct(productId);
        return PRODUCT_SOFT_DELETED_SUCCESSFULLY;
    }

}
