package com.desafio1.demo.videos;

import com.desafio1.demo.categorias.Categorias;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Titulo é obrigatório")
    private String titulo;

    @Column(columnDefinition = "text")
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @Column(nullable = false)
    @NotBlank(message = "Url é obrigatório")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categorias categoria;
}
