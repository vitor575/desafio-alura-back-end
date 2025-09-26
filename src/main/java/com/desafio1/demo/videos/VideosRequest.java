package com.desafio1.demo.videos;

import jakarta.validation.constraints.NotBlank;

public record VideosRequest(
        Long idCategoria,
        String titulo,
        String descricao,
        String url) {
}
