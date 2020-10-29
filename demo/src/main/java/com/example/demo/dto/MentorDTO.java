package com.example.demo.dto;

import com.example.demo.model.Programa;

public class MentorDTO {

    private Long id;

    private String name;

    private int idade;

    private String pais;

    private String escola;

    private Long programaId;

    private Boolean active;

    public MentorDTO(){

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
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

// colocar a classe mentor toda aqui e os objetos serão criados nos 2, pra lá ter a responsabilidade do banco de
    // dados, e aqui ter a responsabilidade das chamadas de método. falta ajeitar.
}
