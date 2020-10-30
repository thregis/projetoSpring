package com.example.demo.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AlunoDTO {

    private Long id;

    @NotNull(message = "Necessário inserir nome")
    @NotEmpty(message = "Nome não pode ser vazio")
    private String name;

    private String classe;

    private Long programaId;
    private String programaName; // aparecer nome também

    private Boolean active;

    public AlunoDTO(){}

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

    public Long getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Long programaId) {
        this.programaId = programaId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getProgramaName() {
        return programaName;
    }

    public void setProgramaName(String programaName) {
        this.programaName = programaName;
    }
}
