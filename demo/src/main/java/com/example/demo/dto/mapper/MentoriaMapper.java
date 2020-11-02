package com.example.demo.dto.mapper;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.ProgramaDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;
import com.example.demo.model.Programa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MentoriaMapper {


    @Mappings({
            @Mapping(source = "mentorId", target = "mentor.id"),
            @Mapping(source = "alunoId", target = "aluno.id")})
    Mentoria toMentoria(MentoriaDTO mentoriaDTO);

    @Mappings({
            @Mapping(source = "mentor.id", target = "mentorId"),
            @Mapping(source = "mentor.name", target = "mentorName"),
            @Mapping(source = "aluno.id", target = "alunoId"),
            @Mapping(source = "aluno.name", target = "alunoName")})
    MentoriaDTO toMentoriaDTO(Mentoria mentoria);
//    public static MentoriaDTO toMentoriaDTO(Mentoria mentoria){
//        MentoriaDTO mentoriaDTO = new MentoriaDTO();
//
//        mentoriaDTO.setId(mentoria.getId());
//
//
//        mentoriaDTO.setMentorId(mentoria.getMentor().getId());
//        mentoriaDTO.setMentorName(mentoria.getMentor().getName());
//
//        mentoriaDTO.setAlunoId(mentoria.getAluno().getId());
//        mentoriaDTO.setAlunoName(mentoria.getAluno().getName());
//
//
//        mentoriaDTO.setActive(mentoria.getActive());
//        return mentoriaDTO;
//    }
//
//    public static Mentoria toMentoria(MentoriaDTO mentoriaDTO){
//        Mentoria mentoria = new Mentoria();
//        mentoria.setId(mentoriaDTO.getId());
//
//            Mentor mentor = new Mentor();
//            mentor.setId(mentoriaDTO.getMentorId());
//            mentoria.setMentor(mentor);
//
//            Aluno aluno = new Aluno();
//            aluno.setId(mentoriaDTO.getAlunoId());
//            mentoria.setAluno(aluno);
//
//        mentoria.setActive(mentoriaDTO.getActive());
//        return mentoria;
//    }
}
