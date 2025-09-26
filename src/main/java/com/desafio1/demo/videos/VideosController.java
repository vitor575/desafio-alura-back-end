package com.desafio1.demo.videos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class VideosController {
    private final VideosService videosService;

    @GetMapping
    public Page<VideosResponse> listarVideos(
            @RequestParam(name = "search", required = false) String pesquisa,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        if (pesquisa == null || pesquisa.isBlank()) {
            return videosService.obterTodosVideosPagina(pageable);
        }
        return videosService.buscarVideosPorTitulo(pesquisa, pageable);
    }

    @GetMapping("/{id}")
    public VideosResponse getVideo(@PathVariable Long id) {
        return videosService.obterVideo(id);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public VideosResponse createVideo(@RequestBody @Valid VideosRequest dados) {
        return videosService.criarVideo(dados);
    }

    @PutMapping("/{id}")
    @Transactional
    public VideosResponse updateVideo(@PathVariable Long id, @RequestBody @Valid VideosRequest dados) {
        return videosService.atualizarVideo(id, dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVideo(@PathVariable Long id) {
        videosService.deletarVideo(id);
    }
}
