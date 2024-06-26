package com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
