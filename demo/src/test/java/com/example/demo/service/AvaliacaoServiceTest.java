package com.example.demo.service;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.AvaliacaoMapper;
import com.example.demo.model.*;
import com.example.demo.repository.AvaliacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoServiceTest {

    @Mock
    AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    AvaliacaoService avaliacaoService;

    @Mock
    AvaliacaoMapper avaliacaoMapper;
    @Mock
    MentoriaService mentoriaService;
    @Mock
    DisciplinaService disciplinaService;


    @Test
    public void testGetAvaliacoesAtivas() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("teste");
        disciplina.setActive(true);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setMentoria(mentoria);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setActive(true);
        avaliacao.setNota(0.0);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setMentoriaId(id);
        avaliacaoDTO.setMentoriaMentorName("tt");
        avaliacaoDTO.setMentoriaAlunoName("t");
        avaliacaoDTO.setDisciplinaId(id);
        avaliacaoDTO.setDisciplinaName("teste");
        avaliacaoDTO.setActive(true);
        avaliacaoDTO.setNota(0.0);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setId(id);
        avaliacao2.setMentoria(mentoria);
        avaliacao2.setDisciplina(disciplina);
        avaliacao2.setActive(true);
        avaliacao2.setNota(0.0);

        List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
        avaliacoes.add(avaliacao);
        avaliacoes.add(avaliacao2);

        Mockito.when(avaliacaoRepository.findByActive(true)).thenReturn(avaliacoes);
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao)).thenReturn(avaliacaoDTO);

        Optional<List<AvaliacaoDTO>> all = this.avaliacaoService.getAvaliacoes();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(avaliacoes.stream().map(avaliacaoMapper::toAvaliacaoDTO).collect(Collectors.toList())))
        );

    }

    @Test
    public void testGetAvaliacoesInativas() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("teste");
        disciplina.setActive(true);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setMentoria(mentoria);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setActive(false);
        avaliacao.setNota(0.0);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setMentoriaId(id);
        avaliacaoDTO.setMentoriaMentorName("tt");
        avaliacaoDTO.setMentoriaAlunoName("t");
        avaliacaoDTO.setDisciplinaId(id);
        avaliacaoDTO.setDisciplinaName("teste");
        avaliacaoDTO.setActive(false);
        avaliacaoDTO.setNota(0.0);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setId(id);
        avaliacao2.setMentoria(mentoria);
        avaliacao2.setDisciplina(disciplina);
        avaliacao2.setActive(false);
        avaliacao2.setNota(0.0);

        List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
        avaliacoes.add(avaliacao);
        avaliacoes.add(avaliacao2);

        Mockito.when(avaliacaoRepository.findByActive(false)).thenReturn(avaliacoes);
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao)).thenReturn(avaliacaoDTO);

        Optional<List<AvaliacaoDTO>> all = this.avaliacaoService.getAvaliacoesInativas();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(avaliacoes.stream().map(avaliacaoMapper::toAvaliacaoDTO).collect(Collectors.toList())))
        );

    }

    @Test
    public void testGetAvaliacaoById() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("teste");
        disciplina.setActive(true);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setMentoria(mentoria);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setActive(true);
        avaliacao.setNota(0.0);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setMentoriaId(id);
        avaliacaoDTO.setMentoriaMentorName("tt");
        avaliacaoDTO.setMentoriaAlunoName("t");
        avaliacaoDTO.setDisciplinaId(id);
        avaliacaoDTO.setDisciplinaName("teste");
        avaliacaoDTO.setActive(true);
        avaliacaoDTO.setNota(0.0);

        Mockito.when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao)).thenReturn(avaliacaoDTO);

        Optional<AvaliacaoDTO> avaliacaoByIndex = this.avaliacaoService.getAvaliacaoByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertTrue(avaliacaoByIndex.isPresent());

        assertAll( //alt+enter static import
                () -> Assertions.assertTrue(avaliacaoByIndex.isPresent()),
                () -> Assertions.assertEquals(avaliacao.getId(), avaliacaoByIndex.get().getId()),
                () -> Assertions.assertEquals(avaliacao.getMentoria().getId(), avaliacaoByIndex.get().getMentoriaId()),
                () -> Assertions.assertEquals(avaliacao.getMentoria().getAluno().getName(), avaliacaoByIndex.get().getMentoriaAlunoName()),
                () -> Assertions.assertEquals(avaliacao.getMentoria().getMentor().getName(), avaliacaoByIndex.get().getMentoriaMentorName()),
                () -> Assertions.assertEquals(avaliacao.getActive(), avaliacaoByIndex.get().getActive()),
                () -> Assertions.assertEquals(avaliacao.getDisciplina().getId(), avaliacaoByIndex.get().getDisciplinaId()),
                () -> Assertions.assertEquals(avaliacao.getDisciplina().getName(), avaliacaoByIndex.get().getDisciplinaName())
        );

    }

    @Test
    public void testPostAvaliacao() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(id);
        mentoriaDTO.setAlunoId(id);
        mentoriaDTO.setAlunoName("t");
        mentoriaDTO.setMentorId(id);
        mentoriaDTO.setMentorName("tt");
        mentoriaDTO.setActive(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("teste");
        disciplina.setActive(true);

        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("teste");
        disciplinaDTO.setActive(true);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setMentoria(mentoria);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setNota(0.0);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setMentoriaId(id);
        avaliacaoDTO.setMentoriaMentorName("tt");
        avaliacaoDTO.setMentoriaAlunoName("t");
        avaliacaoDTO.setDisciplinaId(id);
        avaliacaoDTO.setDisciplinaName("teste");
        avaliacaoDTO.setNota(0.0);

        Mockito.when(mentoriaService.getMentoriaByIndex(id)).thenReturn(Optional.of(mentoriaDTO));
        Mockito.when(disciplinaService.getDisciplinaByIndex(id)).thenReturn(Optional.of(disciplinaDTO));
        Mockito.when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao)).thenReturn(avaliacaoDTO);
        Mockito.when(avaliacaoMapper.toAvaliacao(avaliacaoDTO)).thenReturn(avaliacao);


        Optional<AvaliacaoDTO> avaliacaoSalva = this.avaliacaoService.criaAvaliacao(avaliacaoDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(avaliacaoSalva.isPresent()),
                () -> Assertions.assertEquals(avaliacao.getId(), avaliacaoSalva.get().getId()),
                () -> Assertions.assertEquals(avaliacao.getMentoria().getId(), avaliacaoSalva.get().getMentoriaId()),
                () -> Assertions.assertEquals(avaliacao.getDisciplina().getId(), avaliacaoSalva.get().getDisciplinaId()),
                () -> Assertions.assertEquals(true, avaliacaoSalva.get().getActive()),
                () -> Assertions.assertFalse(avaliacao.getActive() == avaliacaoSalva.get().getActive())
        );

    }

    @Test
    public void testPutAvaliacao() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(id);
        mentoriaDTO.setAlunoId(id);
        mentoriaDTO.setAlunoName("t");
        mentoriaDTO.setMentorId(id);
        mentoriaDTO.setMentorName("tt");
        mentoriaDTO.setActive(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("teste");
        disciplina.setActive(true);

        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("teste");
        disciplinaDTO.setActive(true);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setMentoria(mentoria);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setActive(true);
        avaliacao.setNota(0.0);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setMentoriaId(id);
        avaliacaoDTO.setMentoriaMentorName("tt");
        avaliacaoDTO.setMentoriaAlunoName("t");
        avaliacaoDTO.setDisciplinaId(id);
        avaliacaoDTO.setDisciplinaName("teste");
        avaliacaoDTO.setActive(true);
        avaliacaoDTO.setNota(0.0);

        Mockito.when(mentoriaService.getMentoriaByIndex(id)).thenReturn(Optional.of(mentoriaDTO));
        Mockito.when(disciplinaService.getDisciplinaByIndex(id)).thenReturn(Optional.of(disciplinaDTO));
        Mockito.when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
        Mockito.when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao)).thenReturn(avaliacaoDTO);
        Mockito.when(avaliacaoMapper.toAvaliacao(avaliacaoDTO)).thenReturn(avaliacao);

        Optional<AvaliacaoDTO> avaliacaoSalva = this.avaliacaoService.criaAvaliacao(avaliacaoDTO);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setId(id);
        avaliacao2.setMentoria(mentoria);
        avaliacao2.setDisciplina(disciplina);
        avaliacao2.setActive(true);
        avaliacao2.setNota(8.0);

        AvaliacaoDTO avaliacaoDTO2 = new AvaliacaoDTO();
        avaliacaoDTO2.setId(id);
        avaliacaoDTO2.setMentoriaId(id);
        avaliacaoDTO2.setMentoriaMentorName("tt");
        avaliacaoDTO2.setMentoriaAlunoName("t");
        avaliacaoDTO2.setDisciplinaId(id);
        avaliacaoDTO2.setDisciplinaName("teste");
        avaliacaoDTO2.setActive(true);
        avaliacaoDTO2.setNota(8.0);

        Mockito.when(avaliacaoRepository.save(avaliacao2)).thenReturn(avaliacao2);
        Mockito.when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao2));
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao2)).thenReturn(avaliacaoDTO2);
        Mockito.when(avaliacaoMapper.toAvaliacao(avaliacaoDTO2)).thenReturn(avaliacao2);

        Optional<AvaliacaoDTO> avaliacaoAlterada = this.avaliacaoService.alteraAvaliacao(id, avaliacaoDTO2);

        Assertions.assertAll(
                () -> Assertions.assertTrue(avaliacaoAlterada.isPresent()),
                () -> Assertions.assertEquals(avaliacaoDTO.getId(), avaliacaoAlterada.get().getId()),
                () -> Assertions.assertEquals("tt", avaliacaoAlterada.get().getMentoriaMentorName()),
                () -> Assertions.assertNotEquals(avaliacaoSalva.get().getNota(), avaliacaoAlterada.get().getNota())
        );


    }


    @Test
    public void testDeleteAvaliacao() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("teste");
        disciplina.setActive(true);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setMentoria(mentoria);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setActive(true);
        avaliacao.setNota(0.0);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setMentoriaId(id);
        avaliacaoDTO.setMentoriaMentorName("tt");
        avaliacaoDTO.setMentoriaAlunoName("t");
        avaliacaoDTO.setDisciplinaId(id);
        avaliacaoDTO.setDisciplinaName("teste");
        avaliacaoDTO.setActive(true);
        avaliacaoDTO.setNota(0.0);

        Mockito.when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));
        Mockito.when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao)).thenReturn(avaliacaoDTO);

        Optional<AvaliacaoDTO> avaliacaoRemoval = this.avaliacaoService.removeAvaliacao(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(false, avaliacao.getActive())
        );

    }

    @Test
    public void testReativaAvaliacao() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("teste");
        disciplina.setActive(true);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacao.setMentoria(mentoria);
        avaliacao.setDisciplina(disciplina);
        avaliacao.setActive(false);
        avaliacao.setNota(0.0);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setMentoriaId(id);
        avaliacaoDTO.setMentoriaMentorName("tt");
        avaliacaoDTO.setMentoriaAlunoName("t");
        avaliacaoDTO.setDisciplinaId(id);
        avaliacaoDTO.setDisciplinaName("teste");
        avaliacaoDTO.setActive(false);
        avaliacaoDTO.setNota(0.0);

        Mockito.when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));
        Mockito.when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
        Mockito.when(avaliacaoMapper.toAvaliacaoDTO(avaliacao)).thenReturn(avaliacaoDTO);

        Optional<AvaliacaoDTO> avaliacaoReactive = this.avaliacaoService.reativaAvaliacao(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(true, avaliacao.getActive())
        );
    }
}
