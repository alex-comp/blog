package com.spring.blog.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TB_GRUPO")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
