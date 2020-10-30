package com.example.demo.dto.mapper;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;


public class MentoriaMapper {

    public static MentoriaDTO toMentoriaDTO(Mentoria mentoria){
        MentoriaDTO mentoriaDTO = new MentoriaDTO();

        mentoriaDTO.setId(mentoria.getId());

//        if(mentoria.getMentor().getActive()==false || mentoria.getAluno().getActive()==false){
//            mentoriaDTO.setActive(false);
//            return mentoriaDTO; //PRECISA APLICAR UMA EXCEÇÃO AQUI PARA NÃO ACEITAR CRIAR SEM MENTOR E ALUNO.
//        }

//        if (mentoria.getMentor() == null){
//            mentoriaDTO.setMentorId(null);
//            mentoriaDTO.setMentorName(null);
//        }else{
            mentoriaDTO.setMentorId(mentoria.getMentor().getId());
            mentoriaDTO.setMentorName(mentoria.getMentor().getName()); // teste
//        }

//        if (mentoria.getAluno()==null){
//            mentoriaDTO.setAlunoId(null);
//            mentoriaDTO.setAlunoName(null);
//        }else{
            mentoriaDTO.setAlunoId(mentoria.getAluno().getId());
            mentoriaDTO.setAlunoName(mentoria.getAluno().getName()); // teste


        mentoriaDTO.setActive(mentoria.getActive());
        return mentoriaDTO;
    }

    public static Mentoria toMentoria(MentoriaDTO mentoriaDTO){
        Mentoria mentoria = new Mentoria();
        mentoria.setId(mentoriaDTO.getId());


//        if (mentoriaDTO.getMentorId()==null||mentoriaDTO.getActive()==false||
//            mentoriaDTO.getAlunoId()==null || mentoriaDTO.getActive()==false){
//            mentoria.setActive(false);
//            return mentoria;
//        }

//        if (mentoriaDTO.getMentorId() == null){
//            mentoria.setMentor(null);
//        }else{
            Mentor mentor = new Mentor();
            mentor.setId(mentoriaDTO.getMentorId());
            mentoria.setMentor(mentor);
//        }

//        if (mentoriaDTO.getAlunoId() == null){
//            mentoria.setAluno(null);
//        }else{
            Aluno aluno = new Aluno();
            aluno.setId(mentoriaDTO.getAlunoId());
            mentoria.setAluno(aluno);
//        }

        mentoria.setActive(mentoriaDTO.getActive());
        return mentoria;
    }
}
