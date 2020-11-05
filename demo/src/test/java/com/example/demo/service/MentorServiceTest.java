package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.model.Mentor;
import com.example.demo.model.Programa;
import com.example.demo.repository.MentorRepository;
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
public class MentorServiceTest {
    @Mock
    MentorRepository mentorRepository;

    @InjectMocks
    MentorService mentorService;

    @Mock
    MentoriaService mentoriaService;

    @Mock
    MentorMapper mentorMapper;


    @Test
    public void testGetMentoresAtivos() {

        Programa programa = new Programa();
        programa.setId(1L);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(1);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(1);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(1L);
        mentorDTO.setProgramaName("testeteste");

        Mentor mentor2 = new Mentor();
        mentor2.setId(2L);
        mentor2.setName("te");
        mentor2.setPais("testee");
        mentor2.setIdade(2);
        mentor2.setEscola("testeteste");
        mentor2.setActive(true);
        mentor2.setPrograma(programa);

        List<Mentor> mentores = new ArrayList<Mentor>();
        mentores.add(mentor);
        mentores.add(mentor2);

        Mockito.when(mentorRepository.findByActive(true)).thenReturn(mentores);
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        Optional<List<MentorDTO>> all = this.mentorService.getMentores();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(mentores.stream().map(mentorMapper::toMentorDTO).collect(Collectors.toList())))
        );

    }

    @Test
    public void testGetMentoresInativos() {
        Programa programa = new Programa();
        programa.setId(1L);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(1);
        mentor.setEscola("testeteste");
        mentor.setActive(false);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(1);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(false);
        mentorDTO.setProgramaId(1L);
        mentorDTO.setProgramaName("testeteste");

        Mentor mentor2 = new Mentor();
        mentor2.setId(2L);
        mentor2.setName("te");
        mentor2.setPais("testee");
        mentor2.setIdade(2);
        mentor2.setEscola("testeteste");
        mentor2.setActive(false);
        mentor2.setPrograma(programa);


        List<Mentor> mentores = new ArrayList<Mentor>();
        mentores.add(mentor);
        mentores.add(mentor2);

        Mockito.when(mentorRepository.findByActive(false)).thenReturn(mentores);
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        Optional<List<MentorDTO>> all = this.mentorService.getMentoresInativos();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(mentores.stream().map(mentorMapper::toMentorDTO).collect(Collectors.toList())))
                );
    }

    @Test
    public void testGetMentorById() {
        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(2L);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(1);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(1);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(2L);
        mentorDTO.setProgramaName("testeteste");


        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        Optional<MentorDTO> mentorByIndex = this.mentorService.getMentorByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertTrue(mentorByIndex.isPresent());

        assertAll( //alt+enter static import
                () -> Assertions.assertTrue(mentorByIndex.isPresent()),
                () -> Assertions.assertEquals(mentor.getName(), mentorByIndex.get().getName()),
                () -> Assertions.assertEquals(mentor.getEscola(), mentorByIndex.get().getEscola()),
                () -> Assertions.assertEquals(mentor.getId(), mentorByIndex.get().getId()),
                () -> Assertions.assertEquals(mentor.getActive(), mentorByIndex.get().getActive()),
                () -> Assertions.assertEquals(mentor.getIdade(), mentorByIndex.get().getIdade()),
                () -> Assertions.assertEquals(mentor.getPais(), mentorByIndex.get().getPais()),
                () -> Assertions.assertEquals(mentor.getPrograma().getId(), mentorByIndex.get().getProgramaId()),
                () -> Assertions.assertEquals(mentor.getPrograma().getName(), mentorByIndex.get().getProgramaName())
        );

    }

    @Test
    public void testPostMentor() {

        Programa programa = new Programa();
        programa.setId(1L);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(1);
        mentor.setEscola("testeteste");
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(1);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setProgramaId(1L);
        mentorDTO.setProgramaName("testeteste");


        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);
        Mockito.when(mentorMapper.toMentor(mentorDTO)).thenReturn(mentor);


        Optional<MentorDTO> mentorSalvo = this.mentorService.criaMentor(mentorDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(mentorSalvo.isPresent()),
                () -> Assertions.assertEquals(mentor.getName(), mentorSalvo.get().getName()),
                () -> Assertions.assertEquals(mentor.getEscola(), mentorSalvo.get().getEscola()),
                () -> Assertions.assertEquals(mentor.getId(), mentorSalvo.get().getId()),
                () -> Assertions.assertEquals(mentor.getIdade(), mentorSalvo.get().getIdade()),
                () -> Assertions.assertEquals(mentor.getPais(), mentorSalvo.get().getPais()),
                () -> Assertions.assertEquals(mentor.getPrograma().getId(), mentorSalvo.get().getProgramaId()),
                () -> Assertions.assertEquals(mentor.getPrograma().getName(), mentorSalvo.get().getProgramaName()),
                () -> Assertions.assertEquals(true, mentorSalvo.get().getActive()),
                () -> Assertions.assertFalse(mentor.getActive() == mentorSalvo.get().getActive())
        );

    }


    @Test
    public void testPutMentor() {
        Programa programa = new Programa();
        programa.setId(1L);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(1);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(1);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(1L);
        mentorDTO.setProgramaName("testeteste");


        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
        Mockito.when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);
        Mockito.when(mentorMapper.toMentor(mentorDTO)).thenReturn(mentor);

        Assertions.assertEquals("t", mentorRepository.findById(1L).get().getName());

        Optional<MentorDTO> mentorAlterado = this.mentorService.alteraMentor(1L, mentorDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(mentorAlterado.isPresent()),
                () -> Assertions.assertEquals(mentorDTO.getId(), mentorAlterado.get().getId())
        );


    }


    @Test
    public void testDeleteMentor() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(2L);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(1);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(1);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(1L);
        mentorDTO.setProgramaName("testeteste");

        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);
        Mockito.doNothing().when(mentoriaService).setActiveByMentor(false, id);

        Optional<MentorDTO> mentorRemoval = this.mentorService.removeMentorByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        Mockito.verify(mentoriaService, Mockito.times(1)).setActiveByMentor(false, id);

        Assertions.assertAll(
                () -> Assertions.assertEquals(false, mentor.getActive())
        );


    }

    @Test
    public void testReativaMentor() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(2L);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(1);
        mentor.setEscola("testeteste");
        mentor.setActive(false);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(1);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(false);
        mentorDTO.setProgramaId(1L);
        mentorDTO.setProgramaName("testeteste");

        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        Optional<MentorDTO> mentorReactive = this.mentorService.reativaMentor(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(true, mentor.getActive())
        );
    }
}
