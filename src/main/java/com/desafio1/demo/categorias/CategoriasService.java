package com.desafio1.demo.categorias;

import com.desafio1.demo.videos.Videos;
import com.desafio1.demo.videos.VideosRepository;
import com.desafio1.demo.videos.VideosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriasService {

    private final CategoriasRepository categoriasRepository;
    private final VideosRepository videosRepository;

    public List<CategoriasResponse> getCategorias() {
        return categoriasRepository.findAll()
                .stream()
                .map(CategoriasResponse::new)
                .toList();
    }

    public CategoriasResponse getCategoria(Long id) {
        Categorias categoria = categoriasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        return new CategoriasResponse(categoria);
    }


    public CategoriasResponse createCategoria(CategoriasRequest dados) {
        Categorias categorias = new Categorias();
        categorias.setCor(dados.cor());
        categorias.setTitulo(dados.titulo());

        Categorias salvo = categoriasRepository.save(categorias);
        return new CategoriasResponse(salvo);
    }

    public CategoriasResponse updateCategoria(Long id, CategoriasRequest dados) {
        Categorias categoria = categoriasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        categoria.setTitulo(dados.titulo());
        categoria.setCor(dados.cor());

        Categorias salvo = categoriasRepository.save(categoria);
        return new CategoriasResponse(salvo);
    }

    public void deleteCategoria(Long id) {
        categoriasRepository.deleteById(id);
    }

    public Page<VideosResponse> obterVideosPorCategoria(Long categoriaId, Pageable pageable) {
        categoriasRepository.findById(categoriaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado"));

        Page<Videos> videos = videosRepository.findByCategoriaId(categoriaId, pageable);

        return videos.map(VideosResponse::new);
    }
}
