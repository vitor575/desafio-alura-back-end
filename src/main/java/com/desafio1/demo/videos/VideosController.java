package com.desafio1.demo.videos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public List<VideosResponse> getVideos () {
        return videosService.obterTodosVideos();
    }

    @GetMapping("/{id}")
    public VideosResponse getVideo (@PathVariable Long id){
        return videosService.obterVideo(id);
    }
    @PostMapping
    @Transactional
    public void createVideo (@RequestBody @Valid VideosRequest dados) {
        videosService.criarVideo(dados);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteVideo (@PathVariable Long id) {
        videosService.deletarVideo(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public void updateVideo (@PathVariable Long id, @RequestBody VideosRequest dados) {
        videosService.atualizarVideo(id, dados);
    }
}
