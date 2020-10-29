package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "mentoria")
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentoriaId")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoriaAluno")
    private Aluno aluno;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoriaMentor")
    private Mentor mentor;

    @Column(name = "mentoriaActive")
    private Boolean active;

    public Mentoria(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
