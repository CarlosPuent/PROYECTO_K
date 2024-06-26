package com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
