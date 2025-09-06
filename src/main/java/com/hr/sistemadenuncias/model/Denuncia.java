package com.hr.sistemadenuncias.model;

import com.hr.sistemadenuncias.enums.ClassificacaoDenuncia;
import com.hr.sistemadenuncias.enums.TipoDenuncia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private boolean anonima;

    private String nomeDoador;

    private String contatoDoador;

    @Enumerated(EnumType.STRING)
    private TipoDenuncia tipo;

    @Enumerated(EnumType.STRING)
    private ClassificacaoDenuncia classificacao;

    private LocalDateTime dataHoraCriacao;

    private String setorResponsavel;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}