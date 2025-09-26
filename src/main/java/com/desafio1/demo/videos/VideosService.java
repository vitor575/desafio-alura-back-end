package com.desafio1.demo.videos;

import com.desafio1.demo.categorias.Categorias;
import com.desafio1.demo.categorias.CategoriasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideosService {

    private final VideosRepository videosRepository;
    private final CategoriasRepository categoriasRepository;

    public List<VideosResponse> obterTodosVideos() {
        return videosRepository.findAll().stream().map(VideosResponse::new).toList();
    }

    public VideosResponse obterVideo(Long id) {
        return videosRepository.findById(id)
                .map(VideosResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo não encontrado"));
    }

    public VideosResponse criarVideo(VideosRequest dados) {
        Long idCategoria = dados.idCategoria();
        if (idCategoria == null) {
            idCategoria = 1L;
        }

        Categorias categoria = categoriasRepository.findById(dados.idCategoria())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada"));

        Videos video = new Videos();
        video.setTitulo(dados.titulo());
        video.setDescricao(dados.descricao());
        video.setUrl(dados.url());
        video.setCategoria(categoria);
        Videos salvo = videosRepository.save(video);
        return new VideosResponse(salvo);
    }

    public void deletarVideo(Long id) {
        videosRepository.deleteById(id);
    }

    public VideosResponse atualizarVideo(Long id, VideosRequest dados) {
        Videos videoExistente = videosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo não encontrado"));

        videoExistente.setTitulo(dados.titulo());
        videoExistente.setDescricao(dados.descricao());
        videoExistente.setUrl(dados.url());

        if (dados.idCategoria() != null) {
            Categorias categoria = categoriasRepository.findById(dados.idCategoria())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada"));
            videoExistente.setCategoria(categoria);
        }

        Videos salvo = videosRepository.save(videoExistente);
        return new VideosResponse(salvo);
    }



    public Page<VideosResponse> buscarVideosPorTitulo(String titulo, Pageable pageable) {
        if (titulo == null || titulo.isBlank()) {
            return videosRepository.findAll(pageable).map(VideosResponse::new);
        }
        return videosRepository.findByTituloContainingIgnoreCase(titulo, pageable)
                .map(VideosResponse::new);
    }

    public Page<VideosResponse> obterTodosVideosPagina(Pageable pageable) {
        return videosRepository.findAll(pageable)
                .map(VideosResponse::new);
    }
}
