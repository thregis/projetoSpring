package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvaliacaoDTO {

    private Long id;

    private Long mentoriaId;
    private String  mentoriaMentorName;
    private String mentoriaAlunoName;

    private Long disciplinaId;
    private String disciplinaName;
    
    private Double nota;

    private LocalDate data;

    private Boolean active;

    public AvaliacaoDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMentoriaId() {
        return mentoriaId;
    }

    public void setMentoriaId(Long mentoriaId) {
        this.mentoriaId = mentoriaId;
    }

    public String getMentoriaMentorName() {
        return mentoriaMentorName;
    }

    public void setMentoriaMentorName(String mentoriaMentorName) {
        this.mentoriaMentorName = mentoriaMentorName;
    }

    public String getMentoriaAlunoName() {
        return mentoriaAlunoName;
    }

    public void setMentoriaAlunoName(String mentoriaAlunoName) {
        this.mentoriaAlunoName = mentoriaAlunoName;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getDisciplinaName() {
        return disciplinaName;
    }

    public void setDisciplinaName(String disciplinaName) {
        this.disciplinaName = disciplinaName;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
