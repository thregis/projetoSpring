//package com.example.demo.model;
//
//import javax.persistence.*;
//
////@Entity
//public class Mentoria {
//
////    @Id
////    @Column(name = "idMentoria")
////    private Long id;
//
////    @Id //chave primária composta por ser fruto de relacionamento n-n
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "mentoriaAlunoId")
////    private Aluno aluno;
////
////    @Id
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "mentoriaMentorId")
////    private Mentor mentor;
////
////
////    public Aluno getAluno() {
////        return aluno;
////    }
////
////    public void setAluno(Aluno aluno) {
////        this.aluno = aluno;
////    }
////
////    public Mentor getMentor() {
////        return mentor;
////    }
////
////    public void setMentor(Mentor mentor) {
////        this.mentor = mentor;
////    }
//}
