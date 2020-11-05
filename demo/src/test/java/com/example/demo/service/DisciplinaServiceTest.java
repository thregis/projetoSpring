package com.example.demo.service;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.mapper.DisciplinaMapper;
import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
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
public class DisciplinaServiceTest {

    @Mock
    DisciplinaRepository disciplinaRepository;

    @InjectMocks
    DisciplinaService disciplinaService;

    @Mock
    DisciplinaMapper disciplinaMapper;


    @Test
    public void testGetDisciplinasAtivas() {

        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(1L);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(2L);
        disciplina2.setName("t2");
        disciplina2.setDescricao("testeteste2");
        disciplina2.setActive(true);

        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        disciplinas.add(disciplina);
        disciplinas.add(disciplina2);

        Mockito.when(disciplinaRepository.findByActive(true)).thenReturn(disciplinas);
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);

        Optional<List<DisciplinaDTO>> all = this.disciplinaService.getDisciplinas();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(disciplinas.stream().map(disciplinaMapper::toDisciplinaDTO).collect(Collectors.toList())))
        );

    }

    @Test
    public void testGetDisciplinasInativas() {

        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(false);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(1L);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(false);

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(2L);
        disciplina2.setName("t2");
        disciplina2.setDescricao("testeteste2");
        disciplina2.setActive(false);


        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        disciplinas.add(disciplina);
        disciplinas.add(disciplina2);

        Mockito.when(disciplinaRepository.findByActive(false)).thenReturn(disciplinas);
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);

        Optional<List<DisciplinaDTO>> all = this.disciplinaService.getDisciplinasInativas();

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().size() == 0),
                () -> Assertions.assertEquals(all, Optional.of(disciplinas.stream().map(disciplinaMapper::toDisciplinaDTO).collect(Collectors.toList())))
        );
    }

    @Test
    public void testGetDisciplinaById() {
        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);



        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.of(disciplina));
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);

        Optional<DisciplinaDTO> disciplinaByIndex = this.disciplinaService.getDisciplinaByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        assertAll( //alt+enter static import
                () -> Assertions.assertTrue(disciplinaByIndex.isPresent()),
                () -> Assertions.assertEquals(disciplina.getName(), disciplinaByIndex.get().getName()),
                () -> Assertions.assertEquals(disciplina.getDescricao(), disciplinaByIndex.get().getDescricao()),
                () -> Assertions.assertEquals(disciplina.getId(), disciplinaByIndex.get().getId()),
                () -> Assertions.assertEquals(disciplina.getActive(), disciplinaByIndex.get().getActive())
        );

    }

    @Test
    public void testPostDisciplina() {

        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(1L);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");



        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);
        Mockito.when(disciplinaMapper.toDisciplina(disciplinaDTO)).thenReturn(disciplina);


        Optional<DisciplinaDTO> disciplinaSalva = this.disciplinaService.criaDisciplina(disciplinaDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(disciplinaSalva.isPresent()),
                () -> Assertions.assertEquals(disciplina.getName(), disciplinaSalva.get().getName()),
                () -> Assertions.assertEquals(disciplina.getDescricao(), disciplinaSalva.get().getDescricao()),
                () -> Assertions.assertEquals(disciplina.getId(), disciplinaSalva.get().getId()),
                () -> Assertions.assertEquals(true, disciplinaSalva.get().getActive()),
                () -> Assertions.assertFalse(disciplina.getActive() == disciplinaSalva.get().getActive())
        );

    }


    @Test
    public void testPutDisciplina() {
        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);

        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.of(disciplina));
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);
        Mockito.when(disciplinaMapper.toDisciplina(disciplinaDTO)).thenReturn(disciplina);


        Optional<DisciplinaDTO> disciplinaAlterada = this.disciplinaService.alteraDisciplina(id, disciplinaDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(disciplinaAlterada.isPresent()),
                () -> Assertions.assertEquals(disciplinaDTO.getId(), disciplinaAlterada.get().getId())
        );


    }


    @Test
    public void testDeleteDisciplina() {

        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);


        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.of(disciplina));
        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);

        Optional<DisciplinaDTO> disciplinaRemoval = this.disciplinaService.removeDisciplina(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(false, disciplina.getActive())
        );


    }

    @Test
    public void testReativaDisciplina() {

        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(false);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(false);


        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.of(disciplina));
        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);

        Optional<DisciplinaDTO> mentorReactive = this.disciplinaService.reativaDisciplina(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(true, disciplina.getActive())
        );
    }
}
