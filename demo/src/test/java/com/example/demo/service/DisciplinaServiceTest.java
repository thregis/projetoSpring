package com.example.demo.service;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.mapper.DisciplinaMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        Long id = 1L;
        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(2L);
        disciplina2.setName("t2");
        disciplina2.setDescricao("testeteste2");
        disciplina2.setActive(true);

        Pageable pageable = PageRequest.of(0, 5);

        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        disciplinas.add(disciplina);
        disciplinas.add(disciplina2);

        Page<Disciplina> pageDisciplinas = new PageImpl<>(disciplinas);

        Mockito.when(disciplinaRepository.findByActive(true, pageable)).thenReturn(pageDisciplinas);
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);

        Optional<Page<DisciplinaDTO>> all = this.disciplinaService.getDisciplinas(pageable);

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().getSize() == 0),
                () -> Assertions.assertEquals(all, Optional.of(pageDisciplinas.map(disciplinaMapper::toDisciplinaDTO)))
        );

    }

    @Test
    public void testGetDisciplinasInativas() {

        Long id = 1L;
        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(false);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(false);

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(2L);
        disciplina2.setName("t2");
        disciplina2.setDescricao("testeteste2");
        disciplina2.setActive(false);

        Pageable pageable = PageRequest.of(0,5);

        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        disciplinas.add(disciplina);
        disciplinas.add(disciplina2);

        Page<Disciplina> pageDisciplinas = new PageImpl<>(disciplinas);

        Mockito.when(disciplinaRepository.findByActive(false, pageable)).thenReturn(pageDisciplinas);
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);

        Optional<Page<DisciplinaDTO>> all = this.disciplinaService.getDisciplinasInativas(pageable);

        Assertions.assertAll(
                () -> Assertions.assertTrue(all.isPresent()),
                () -> Assertions.assertFalse(all.get().getSize() == 0),
                () -> Assertions.assertEquals(all, Optional.of(pageDisciplinas.map(disciplinaMapper::toDisciplinaDTO)))
        );
    }

    @Test
    public void testGetDisciplinaById() {
        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
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

        Long id = 1L;
        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
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
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);

        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.of(disciplina));
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);
        Mockito.when(disciplinaMapper.toDisciplina(disciplinaDTO)).thenReturn(disciplina);


        Optional<DisciplinaDTO> disciplinaSalva = this.disciplinaService.criaDisciplina(disciplinaDTO);

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(id);
        disciplina2.setName("tt2");
        disciplina2.setDescricao("testeteste2");
        disciplina2.setActive(true);


        DisciplinaDTO disciplinaDTO2 = new DisciplinaDTO();
        disciplinaDTO2.setId(id);
        disciplinaDTO2.setName("tt2");
        disciplinaDTO2.setDescricao("testeteste2");
        disciplinaDTO2.setActive(true);

        Mockito.when(disciplinaRepository.save(disciplina2)).thenReturn(disciplina2);
        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.of(disciplina2));
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina2)).thenReturn(disciplinaDTO2);
        Mockito.when(disciplinaMapper.toDisciplina(disciplinaDTO2)).thenReturn(disciplina2);

        Optional<DisciplinaDTO> disciplinaAlterada = this.disciplinaService.alteraDisciplina(id, disciplinaDTO2);

        Assertions.assertAll(
                () -> Assertions.assertTrue(disciplinaAlterada.isPresent()),
                () -> Assertions.assertEquals(disciplinaDTO.getId(), disciplinaAlterada.get().getId()),
                () -> Assertions.assertEquals("tt2", disciplinaAlterada.get().getName()),
                () -> Assertions.assertNotEquals(disciplinaAlterada.get().getName(), disciplinaSalva.get().getName())
        );


    }


    @Test
    public void testDeleteDisciplina() {

        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
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
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(false);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
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

    // -------------------TESTES DE EXCEÇÕES-----------------------

    @Test
    public void testGetDisciplinaByIdNotFound() {
        Long id = 1L;

        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> disciplinaService.getDisciplinaByIndex(id));

    }

    @Test
    public void testPostDisciplinaComValoresInvalidos() {

        Long id = 1L;
        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("t");
        disciplina.setDescricao("testeteste");


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("t");
        disciplinaDTO.setDescricao("testeteste");

        Assertions.assertThrows(BadRequestException.class, () -> disciplinaService.criaDisciplina(disciplinaDTO));
    }

    @Test
    public void testPutDisciplinaNotFound() {
        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setName("tt");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);

        Assertions.assertThrows(NotFoundException.class, () -> disciplinaService.alteraDisciplina(id, disciplinaDTO));


    }

@Test
    public void testPutDisciplinaComValoresInvalidos() {
        Long id = 1L;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setName("tt");
        disciplina.setDescricao("testeteste");
        disciplina.setActive(true);


        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(id);
        disciplinaDTO.setName("tt");
        disciplinaDTO.setDescricao("testeteste");
        disciplinaDTO.setActive(true);

        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);
        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.of(disciplina));
        Mockito.when(disciplinaMapper.toDisciplinaDTO(disciplina)).thenReturn(disciplinaDTO);
        Mockito.when(disciplinaMapper.toDisciplina(disciplinaDTO)).thenReturn(disciplina);


        Optional<DisciplinaDTO> disciplinaSalva = this.disciplinaService.criaDisciplina(disciplinaDTO);

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(id);
        disciplina2.setName("t");
        disciplina2.setDescricao("testeteste2");
        disciplina2.setActive(true);


        DisciplinaDTO disciplinaDTO2 = new DisciplinaDTO();
        disciplinaDTO2.setId(id);
        disciplinaDTO2.setName("t");
        disciplinaDTO2.setDescricao("testeteste2");
        disciplinaDTO2.setActive(true);

        Assertions.assertThrows(BadRequestException.class, () -> disciplinaService.alteraDisciplina(id, disciplinaDTO2));


    }
    @Test
    public void testDeleteDisciplinaNotFound() {

        Long id = 1L;

        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> disciplinaService.removeDisciplina(id));


    }

    @Test
    public void testReativaDisciplinaNotFound() {

        Long id = 1L;

        Mockito.when(disciplinaRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> disciplinaService.reativaDisciplina(id));
    }
}
