package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "programa")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DEVIA SER IDENTITY MAS TÁ BUGANDO COM FALTA DE DEFAULT EM ID
    @Column(name = "programaId")
    private Long id;

    @Column(name = "programaName")
    private String name;

    @Column(name = "programaInicio")
    private LocalDate dataInicio;   //USAR LOCALDATE

    @Column(name = "programaFinal")
    private LocalDate dataFinal;

    @Column(name = "programaActive")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL/*, mappedBy = "programa"*/)
    @JoinColumn(name = "mentorPrograma")
    private List<Mentor> mentores;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
//    @JoinColumn(name = "alunoPrograma")
//    private List<Aluno> alunos;

    public Programa(){

    }

//    public Programa(Long id, String name, int anoInicio, int anoFinal){
//        this.id = id;
//        this.name = name;
//        this.anoInicio = anoInicio;
//        this.anoFinal = anoFinal;
//    }


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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Mentor> getMentores() {
        return mentores;
    }

    public void setMentores(List<Mentor> mentores) {
        this.mentores = mentores;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

