package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Mock
    AlunoService alunoService;
    @Mock
    MentorService mentorService;


    @Test
    public void testGetMentoriasAtivas() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("teste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");

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

        Pageable pageable = PageRequest.of(0, 5);

        List<Mentoria> mentorias = new ArrayList<Mentoria>();
        mentorias.add(mentoria);
        mentorias.add(mentoria2);

        Page<Mentoria> pageMentorias = new PageImpl<>(mentorias);

        Mockito.when(mentoriaRepository.findByActive(true, pageable)).thenReturn(pageMentorias);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        Optional<Page<MentoriaDTO>> all = this.mentoriaService.getMentorias(pageable);

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().getSize() == 0),
                () -> Assertions.assertEquals(all, Optional.of(pageMentorias.map(mentoriaMapper::toMentoriaDTO)))
        );

    }

    @Test
    public void testGetMentoriasAtivasList() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("teste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");

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

        Mockito.when(mentoriaRepository.findListByActive(true)).thenReturn(mentorias);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        Optional<List<MentoriaDTO>> all = this.mentoriaService.getMentoriasList();

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

        Pageable pageable = PageRequest.of(0, 5);

        List<Mentoria> mentorias = new ArrayList<Mentoria>();
        mentorias.add(mentoria);
        mentorias.add(mentoria2);

        Page<Mentoria> pageMentorias = new PageImpl<>(mentorias);

        Mockito.when(mentoriaRepository.findByActive(false, pageable)).thenReturn(pageMentorias);
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);

        Optional<Page<MentoriaDTO>> all = this.mentoriaService.getMentoriasInativas(pageable);

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().getSize() == 0),
                () -> Assertions.assertEquals(all, Optional.of(pageMentorias.map(mentoriaMapper::toMentoriaDTO)))
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

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);

        Mentor mentor = new Mentor();
        mentor.setId(id);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);

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

        Mockito.when(alunoService.getAlunoByIndex(id)).thenReturn(Optional.of(alunoDTO));
        Mockito.when(mentorService.getMentorByIndex(id)).thenReturn(Optional.of(mentorDTO));
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
        Long id2 = 2L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("tt");

        Aluno aluno2 = new Aluno();
        aluno2.setId(id2);
        aluno2.setName("t2");
        AlunoDTO alunoDTO2 = new AlunoDTO();
        alunoDTO2.setId(id2);
        alunoDTO2.setName("t2");

        Mentor mentor2 = new Mentor();
        mentor2.setId(id2);
        mentor2.setName("tt2");
        MentorDTO mentorDTO2 = new MentorDTO();
        mentorDTO2.setId(id2);
        mentorDTO2.setName("tt2");

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


        Mockito.when(alunoService.getAlunoByIndex(id)).thenReturn(Optional.of(alunoDTO));
        Mockito.when(mentorService.getMentorByIndex(id)).thenReturn(Optional.of(mentorDTO));
        Mockito.when(alunoService.getAlunoByIndex(id2)).thenReturn(Optional.of(alunoDTO2));
        Mockito.when(mentorService.getMentorByIndex(id2)).thenReturn(Optional.of(mentorDTO2));
        Mockito.when(mentoriaRepository.save(mentoria)).thenReturn(mentoria);
        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);
        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);

        Optional<MentoriaDTO> mentoriaSalva = this.mentoriaService.criaMentoria(mentoriaDTO);

        Mentoria mentoria2 = new Mentoria();
        mentoria2.setId(id);
        mentoria2.setAluno(aluno2);
        mentoria2.setMentor(mentor2);
        mentoria2.setActive(true);

        MentoriaDTO mentoriaDTO2 = new MentoriaDTO();
        mentoriaDTO2.setId(id);
        mentoriaDTO2.setAlunoId(aluno2.getId());
        mentoriaDTO2.setAlunoName(aluno2.getName());
        mentoriaDTO2.setMentorId(mentor2.getId());
        mentoriaDTO2.setMentorName(mentor2.getName());
        mentoriaDTO2.setActive(true);

        Mockito.when(mentoriaRepository.save(mentoria2)).thenReturn(mentoria2);
        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria2));
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria2)).thenReturn(mentoriaDTO2);
        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO2)).thenReturn(mentoria2);

        Optional<MentoriaDTO> mentoriaAlterada = this.mentoriaService.alteraMentoria(id, mentoriaDTO2);

        Assertions.assertAll(
                () -> Assertions.assertTrue(mentoriaAlterada.isPresent()),
                () -> Assertions.assertEquals(mentoriaSalva.get().getId(), mentoriaAlterada.get().getId()),
                () -> Assertions.assertEquals("tt2", mentoriaAlterada.get().getMentorName()),
                () -> Assertions.assertEquals("t2", mentoriaAlterada.get().getAlunoName()),
                () -> Assertions.assertNotEquals(mentoriaSalva.get().getAlunoId(), mentoriaAlterada.get().getAlunoId()),
                () -> Assertions.assertNotEquals(mentoriaSalva.get().getMentorId(), mentoriaAlterada.get().getMentorId())
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
        aluno.setActive(true);

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");
        mentor.setActive(true);

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

    // -------------------TESTES DE EXCEÇÕES-----------------------


    @Test
    public void testGetMentoriaByIdNotFound() {

        Long id = 1L;

        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentoriaService.getMentoriaByIndex(id));

    }

    @Test
    public void testPostMentoriaComValoresInválidos() {

        Long id = 1L;

        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(id);

        Assertions.assertThrows(BadRequestException.class, () -> mentoriaService.criaMentoria(mentoriaDTO));

    }

    @Test
    public void testPutMentoriaNotFound() {

        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);

        Mentor mentor = new Mentor();
        mentor.setId(id);


        Mentoria mentoria = new Mentoria();
        mentoria.setId(id);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);
        mentoria.setActive(true);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(id);
        mentoriaDTO.setAlunoId(aluno.getId());
        mentoriaDTO.setMentorId(mentor.getId());
        mentoriaDTO.setActive(true);

        Assertions.assertThrows(NotFoundException.class, () -> mentoriaService.alteraMentoria(id, mentoriaDTO));


    }

    @Test
    public void testPutMentoriaComValoresInvalidos() {

        Long id = 1L;
        Long id2 = 2L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("t");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("tt");
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("tt");

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

        Aluno aluno2 = new Aluno();
        aluno2.setId(id2);

        Mockito.when(alunoService.getAlunoByIndex(id)).thenReturn(Optional.of(alunoDTO));
        Mockito.when(mentorService.getMentorByIndex(id)).thenReturn(Optional.of(mentorDTO));
        Mockito.when(mentoriaRepository.save(mentoria)).thenReturn(mentoria);
        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));
        Mockito.when(mentoriaMapper.toMentoriaDTO(mentoria)).thenReturn(mentoriaDTO);
        Mockito.when(mentoriaMapper.toMentoria(mentoriaDTO)).thenReturn(mentoria);

        Optional<MentoriaDTO> mentoriaSalva = this.mentoriaService.criaMentoria(mentoriaDTO);

        Mentoria mentoria2 = new Mentoria();
        mentoria2.setId(id);
        mentoria2.setMentor(mentor);
        mentoria2.setAluno(aluno2);
        mentoria2.setActive(true);

        MentoriaDTO mentoriaDTO2 = new MentoriaDTO();
        mentoriaDTO2.setId(id);
        mentoriaDTO2.setMentorId(mentor.getId());
        mentoriaDTO2.setMentorName(mentor.getName());
        mentoriaDTO2.setAlunoId(aluno2.getId());
        mentoriaDTO2.setActive(true);

        Assertions.assertThrows(BadRequestException.class, () -> mentoriaService.alteraMentoria(id, mentoriaDTO2));


    }

    @Test
    public void testDeleteMentoriaNotFound() {

        Long id = 1L;

        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentoriaService.removeMentoria(id));

    }

    @Test
    public void testReativaMentoriaNotFound() {

        Long id = 1L;

        Mockito.when(mentoriaRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentoriaService.reativaMentoria(id));

    }
}
