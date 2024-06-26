package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.UserDto;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.UserRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    Page<UserDto> findAll(Pageable pageable);
    Optional<UserDto> findById(Long id);
    UserDto save(UserEntity user);
    Optional<UserDto> update(UserRequest user, Long id);
    void deleteById(Long id);
}
