package org.madmotor.apimadmotor.categorias.mapper;

import org.madmotor.apimadmotor.categorias.dto.CategoriaDto;
import org.madmotor.apimadmotor.categorias.models.Categoria;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CategoriaMapper {
    public Categoria map(CategoriaDto dto) {
        return new Categoria(
                null,
                dto.getName(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false);
    }

    public Categoria map(CategoriaDto dto, Categoria categoria) {
        return new Categoria(
                categoria.getId(),
                dto.getName() != null ? dto.getName() : categoria.getName(),
                categoria.getCreatedAt(),
                LocalDateTime.now(),
                categoria.getIsDeleted() != null ? dto.getIsDeleted() : categoria.getIsDeleted());
    }
}
