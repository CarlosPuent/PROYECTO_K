package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    // Product Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleProductNotFoundException() {
        return ErrorResponse.builder()
                .code(PRODUCT_NOT_FOUND.getCode())
                .message(PRODUCT_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateProductCodeException.class)
    public ErrorResponse handleDuplicateProductCodeException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_PRODUCT_CODE.getCode())
                .message(DUPLICATE_PRODUCT_CODE.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateProductNameException.class)
    public ErrorResponse handleDuplicateProductNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_PRODUCT_NAME.getCode())
                .message(DUPLICATE_PRODUCT_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidProductException.class)
    public ErrorResponse handleInvalidProductException() {
        return ErrorResponse.builder()
                .code(INVALID_PRODUCT.getCode())
                .message(INVALID_PRODUCT.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Category Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFoundException() {
        return ErrorResponse.builder()
                .code(CATEGORY_NOT_FOUND.getCode())
                .message(CATEGORY_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateCategoryCodeException.class)
    public ErrorResponse handleDuplicateCategoryCodeException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_CATEGORY_CODE.getCode())
                .message(DUPLICATE_CATEGORY_CODE.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateCategoryNameException.class)
    public ErrorResponse handleDuplicateCategoryNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_CATEGORY_NAME.getCode())
                .message(DUPLICATE_CATEGORY_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCategoryException.class)
    public ErrorResponse handleInvalidCategoryException() {
        return ErrorResponse.builder()
                .code(INVALID_CATEGORY.getCode())
                .message(INVALID_CATEGORY.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Brand Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BrandNotFoundException.class)
    public ErrorResponse handleBrandNotFoundException() {
        return ErrorResponse.builder()
                .code(BRAND_NOT_FOUND.getCode())
                .message(BRAND_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateBrandCodeException.class)
    public ErrorResponse handleDuplicateBrandCodeException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_BRAND_CODE.getCode())
                .message(DUPLICATE_BRAND_CODE.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateBrandNameException.class)
    public ErrorResponse handleDuplicateBrandNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_BRAND_NAME.getCode())
                .message(DUPLICATE_BRAND_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidBrandException.class)
    public ErrorResponse handleInvalidBrandException() {
        return ErrorResponse.builder()
                .code(INVALID_BRAND.getCode())
                .message(INVALID_BRAND.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Model Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException() {
        return ErrorResponse.builder()
                .code(MODEL_NOT_FOUND.getCode())
                .message(MODEL_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateModelCodeException.class)
    public ErrorResponse handleDuplicateModelCodeException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_MODEL_CODE.getCode())
                .message(DUPLICATE_MODEL_CODE.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateModelNameException.class)
    public ErrorResponse handleDuplicateModelNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_MODEL_NAME.getCode())
                .message(DUPLICATE_MODEL_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidModelException.class)
    public ErrorResponse handleInvalidModelException() {
        return ErrorResponse.builder()
                .code(INVALID_MODEL.getCode())
                .message(INVALID_MODEL.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // General Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("ERR_GEN_002")
                .message("Validation Error.")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .detailsMessages(errors)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse handleAuthenticationException() {
        return ErrorResponse.builder()
                .code(AUTHENTICATION_ERROR.getCode())
                .message(AUTHENTICATION_ERROR.getMessage())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public ErrorResponse handleAuthorizationException() {
        return ErrorResponse.builder()
                .code(AUTHORIZATION_ERROR.getCode())
                .message(AUTHORIZATION_ERROR.getMessage())
                .httpStatus(HttpStatus.FORBIDDEN)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    public ErrorResponse handleDatabaseException() {
        return ErrorResponse.builder()
                .code(DATABASE_ERROR.getCode())
                .message(DATABASE_ERROR.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericException(Exception exception) {
        return ErrorResponse.builder()
                .code(GENERIC_ERROR.getCode())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(GENERIC_ERROR.getMessage())
                .detailsMessages(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Store Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StoreNotFoundException.class)
    public ErrorResponse handleStoreNotFoundException() {
        return ErrorResponse.builder()
                .code(STORE_NOT_FOUND.getCode())
                .message(STORE_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateStoreCodeException.class)
    public ErrorResponse handleDuplicateStoreCodeException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_STORE_CODE.getCode())
                .message(DUPLICATE_STORE_CODE.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateStoreNameException.class)
    public ErrorResponse handleDuplicateStoreNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_STORE_NAME.getCode())
                .message(DUPLICATE_STORE_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidStoreException.class)
    public ErrorResponse handleInvalidStoreException() {
        return ErrorResponse.builder()
                .code(INVALID_STORE.getCode())
                .message(INVALID_STORE.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Supplier Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SupplierNotFoundException.class)
    public ErrorResponse handleSupplierNotFoundException() {
        return ErrorResponse.builder()
                .code(SUPPLIER_NOT_FOUND.getCode())
                .message(SUPPLIER_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateSupplierCodeException.class)
    public ErrorResponse handleDuplicateSupplierCodeException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_SUPPLIER_CODE.getCode())
                .message(DUPLICATE_SUPPLIER_CODE.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateSupplierNameException.class)
    public ErrorResponse handleDuplicateSupplierNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_SUPPLIER_NAME.getCode())
                .message(DUPLICATE_SUPPLIER_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidSupplierException.class)
    public ErrorResponse handleInvalidSupplierException() {
        return ErrorResponse.builder()
                .code(INVALID_SUPPLIER.getCode())
                .message(INVALID_SUPPLIER.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Aisle Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AisleNotFoundException.class)
    public ErrorResponse handleAisleNotFoundException() {
        return ErrorResponse.builder()
                .code(AISLE_NOT_FOUND.getCode())
                .message(AISLE_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateAisleNameException.class)
    public ErrorResponse handleDuplicateAisleNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_AISLE_NAME.getCode())
                .message(DUPLICATE_AISLE_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Shelf Errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ShelfNotFoundException.class)
    public ErrorResponse handleShelfNotFoundException() {
        return ErrorResponse.builder()
                .code(SHELF_NOT_FOUND.getCode())
                .message(SHELF_NOT_FOUND.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DuplicateShelfNameException.class)
    public ErrorResponse handleDuplicateShelfNameException() {
        return ErrorResponse.builder()
                .code(DUPLICATE_SHELF_NAME.getCode())
                .message(DUPLICATE_SHELF_NAME.getMessage())
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Stock Errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsufficientStockException.class)
    public ErrorResponse handleInsufficientStockException() {
        return ErrorResponse.builder()
                .code(INSUFFICIENT_STOCK.getCode())
                .message(INSUFFICIENT_STOCK.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }
}