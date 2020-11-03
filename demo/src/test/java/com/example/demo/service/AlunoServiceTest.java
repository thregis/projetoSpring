package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;
import com.example.demo.repository.AlunoRepository;
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
public class AlunoServiceTest {

    @Mock
    AlunoRepository alunoRepository;

    @InjectMocks
    AlunoService alunoService;

    @Mock
    AlunoMapper alunoMapper;


    @Test
    public void testGetAlunosAtivos() {

        Programa programa = new Programa();
        programa.setId(1L);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);
        alunoDTO.setName("t");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(true);
        alunoDTO.setProgramaId(1L);
        alunoDTO.setProgramaName("testeteste");

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);
        aluno2.setName("t2");
        aluno2.setActive(true);

        Aluno aluno3 = new Aluno();
        aluno3.setId(3L);
        aluno3.setName("t3");
        aluno3.setActive(false);

        List<Aluno> alunos = new ArrayList<Aluno>();
        alunos.add(aluno);
        alunos.add(aluno2);

        Mockito.when(alunoRepository.findByActive(true)).thenReturn(alunos);
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);

        Optional<List<AlunoDTO>> all = this.alunoService.getAlunos();

        Assertions.assertTrue(all.isPresent());
        Assertions.assertFalse(all.get().size() == 0);
        Assertions.assertEquals(all, Optional.of(alunos.stream().map(alunoMapper::toAlunoDTO).collect(Collectors.toList())));

    }

    @Test
    public void testGetAlunosInativos() {
        Programa programa = new Programa();
        programa.setId(1L);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setActive(false);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);
        alunoDTO.setName("t");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(false);
        alunoDTO.setProgramaId(1L);
        alunoDTO.setProgramaName("testeteste");

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);
        aluno2.setName("t2");
        aluno2.setActive(false);

        Aluno aluno3 = new Aluno();
        aluno3.setId(3L);
        aluno3.setName("t3");
        aluno3.setActive(true);

        List<Aluno> alunos = new ArrayList<Aluno>();
        alunos.add(aluno);
        alunos.add(aluno2);

        Mockito.when(alunoRepository.findByActive(false)).thenReturn(alunos);
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);

        Optional<List<AlunoDTO>> all = this.alunoService.getAlunosInativos();

        Assertions.assertTrue(all.isPresent());
        Assertions.assertFalse(all.get().size() == 0);
        Assertions.assertEquals(all, Optional.of(alunos.stream().map(alunoMapper::toAlunoDTO).collect(Collectors.toList())));
    }

    @Test
    public void testGetAlunoById() {
        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(2L);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setId(1L);
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setName("t");
        alunoDTO.setClasse("teste");
        alunoDTO.setId(1L);
        alunoDTO.setActive(true);
        alunoDTO.setProgramaName("testeteste");
        alunoDTO.setProgramaId(2L);


        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);

        Optional<AlunoDTO> alunoByIndex = this.alunoService.getAlunoByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertTrue(alunoByIndex.isPresent());

        assertAll( //alt+enter static import
                () -> Assertions.assertTrue(alunoByIndex.isPresent()),
                () -> Assertions.assertEquals(aluno.getName(), alunoByIndex.get().getName()),
                () -> Assertions.assertEquals(aluno.getClasse(), alunoByIndex.get().getClasse()),
                () -> Assertions.assertEquals(aluno.getId(), alunoByIndex.get().getId()),
                () -> Assertions.assertEquals(aluno.getActive(), alunoByIndex.get().getActive()),
                () -> Assertions.assertEquals(aluno.getPrograma().getId(), alunoByIndex.get().getProgramaId()),
                () -> Assertions.assertEquals(aluno.getPrograma().getName(), alunoByIndex.get().getProgramaName())
        );
//        Assertions.assertEquals("t", alunoDTO.getName());
//        Assertions.assertEquals("teste", alunoDTO.getClasse());
//        Assertions.assertEquals(true, alunoDTO.getActive());
//        Assertions.assertEquals(2L, alunoDTO.getProgramaId());
//        Assertions.assertEquals("testeteste", alunoDTO.getProgramaName());
//        Assertions.assertEquals(1L, alunoDTO.getId());
    }

    @Test
    public void testPostAluno() {

        Programa programa = new Programa();
        programa.setId(1L);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);
        alunoDTO.setName("t");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(true);
        alunoDTO.setProgramaId(1L);
        alunoDTO.setProgramaName("testeteste");


        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);
        Mockito.when(alunoMapper.toAluno(alunoDTO)).thenReturn(aluno);


        Optional<AlunoDTO> alunoSalvo = this.alunoService.criaAluno(alunoDTO);

        Assertions.assertAll(
                () -> Assertions.assertTrue(alunoSalvo.isPresent()),
                () -> Assertions.assertEquals(aluno.getId(), alunoSalvo.get().getId()),
                () -> Assertions.assertEquals(aluno.getName(), alunoSalvo.get().getName()),
                () -> Assertions.assertEquals(aluno.getClasse(), alunoSalvo.get().getClasse()),
                () -> Assertions.assertEquals(aluno.getActive(), alunoSalvo.get().getActive()),
                () -> Assertions.assertEquals(aluno.getPrograma().getId(), alunoSalvo.get().getProgramaId()),
                () -> Assertions.assertEquals(aluno.getPrograma().getName(), alunoSalvo.get().getProgramaName())
        );
//        Assertions.assertTrue(alunoSalvo.isPresent());

    }


    @Test
    public void testPutAluno() {
//        Programa programa = new Programa();
//        programa.setId(1L);
//
//        Aluno aluno = new Aluno();
//        aluno.setId(1L);
//        aluno.setName("t");
//        aluno.setClasse("teste");
//        aluno.setActive(true);
//        aluno.setPrograma(programa);
//
//        AlunoDTO alunoDTO = alunoMapper.toAlunoDTO(aluno);
//
//        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
//
//        Optional<AlunoDTO> alunoSalvo = this.alunoService.criaAluno(alunoDTO);
//
//        Assertions.assertTrue(alunoSalvo.isPresent());
    }


    @Test
    public void testDeleteAluno() {

//        Long id = 1L;
//
//        Programa programa = new Programa();
//        programa.setId(2L);
//        programa.setName("testeteste");
//
//        Aluno aluno = new Aluno();
//        aluno.setName("t");
//        aluno.setClasse("teste");
//        aluno.setId(1L);
//        aluno.setActive(true);
//        aluno.setPrograma(programa);
//
//        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
//
//        Optional<AlunoDTO> alunoByIndex = this.alunoService.removeAluno(id); //CHAMADA DO MÉTODO A TESTAR
//
//        Assertions.assertTrue(alunoRepository.findByActive(false).contains(alunoByIndex));

    }

    @Test
    public void testReativaAluno() {

//        Long id = 1L;
//
//        Programa programa = new Programa();
//        programa.setId(2L);
//        programa.setName("testeteste");
//
//        Aluno aluno = new Aluno();
//        aluno.setName("t");
//        aluno.setClasse("teste");
//        aluno.setId(1L);
//        aluno.setActive(false);
//        aluno.setPrograma(programa);
//
//        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
//
//        Optional<AlunoDTO> alunoByIndex = this.alunoService.reativaAluno(id); //CHAMADA DO MÉTODO A TESTAR
//
//        Assertions.assertTrue(alunoRepository.findByActive(true).contains(alunoByIndex));
    }
}
