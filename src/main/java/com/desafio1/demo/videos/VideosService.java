package com.desafio1.demo.videos;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideosService {

    private final VideosRepository videosRepository;

    public List<VideosResponse> obterTodosVideos () {
        return videosRepository.findAll().stream().map(VideosResponse::new).toList();
    }

    public VideosResponse obterVideo(Long id) {
        return videosRepository.findById(id)
                .map(VideosResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo não encontrado"));
    }

    public void criarVideo(VideosRequest dados) {
        Videos videos = new Videos();
        videos.setTitulo(dados.titulo());
        videos.setDescricao(dados.descricao());
        videos.setUrl(dados.url());
        videosRepository.save(videos);
    }

    public void deletarVideo(Long id) {
        videosRepository.deleteById(id);
    }

    public void atualizarVideo (Long id, VideosRequest dados) {
        Videos videoExistente = videosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo não encontrado"));

        videoExistente.setTitulo(dados.titulo());
        videoExistente.setDescricao(dados.descricao());
        videoExistente.setUrl(dados.url());

        videosRepository.save(videoExistente);
    }
}
