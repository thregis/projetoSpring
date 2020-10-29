package com.example.demo.model;

import org.hibernate.mapping.Join;

import javax.persistence.*;

@Entity
@Table(name = "mentor")
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentorId")
    private Long id;

    @Column(name = "mentorName")
    private String name;

    @Column (name = "mentorIdade")
    private int idade;

    @Column (name = "mentorPais")
    private String pais;

    @Column(name = "mentorEscola")
    private String escola;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorPrograma")
    private Programa programa;

    @Column(name = "mentorActive")
    private Boolean active;

    public Mentor(){

    }
    /*public Mentor(String name, int idade, String pais, String escola) {
        this.name = name;
        this.idade = idade;
        this.pais = pais;
        this.escola = escola;
    }
*/

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
