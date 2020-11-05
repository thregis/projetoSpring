package com.example.demo.service;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;
import com.example.demo.repository.MentoriaRepository;
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
public class MentoriaServiceTest {

    @Mock
    MentoriaRepository mentoriaRepository;

    @InjectMocks
    MentoriaService mentoriaService;

    @Mock
    AvaliacaoService avaliacaoService;

    @Mock
    MentoriaMapper mentoriaMapper;


    @Test
    public void testGetMentoriasAtivas() {

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
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setAlunoName(aluno.getName());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setMentorName(mentor.getName());
        mentoriaDTO.setActive(true);

        Mentoria mentoria2 = new Mentoria();
        mentoria2.setId(2L);
        mentoria2.setAluno(aluno);
        mentoria2.setMentor(mentor);
        mentoria2.setActive(true);

        List<Mentoria> mentorias = new ArrayList<Mentoria>();
        mentorias.add(mentoria);
        mentorias.add(mentoria2);

        Mockito.when(mentoriaRepository.findByActive(true)).thenReturn(mentorias);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        Optional<List<MentoriaDTO>> all = this.mentoriaService.getMentorias();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(mentorias.stream().map(mentoriaMapper::toMentoriaDTO).collect(Collectors.toList())))
        );

    }

    @Test
    public void testGetMentoriasInativas() {
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
        mentoria.setActive(false);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(id);
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setAlunoName(aluno.getName());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setMentorName(mentor.getName());
        mentoriaDTO.setActive(false);

        Mentoria mentoria2 = new Mentoria();
        mentoria2.setId(2L);
        mentoria2.setAluno(aluno);
        mentoria2.setMentor(mentor);
        mentoria2.setActive(false);

        List<Mentoria> mentorias = new ArrayList<Mentoria>();
        mentorias.add(mentoria);
        mentorias.add(mentoria2);


        Mockito.when(mentoriaRepository.findByActive(false)).thenReturn(mentorias);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        Optional<List<MentoriaDTO>> all = this.mentoriaService.getMentoriasInativas();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(mentorias.stream().map(mentoriaMapper::toMentoriaDTO).collect(Collectors.toList())))
        );
    }

    @Test
    public void testGetMentoriaById() {

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
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setAlunoName(aluno.getName());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setMentorName(mentor.getName());
        mentoriaDTO.setActive(true);


        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        Optional<MentoriaDTO> mentoriaByIndex = this.mentoriaService.getMentoriaByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertTrue(mentoriaByIndex.isPresent());

        assertAll( //alt+enter static import
                () -> Assertions.assertTrue(mentoriaByIndex.isPresent()),
                () -> Assertions.assertEquals(mentoria.getId(), mentoriaByIndex.get().getId()),
                () -> Assertions.assertEquals(mentoria.getAluno().getId(), mentoriaByIndex.get().getAlunoId()),
                () -> Assertions.assertEquals(mentoria.getAluno().getName(), mentoriaByIndex.get().getAlunoName()),
                () -> Assertions.assertEquals(mentoria.getActive(), mentoriaByIndex.get().getActive()),
                () -> Assertions.assertEquals(mentoria.getMentor().getId(), mentoriaByIndex.get().getMentorId()),
                () -> Assertions.assertEquals(mentoria.getMentor().getName(), mentoriaByIndex.get().getMentorName())
        );

    }

    @Test
    public void testPostMentoria() {

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

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(id);
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setAlunoName(aluno.getName());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setMentorName(mentor.getName());


        Mockito.when(mentoriaRepository.save(mentoria)).thenReturn(mentoria);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);
        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);


        Optional<MentoriaDTO> mentoriaSalva = this.mentoriaService.criaMentoria(mentoriaDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(mentoriaSalva.isPresent()),
                () -> Assertions.assertEquals(mentoria.getId(), mentoriaSalva.get().getId()),
                () -> Assertions.assertEquals(mentoria.getAluno().getId(), mentoriaSalva.get().getAlunoId()),
                () -> Assertions.assertEquals(mentoria.getAluno().getName(), mentoriaSalva.get().getAlunoName()),
                () -> Assertions.assertEquals(mentoria.getMentor().getId(), mentoriaSalva.get().getMentorId()),
                () -> Assertions.assertEquals(true, mentoriaSalva.get().getActive()),
                () -> Assertions.assertFalse(mentoria.getActive() == mentoriaSalva.get().getActive())
        );

    }


    @Test
    public void testPutMentoria() {

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
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setAlunoName(aluno.getName());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setMentorName(mentor.getName());
        mentoriaDTO.setActive(true);


        Mockito.when(mentoriaRepository.save(mentoria)).thenReturn(mentoria);
        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);
        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);

        Assertions.assertEquals("t", mentoriaRepository.findById(id).get().getAluno().getName());

        Optional<MentoriaDTO> mentoriaAlterada = this.mentoriaService.alteraMentoria(id, mentoriaDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(mentoriaAlterada.isPresent()),
                () -> Assertions.assertEquals(mentoriaDTO.getId(), mentoriaAlterada.get().getId())
        );


    }


    @Test
    public void testDeleteMentoria() {

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
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setAlunoName(aluno.getName());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setMentorName(mentor.getName());
        mentoriaDTO.setActive(true);

        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));
        Mockito.when(mentoriaRepository.save(mentoria)).thenReturn(mentoria);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);
        Mockito.doNothing().when(avaliacaoService).setActiveByMentoria(false, id);

        Optional<MentoriaDTO> mentoriaRemoval = this.mentoriaService.removeMentoria(id); //CHAMADA DO MÉTODO A TESTAR

        Mockito.verify(avaliacaoService, Mockito.times(1)).setActiveByMentoria(false, id);

        Assertions.assertAll(
                () -> Assertions.assertEquals(false, mentoria.getActive())
        );

    }

    @Test
    public void testReativaMentoria() {

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
        mentoria.setActive(false);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(id);
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setAlunoName(aluno.getName());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setMentorName(mentor.getName());
        mentoriaDTO.setActive(false);

        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));
        Mockito.when(mentoriaRepository.save(mentoria)).thenReturn(mentoria);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        Optional<MentoriaDTO> mentoriaReactive = this.mentoriaService.reativaMentoria(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(true, mentoria.getActive())
        );
    }
}
