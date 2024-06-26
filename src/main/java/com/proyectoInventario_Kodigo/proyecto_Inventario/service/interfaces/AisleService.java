package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleResponse;
import java.util.List;

public interface AisleService {

    List<AisleResponse> findAll();
    AisleResponse findByName(String name);

    AisleResponse save(AisleRequest request);
    AisleResponse update(Long id, AisleRequest request);
    void deleteById(Long id);
}
