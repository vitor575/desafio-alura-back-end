package com.desafio1.demo.videos;

public record VideosResponse(Long id,
                             String titulo,
                             String descricao,
                             String url) {
    public VideosResponse (Videos videos) {
        this(videos.getId(), videos.getTitulo(), videos.getDescricao(), videos.getUrl());
    }
}
