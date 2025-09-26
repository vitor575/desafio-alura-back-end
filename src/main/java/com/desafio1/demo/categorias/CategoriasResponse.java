package com.desafio1.demo.categorias;

import com.desafio1.demo.videos.Videos;
import lombok.AllArgsConstructor;

public record CategoriasResponse(Long id, String titulo, String cor) {
    public CategoriasResponse (Categorias categoria) {
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }
}
