package com.example.demo.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alunoId")
    private Long id;

    @Column(name = "alunoName")
    @Size(min = 3, max = 50, message = "Mínimo de 3 caracteres, máximo de 50.")
    private String name;

    @Column(name = "alunoClasse")
    private String classe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alunoPrograma")
    private Programa programa;

    @Column(name = "alunoActive")
    private Boolean active;

    public Aluno(){

    }

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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

//fazer uma outra classe Nota com os atributos Aluno, Mentor,
// Data