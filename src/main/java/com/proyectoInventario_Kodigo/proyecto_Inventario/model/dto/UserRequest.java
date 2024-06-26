package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.IUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest implements IUser {
    @NotBlank
    @Size(min = 4, max = 8)
    private String username;

    @NotEmpty
    @Email
    private String email;

    private boolean admin;

    public UserRequest(String updatedUser, String mail, String password123) {
    }
}
