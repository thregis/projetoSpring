package com.example.demo.dto;

public class MentoriaDTO {

    private Long id;

    private Long alunoId;
    private String alunoName;

    private Long mentorId;
    private String mentorName;

    private Boolean active;

    public MentoriaDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAlunoName() {
        return alunoName;
    }

    public void setAlunoName(String alunoName) {
        this.alunoName = alunoName;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }
}
