package com.ejada.product.service.repository.facade;

import com.ejada.product.service.model.entity.Product;
import com.ejada.product.service.model.filter.ProductFilter;
import com.ejada.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ejada.product.service.exception.CommonExceptionHandler.handleInternalServerErrorException;
import static com.ejada.product.service.util.Constants.DATABASE_GENERAL_ERROR_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductRepositoryFacade {

    private final ProductRepository productRepository;

    public Page<Product> findAllByCategoryAndPriceRange(ProductFilter productFilter, Pageable pageable) {
        log.info("Find all products by category and price range: [{}]", productFilter.toString());
        try {
            return productRepository.findAllByCategoryAndPriceRange(productFilter, pageable);
        } catch (Exception e) {
            log.error("Error occurred while finding products by category and price range: [{}]", e.getMessage());
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

    public void save(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error occurred while saving product ProductRepositoryFacade: [{}]", product.toString());
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }

    }

    public Optional<Product> findByName(String name) {
        log.info("Find product by name ProductRepositoryFacade: [{}]", name);
        try {
            return productRepository.findByName(name);
        } catch (Exception e) {
            log.error("Error occurred while finding product by name ProductRepositoryFacade: [{}]", name);
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

    public List<Product> findAllById(List<Integer> ids) {
        log.info("Find all products by certain ids ProductRepositoryFacade: [{}]", ids);
        try {
            return productRepository.findAllByIdExcludingDeleted(ids);
        } catch (Exception e) {
            log.error("Error occurred while finding products by certain ids ProductRepositoryFacade: [{}]", ids);
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

    public Optional<Product> findProductById(int id) {
        log.info("Find product by certain id ProductRepositoryFacade: [{}]", id);
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            log.error("Error occurred while finding product by id ProductRepositoryFacade: [{}]", e.getMessage());
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

    public void updateProduct(Product product) {
        log.info("update product ProductRepositoryFacade: [{}]", product.getId());
        try {
            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error occurred while updating product with id [{}] ProductRepositoryFacade: [{}]", product.getId(), e.getMessage());
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

}
