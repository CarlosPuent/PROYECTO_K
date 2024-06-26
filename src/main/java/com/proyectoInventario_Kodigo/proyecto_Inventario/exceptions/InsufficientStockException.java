package com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions;

import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(ErrorCatalog errorCatalog) {
    }

    public InsufficientStockException() {}
}
