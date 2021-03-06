package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplinaId")
    private Long id;

    @Column(name = "disciplinaName")
    @Size(min = 2, max = 50, message = "Mínimo de 2 caracteres, máximo de 50.")
    private String name;

    @Column(name = "disciplinaDescricao")
    private String descricao;

    @Column(name = "disciplinaActive")
    private Boolean active;

    public Disciplina(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
