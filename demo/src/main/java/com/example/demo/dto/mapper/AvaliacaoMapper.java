package com.example.demo.dto.mapper;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    @Mappings({
            @Mapping(source = "mentoriaId", target = "mentoria.id"),
            @Mapping(source = "disciplinaId", target = "disciplina.id")})
    Avaliacao toAvaliacao(AvaliacaoDTO avaliacaoDTO);

    @Mappings({
            @Mapping(source = "mentoria.id", target = "mentoriaId"),
            @Mapping(source = "mentoria.mentor.name", target = "mentoriaMentorName"),
            @Mapping(source = "mentoria.aluno.name", target = "mentoriaAlunoName"),
            @Mapping(source = "disciplina.id", target = "disciplinaId"),
            @Mapping(source = "disciplina.name", target = "disciplinaName")})
    AvaliacaoDTO toAvaliacaoDTO(Avaliacao avaliacao);


//    public static AvaliacaoDTO toAvaliacaoDTO(Avaliacao avaliacao){
//        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
//
//        avaliacaoDTO.setId(avaliacao.getId());
//
//        avaliacaoDTO.setMentoriaId(avaliacao.getMentoria().getId());
//        avaliacaoDTO.setMentoriaMentorName(avaliacao.getMentoria().getMentor().getName()); // teste
//
//        avaliacaoDTO.setDisciplinaId(avaliacao.getDisciplina().getId());
//        avaliacaoDTO.setDisciplinaName(avaliacao.getDisciplina().getName());
//
//        avaliacaoDTO.setNota(avaliacao.getNota());
//
//        avaliacaoDTO.setData(avaliacao.getData());
//
//        avaliacaoDTO.setActive(avaliacao.getActive());
//        return avaliacaoDTO;
//    }
//
//    public static Avaliacao toAvaliacao(AvaliacaoDTO avaliacaoDTO){
//        Avaliacao avaliacao = new Avaliacao();
//        avaliacao.setId(avaliacaoDTO.getId());
//
//        Mentoria mentoria = new Mentoria();
//        mentoria.setId(avaliacaoDTO.getMentoriaId());
//        avaliacao.setMentoria(mentoria);
//
//        Disciplina disciplina = new Disciplina();
//        disciplina.setId(avaliacaoDTO.getDisciplinaId());
//        avaliacao.setDisciplina(disciplina);
//
//        avaliacao.setNota(avaliacaoDTO.getNota());
//
//        avaliacao.setData(avaliacaoDTO.getData());
//
//        avaliacao.setActive(avaliacaoDTO.getActive());
//        return avaliacao;
//    }
}
