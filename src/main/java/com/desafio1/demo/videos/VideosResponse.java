package com.desafio1.demo.videos;

public record VideosResponse(Long id,
                             Long idCategoria,
                             String titulo,
                             String descricao,
                             String url) {
    public VideosResponse (Videos videos) {
        this(videos.getId(),videos.getCategoria().getId(), videos.getTitulo(), videos.getDescricao(), videos.getUrl());
    }
}
