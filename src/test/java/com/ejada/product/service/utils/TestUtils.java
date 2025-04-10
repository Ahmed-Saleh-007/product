package com.ejada.product.service.utils;

import com.ejada.product.service.event.message.LowStockMessage;
import com.ejada.product.service.model.entity.Promotion;
import com.ejada.product.service.model.enums.DiscountTypeEnum;
import com.ejada.product.service.model.filter.OrderFilter;
import com.ejada.product.service.model.request.CreateOrderRequest;
import com.ejada.product.service.model.request.CreateProductRequest;
import com.ejada.product.service.model.request.OrderProductRequest;
import com.ejada.product.service.model.entity.Customer;
import com.ejada.product.service.model.entity.Product;
import com.ejada.product.service.model.response.CreateOrderResponse;

import java.math.BigDecimal;
import java.util.List;

import static com.ejada.product.service.utils.TestConstants.CUSTOMER_ID;
import static com.ejada.product.service.utils.TestConstants.CUSTOMER_NAME;
import static com.ejada.product.service.utils.TestConstants.PRODUCT_ID;
import static com.ejada.product.service.utils.TestConstants.PRODUCT_NAME;
import static com.ejada.product.service.utils.TestConstants.PRODUCT_PRICE;
import static com.ejada.product.service.utils.TestConstants.PRODUCT_STOCK_QUANTITY;

public class TestUtils {
    public static Product buildProduct()
    {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setStockQuantity(PRODUCT_STOCK_QUANTITY);
        product.setPrice(PRODUCT_PRICE);
        return product;
    }

    public static Customer buildCustomer()
    {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setName(CUSTOMER_NAME);
        return customer;
    }

    public static Promotion buildPrecentagePromotion() {
        return Promotion.builder()
                .id(1)
                .code("PROMO2025")
                .discountType(DiscountTypeEnum.PERCENTAGE)
                .discountValue(BigDecimal.valueOf(10.00))
                .build();
    }

    public static Promotion buildAmountPromotion() {
        return Promotion.builder()
                .id(2)
                .code("PROMO500")
                .discountType(DiscountTypeEnum.FLAT_AMOUNT)
                .discountValue(BigDecimal.valueOf(500.00))
                .build();
    }

    public static CreateOrderRequest buildOrderRequest()
    {
        OrderProductRequest orderProductRequest = new OrderProductRequest(1, 2);
        List<OrderProductRequest> orderProductRequests = List.of(orderProductRequest);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setCustomerId(CUSTOMER_ID);
        createOrderRequest.setProducts(orderProductRequests);

        return createOrderRequest;
    }

    public static CreateProductRequest buildCreateProductRequest() {
        return CreateProductRequest.builder()
                .name("Test Product")
                .price(BigDecimal.valueOf(1.99))
                .description("")
                .categoryId(1)
                .quantity(5)
                .build();
    }

    public static OrderFilter buildOrderFilter() {
        return OrderFilter.builder()
                .customerId(3)
                .pageSize(1)
                .build();
    }

    public static LowStockMessage buildLowStockMsg() {
        return LowStockMessage.builder()
                .productId(1)
                .build();
    }

    public static CreateOrderResponse buildOrderResponse() {
        return CreateOrderResponse.builder()
                .orderId(111)
                .totalAmount(new BigDecimal("150.00"))
                .status("completed")
                .build();
    }
}
