package com.desafio1.demo.categorias;

import com.desafio1.demo.videos.VideosResponse;
import com.desafio1.demo.videos.VideosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriasController {
    private final VideosService videosService;
    private final CategoriasService categoriasService;

    @PostMapping
    public CategoriasResponse createCategoria(@RequestBody @Valid CategoriasRequest dados) {
        return categoriasService.createCategoria(dados);
    }

    @GetMapping
    public List<CategoriasResponse> getCategorias() {
        return categoriasService.getCategorias();
    }

    @GetMapping("/{id}")
    public CategoriasResponse getCategoria(@PathVariable Long id) {
        return categoriasService.getCategoria(id);
    }

    @PutMapping("/{id}")
    public CategoriasResponse updateCategoria(@PathVariable Long id, @RequestBody @Valid CategoriasRequest dados) {
        return categoriasService.updateCategoria(id, dados);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriasService.deleteCategoria(id);
    }

    @GetMapping("/{id}/videos")
    public Page<VideosResponse> getVideosByCategoria(
            @PathVariable("id") Long categoriaId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return categoriasService.obterVideosPorCategoria(categoriaId, pageable);
    }
}

