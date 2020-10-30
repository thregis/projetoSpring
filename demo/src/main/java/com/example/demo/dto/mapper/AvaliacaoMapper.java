package com.example.demo.dto.mapper;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.model.*;

public class AvaliacaoMapper {

    public static AvaliacaoDTO toMAvaliacaoDTO(Avaliacao avaliacao){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();

        avaliacaoDTO.setId(avaliacao.getId());

        avaliacaoDTO.setMentoriaId(avaliacao.getMentoria().getId());
        avaliacaoDTO.setMentoriaMentorName(avaliacao.getMentoria().getMentor().getName()); // teste

        avaliacaoDTO.setDisciplinaId(avaliacao.getDisciplina().getId());
        avaliacaoDTO.setDisciplinaName(avaliacao.getDisciplina().getName());

        avaliacaoDTO.setNota(avaliacao.getNota());

        avaliacaoDTO.setData(avaliacao.getData());

        avaliacaoDTO.setActive(avaliacao.getActive());
        return avaliacaoDTO;
    }

    public static Avaliacao toAvaliacao(AvaliacaoDTO avaliacaoDTO){
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(avaliacaoDTO.getId());

        Mentoria mentoria = new Mentoria();
        mentoria.setId(avaliacaoDTO.getMentoriaId());
        avaliacao.setMentoria(mentoria);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(avaliacaoDTO.getDisciplinaId());
        avaliacao.setDisciplina(disciplina);

        avaliacao.setNota(avaliacaoDTO.getNota());

        avaliacao.setData(avaliacaoDTO.getData());

        avaliacao.setActive(avaliacaoDTO.getActive());
        return avaliacao;
    }
}
