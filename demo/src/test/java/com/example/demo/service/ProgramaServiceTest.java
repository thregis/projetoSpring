package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.repository.ProgramaRepository;
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
public class ProgramaServiceTest {

    @Mock
    ProgramaRepository programaRepository;

    @InjectMocks
    ProgramaService programaService;

    @Mock
    ProgramaMapper programaMapper;


    @Test
    public void testGetProgramasAtivos() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");
        programa.setActive(true);

        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("testeteste");
        programaDTO.setActive(true);

        Programa programa2 = new Programa();
        programa2.setId(2L);
        programa2.setName("t2");
        programa2.setActive(true);

        List<Programa> programas = new ArrayList<Programa>();
        programas.add(programa);
        programas.add(programa2);

        Mockito.when(programaRepository.findByActive(true)).thenReturn(programas);
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);

        Optional<List<ProgramaDTO>> all = this.programaService.getProgramas();

        Assertions.assertTrue(all.isPresent());
        Assertions.assertFalse(all.get().size() == 0);
        Assertions.assertEquals(all, Optional.of(programas.stream().map(programaMapper::toProgramaDTO).collect(Collectors.toList())));

    }

    @Test
    public void testGetProgramasInativos() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");
        programa.setActive(false);

        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("testeteste");
        programaDTO.setActive(false);

        Programa programa2 = new Programa();
        programa2.setId(2L);
        programa2.setName("t2");
        programa2.setActive(false);

        List<Programa> programas = new ArrayList<Programa>();
        programas.add(programa);
        programas.add(programa2);

        Mockito.when(programaRepository.findByActive(false)).thenReturn(programas);
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);

        Optional<List<ProgramaDTO>> all = this.programaService.getProgramasInativos();

        Assertions.assertTrue(all.isPresent());
        Assertions.assertFalse(all.get().size() == 0);
        Assertions.assertEquals(all, Optional.of(programas.stream().map(programaMapper::toProgramaDTO).collect(Collectors.toList())));
    }

    @Test
    public void testGetProgramaById() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");
        programa.setActive(true);

        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("testeteste");
        programaDTO.setActive(true);


        Mockito.when(programaRepository.findById(id)).thenReturn(Optional.of(programa));
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);

        Optional<ProgramaDTO> programaByIndex = this.programaService.getProgramaByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertTrue(programaByIndex.isPresent());

        assertAll( //alt+enter static import
                () -> Assertions.assertTrue(programaByIndex.isPresent()),
                () -> Assertions.assertEquals(programa.getName(), programaByIndex.get().getName()),
                () -> Assertions.assertEquals(programa.getId(), programaByIndex.get().getId()),
                () -> Assertions.assertEquals(programa.getActive(), programaByIndex.get().getActive())
        );
    }

    @Test
    public void testPostPrograma() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("testeteste");

        Mockito.when(programaRepository.save(programa)).thenReturn(programa);
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);
        Mockito.when(programaMapper.toPrograma(programaDTO)).thenReturn(programa);

        Optional<ProgramaDTO> programaSalvo = this.programaService.criaPrograma(programaDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(programaSalvo.isPresent()),
                () -> Assertions.assertEquals(programa.getId(), programaSalvo.get().getId()),
                () -> Assertions.assertEquals(programa.getName(), programaSalvo.get().getName()),
                () -> Assertions.assertEquals(true, programaSalvo.get().getActive()),
                () -> Assertions.assertFalse(programa.getActive() == programaSalvo.get().getActive())
        );

    }


    @Test
    public void testPutPrograma() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");
        programa.setActive(true);

        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("testeteste");
        programaDTO.setActive(true);


        Mockito.when(programaRepository.save(programa)).thenReturn(programa);
        Mockito.when(programaRepository.findById(id)).thenReturn(Optional.of(programa));
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);
        Mockito.when(programaMapper.toPrograma(programaDTO)).thenReturn(programa);

        Optional<ProgramaDTO> programaAlterado = this.programaService.alteraPrograma(id, programaDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(programaAlterado.isPresent()),
                () -> Assertions.assertEquals(programaDTO.getId(), programaAlterado.get().getId())
        );


    }


    @Test
    public void testDeletePrograma() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");
        programa.setActive(true);

        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("testeteste");
        programaDTO.setActive(true);

        Mockito.when(programaRepository.findById(id)).thenReturn(Optional.of(programa));
        Mockito.when(programaRepository.save(programa)).thenReturn(programa);
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);

        Optional<ProgramaDTO> programaRemoval = this.programaService.removePrograma(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(false, programa.getActive())
        );


    }

    @Test
    public void testReativaPrograma() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");
        programa.setActive(false);

        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("testeteste");
        programaDTO.setActive(false);

        Mockito.when(programaRepository.findById(id)).thenReturn(Optional.of(programa));
        Mockito.when(programaRepository.save(programa)).thenReturn(programa);
        Mockito.when(programaMapper.toProgramaDTO(programa)).thenReturn(programaDTO);

        Optional<ProgramaDTO> programaReactive = this.programaService.reativaPrograma(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(true, programa.getActive())
        );
    }
}
