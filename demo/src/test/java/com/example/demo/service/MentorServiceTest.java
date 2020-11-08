package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
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

        Long id = 1L;
        Integer idade = 20;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");

        Mentor mentor2 = new Mentor();
        mentor2.setId(2L);
        mentor2.setName("teste2");
        mentor2.setPais("teste2");
        mentor2.setIdade(idade);
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
        Long id = 1L;
        Integer idade = 20;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(false);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(false);
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");

        Mentor mentor2 = new Mentor();
        mentor2.setId(2L);
        mentor2.setName("teste2");
        mentor2.setPais("teste2");
        mentor2.setIdade(idade);
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
        Integer idade = 20;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");


        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        Optional<MentorDTO> mentorByIndex = this.mentorService.getMentorByIndex(id); //CHAMADA DO MÉTODO A TESTAR

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

        Long id = 1L;
        Integer idade = 20;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setProgramaId(id);
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
        Long id = 1L;
        Integer idade = 20;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");

        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
        Mockito.when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);
        Mockito.when(mentorMapper.toMentor(mentorDTO)).thenReturn(mentor);

        Optional<MentorDTO> mentorSalvo = this.mentorService.criaMentor(mentorDTO);

        Mentor mentor2 = new Mentor();
        mentor2.setId(id);
        mentor2.setName("teste2");
        mentor2.setPais("teste2");
        mentor2.setIdade(idade);
        mentor2.setEscola("testeteste2");
        mentor2.setActive(true);
        mentor2.setPrograma(programa);

        MentorDTO mentorDTO2 = new MentorDTO();
        mentorDTO2.setId(id);
        mentorDTO2.setName("teste2");
        mentorDTO2.setPais("teste2");
        mentorDTO2.setIdade(idade);
        mentorDTO2.setEscola("testeteste2");
        mentorDTO2.setActive(true);
        mentorDTO2.setProgramaId(id);
        mentorDTO2.setProgramaName("testeteste");

        Mockito.when(mentorRepository.save(mentor2)).thenReturn(mentor2);
        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor2));
        Mockito.when(mentorMapper.toMentorDTO(mentor2)).thenReturn(mentorDTO2);
        Mockito.when(mentorMapper.toMentor(mentorDTO2)).thenReturn(mentor2);

        Optional<MentorDTO> mentorAlterado = this.mentorService.alteraMentor(id, mentorDTO2);

        Assertions.assertAll(
                () -> Assertions.assertTrue(mentorAlterado.isPresent()),
                () -> Assertions.assertEquals(mentorDTO.getId(), mentorAlterado.get().getId()),
                () -> Assertions.assertEquals("teste2", mentorAlterado.get().getName()),
                () -> Assertions.assertEquals("teste2", mentorAlterado.get().getPais()),
                () -> Assertions.assertEquals("testeteste2", mentorAlterado.get().getEscola()),
                () -> Assertions.assertNotEquals(mentorAlterado.get().getName(), mentorSalvo.get().getName())
        );


    }


    @Test
    public void testDeleteMentor() {

        Long id = 1L;
        Integer idade = 20;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(id);
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
        Integer idade = 20;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(false);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(false);
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");

        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);

        Optional<MentorDTO> mentorReactive = this.mentorService.reativaMentor(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(true, mentor.getActive())
        );
    }

    // -------------------TESTES DE EXCEÇÕES-----------------------

    @Test
    public void testGetMentorByIdNotFound() {
        Long id = 1L;

        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentorService.getMentorByIndex(id));

    }

    @Test
    public void testPostMentorComValoresInvalidos() {

        Long id = 1L;
        Integer idade = 20;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("t");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("t");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");

        Assertions.assertThrows(BadRequestException.class, () -> mentorService.criaMentor(mentorDTO));

    }


    @Test
    public void testPutMentorNotFound() {
        Long id = 1L;
        Integer idade = 20;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");

        Assertions.assertThrows(NotFoundException.class, () -> mentorService.alteraMentor(id, mentorDTO));

    }

    @Test
    public void testPutMentorDadosInvalidos(){
        Long id = 1L;
        Integer idade = 20;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Mentor mentor = new Mentor();
        mentor.setId(id);
        mentor.setName("teste");
        mentor.setPais("teste");
        mentor.setIdade(idade);
        mentor.setEscola("testeteste");
        mentor.setActive(true);
        mentor.setPrograma(programa);

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(id);
        mentorDTO.setName("teste");
        mentorDTO.setPais("teste");
        mentorDTO.setIdade(idade);
        mentorDTO.setEscola("testeteste");
        mentorDTO.setActive(true);
        mentorDTO.setProgramaId(id);
        mentorDTO.setProgramaName("testeteste");

        Mockito.when(mentorRepository.save(mentor)).thenReturn(mentor);
        Mockito.when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));
        Mockito.when(mentorMapper.toMentorDTO(mentor)).thenReturn(mentorDTO);
        Mockito.when(mentorMapper.toMentor(mentorDTO)).thenReturn(mentor);

        Optional<MentorDTO> mentorSalvo = this.mentorService.criaMentor(mentorDTO);

        Mentor mentor2 = new Mentor();
        mentor2.setId(id);
        mentor2.setName("t");
        mentor2.setPais("teste2");
        mentor2.setIdade(idade);
        mentor2.setEscola("testeteste2");
        mentor2.setActive(true);
        mentor2.setPrograma(programa);

        MentorDTO mentorDTO2 = new MentorDTO();
        mentorDTO2.setId(id);
        mentorDTO2.setName("t");
        mentorDTO2.setPais("teste2");
        mentorDTO2.setIdade(idade);
        mentorDTO2.setEscola("testeteste2");
        mentorDTO2.setActive(true);
        mentorDTO2.setProgramaId(id);
        mentorDTO2.setProgramaName("testeteste");

        Assertions.assertThrows(BadRequestException.class, () -> mentorService.alteraMentor(id, mentorDTO2));

    }

    @Test
    public void testDeleteMentorNotFound() {

        Long id = 1L;

        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentorService.removeMentorByIndex(id));
    }

    @Test
    public void testReativaMentorNotFound() {

        Long id = 1L;

        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> mentorService.reativaMentor(id));
    }
}
