package com.proyectoInventario_Kodigo.proyecto_Inventario.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    // Product Errors
    PRODUCT_NOT_FOUND("ERR_PROD_001", "Product Not Found."),
    INVALID_PRODUCT("ERR_PROD_002", "Invalid Product Parameters."),
    DUPLICATE_PRODUCT_CODE("ERR_PROD_003", "Duplicate Product Code."),
    DUPLICATE_PRODUCT_NAME("ERR_PROD_004", "Duplicate Product Name."),

    //STOCK
    INSUFFICIENT_STOCK("ERR_STOCK_001", "Insufficient stock available."),

    // Category Errors
    CATEGORY_NOT_FOUND("ERR_CAT_001", "Category Not Found."),
    INVALID_CATEGORY("ERR_CAT_002", "Invalid Category Parameters."),
    DUPLICATE_CATEGORY_CODE("ERR_CAT_003", "Duplicate Category Code."),
    DUPLICATE_CATEGORY_NAME("ERR_CAT_004", "Duplicate Category Name."),

    // Brand Errors
    BRAND_NOT_FOUND("ERR_BRA_001", "Brand Not Found."),
    INVALID_BRAND("ERR_BRA_002", "Invalid Brand Parameters."),
    DUPLICATE_BRAND_CODE("ERR_BRA_003", "Duplicate Brand Code."),
    DUPLICATE_BRAND_NAME("ERR_BRA_004", "Duplicate Brand Name."),

    // Model Errors
    MODEL_NOT_FOUND("ERR_MOD_001", "Model Not Found."),
    INVALID_MODEL("ERR_MOD_002", "Invalid Model Parameters."),
    DUPLICATE_MODEL_CODE("ERR_MOD_003", "Duplicate Model Code."),
    DUPLICATE_MODEL_NAME("ERR_MOD_004", "Duplicate Model Name."),

    //Supplier Errors
    SUPPLIER_NOT_FOUND("ERR_SUP_001", "Supplier Not Found."),
    INVALID_SUPPLIER("ERR_SUP_002", "Invalid Supplier Parameters."),
    DUPLICATE_SUPPLIER_CODE("ERR_SUP_003", "Duplicate Supplier Code."),
    DUPLICATE_SUPPLIER_NAME("ERR_SUP_004", "Duplicate Supplier Name."),

    //Store Errors
    STORE_NOT_FOUND("ERR_STR_001", "Store Not Found."),
    INVALID_STORE("ERR_STR_002", "Invalid Store Parameters."),
    DUPLICATE_STORE_CODE("ERR_STR_003", "Duplicate Store Code."),
    DUPLICATE_STORE_NAME("ERR_STR_004", "Duplicate Store Name."),

    //Aisle Errors
    AISLE_NOT_FOUND("ERR_AISLE_001", "Aisle Not Found."),
    INVALID_AISLE("ERR_AISLE_002", "Invalid Aisle Parameters."),
    DUPLICATE_AISLE_NAME("ERR_AISLE_004", "Duplicate Aisle Name."),

    //Shelf Errors
    SHELF_NOT_FOUND("ERR_SHF_001", "Shelf Not Found."),
    INVALID_SHELF("ERR_SHF_002", "Invalid Shelf Parameters."),
    DUPLICATE_SHELF_NAME("ERR_SHF_004", "Duplicate Shelf Name."),

    // General Errors
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred."),
    VALIDATION_ERROR("ERR_GEN_002", "Validation Error."),
    AUTHENTICATION_ERROR("ERR_GEN_003", "Authentication Failed."),
    AUTHORIZATION_ERROR("ERR_GEN_004", "Authorization Failed."),
    DATABASE_ERROR("ERR_GEN_005", "Database Error.");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

