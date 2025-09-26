package com.desafio1.demo.videos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosRepository extends JpaRepository<Videos, Long> {
    Page<Videos> findByCategoriaId(Long categoriaId, Pageable pageable);
    Page<Videos> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
