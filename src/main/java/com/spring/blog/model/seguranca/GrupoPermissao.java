package com.spring.blog.model.seguranca;

import com.spring.blog.model.Grupo;
import com.spring.blog.model.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "TB_GRUPO_PERMISSAO")
public class GrupoPermissao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permissao_id")
    private Permissao permissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
