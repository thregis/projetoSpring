package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public class ProgramaDTO {

    private Long id;

    @NotNull(message = "Nome precisa ser inserido")
    @NotEmpty(message = "Nome precisa ser inserido")
    private String name;

    @Past //valida se a data está no passado   @Future faz o inverso
    private LocalDate dataInicio;

    private LocalDate dataFinal;

    private Boolean active;

    private List<MentorDTO> mentores;


    public ProgramaDTO(){

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<MentorDTO> getMentores() {
        return mentores;
    }

    public void setMentores(List<MentorDTO> mentores) {
        this.mentores = mentores;
    }
}
