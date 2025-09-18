package com.desafio1.demo.videos;

import jakarta.validation.constraints.NotBlank;

public record VideosRequest(
        @NotBlank(message = "Titulo é obrigatório")
        String titulo,
        @NotBlank(message = "Descrição é obrigatório")
        String descricao,
        @NotBlank(message = "Url é obrigatório")
        String url) {
}
