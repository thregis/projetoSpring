package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
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
    MentoriaService mentoriaService;

    @Mock
    AvaliacaoService avaliacaoService;

    @Mock
    AlunoMapper alunoMapper;


    @Test
    public void testGetAlunosAtivos() {

        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(true);
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);
        aluno2.setName("teste2");
        aluno2.setActive(true);

        Aluno aluno3 = new Aluno();
        aluno3.setId(3L);
        aluno3.setName("teste3");
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
        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setActive(false);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(false);
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);
        aluno2.setName("teste2");
        aluno2.setActive(false);

        Aluno aluno3 = new Aluno();
        aluno3.setId(3L);
        aluno3.setName("teste3");
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
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setId(id);
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setId(id);
        alunoDTO.setActive(true);
        alunoDTO.setProgramaName("testeteste");
        alunoDTO.setProgramaId(id);


        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);

        Optional<AlunoDTO> alunoByIndex = this.alunoService.getAlunoByIndex(id); //CHAMADA DO MÉTODO A TESTAR

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

        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setProgramaId(id);
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
                () -> Assertions.assertEquals(aluno.getPrograma().getId(), alunoSalvo.get().getProgramaId()),
                () -> Assertions.assertEquals(aluno.getPrograma().getName(), alunoSalvo.get().getProgramaName()),
                () -> Assertions.assertEquals(true, alunoSalvo.get().getActive()),
                () -> Assertions.assertFalse(aluno.getActive() == alunoSalvo.get().getActive())
        );

    }


    @Test
    public void testPutAluno() {
        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(true);
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);
        Mockito.when(alunoMapper.toAluno(alunoDTO)).thenReturn(aluno);

        Optional<AlunoDTO> alunoSalvo = this.alunoService.criaAluno(alunoDTO);

        Aluno aluno2 = new Aluno();
        aluno2.setId(id);
        aluno2.setName("teste2");
        aluno2.setClasse("teste2");
        aluno2.setActive(true);
        aluno2.setPrograma(programa);

        AlunoDTO alunoDTO2 = new AlunoDTO();
        alunoDTO2.setId(id);
        alunoDTO2.setName("teste2");
        alunoDTO2.setClasse("teste2");
        alunoDTO2.setActive(true);
        alunoDTO2.setProgramaId(id);
        alunoDTO2.setProgramaName("testeteste");

        Mockito.when(alunoRepository.save(aluno2)).thenReturn(aluno2);
        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno2));
        Mockito.when(alunoMapper.toAlunoDTO(aluno2)).thenReturn(alunoDTO2);
        Mockito.when(alunoMapper.toAluno(alunoDTO2)).thenReturn(aluno2);

        Optional<AlunoDTO> alunoAlterado = this.alunoService.alteraAluno(id, alunoDTO2);

        Assertions.assertAll(
                () -> Assertions.assertTrue(alunoAlterado.isPresent()),
                () -> Assertions.assertEquals(alunoDTO.getId(), alunoAlterado.get().getId()),
                () -> Assertions.assertEquals("teste2", alunoAlterado.get().getName()),
                () -> Assertions.assertEquals("teste2", alunoAlterado.get().getClasse()),
                () -> Assertions.assertNotEquals(alunoAlterado.get().getName(), alunoSalvo.get().getName())
        );

    }


    @Test
    public void testDeleteAluno() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setId(id);
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setId(id);
        alunoDTO.setActive(true);
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);
        Mockito.doNothing().when(mentoriaService).setActiveByAluno(false, id);
        Mockito.doNothing().when(avaliacaoService).setActiveByAluno(false, id);

        Optional<AlunoDTO> alunoRemoval = this.alunoService.removeAluno(id); //CHAMADA DO MÉTODO A TESTAR

        Mockito.verify(mentoriaService, Mockito.times(1)).setActiveByAluno(false, id);

        Assertions.assertAll(
                () -> Assertions.assertEquals(false, aluno.getActive())
        );


    }

    @Test
    public void testReativaAluno() {

        Long id = 1L;

        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setId(id);
        aluno.setActive(false);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setId(id);
        alunoDTO.setActive(false);
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);

        Optional<AlunoDTO> alunoReactive = this.alunoService.reativaAluno(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertAll(
                () -> Assertions.assertEquals(true, aluno.getActive())
        );
    }

    // -------------------TESTES DE EXCEÇÕES-----------------------

    @Test
    public void testGetAlunoByIdNotFound() {
        Long id = 1L;

        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> alunoService.getAlunoByIndex(id));
    }

    @Test
    public void testPostAlunoComValoresInvalidos() {

        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("t");
        alunoDTO.setClasse("teste");
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Assertions.assertThrows(BadRequestException.class, () -> alunoService.criaAluno(alunoDTO));

    }


    @Test
    public void testPutAlunoNotFound() {
        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(true);
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Assertions.assertThrows(NotFoundException.class, () -> alunoService.alteraAluno(id, alunoDTO));

    }
    @Test
    public void testPutAlunoDadosInvalidos() {
        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("testeteste");

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setName("teste");
        aluno.setClasse("teste");
        aluno.setActive(true);
        aluno.setPrograma(programa);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setName("teste");
        alunoDTO.setClasse("teste");
        alunoDTO.setActive(true);
        alunoDTO.setProgramaId(id);
        alunoDTO.setProgramaName("testeteste");

        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        Mockito.when(alunoMapper.toAlunoDTO(aluno)).thenReturn(alunoDTO);
        Mockito.when(alunoMapper.toAluno(alunoDTO)).thenReturn(aluno);

        Optional<AlunoDTO> alunoSalvo = this.alunoService.criaAluno(alunoDTO);

        Aluno aluno2 = new Aluno();
        aluno2.setId(id);
        aluno2.setName("t");
        aluno2.setClasse("teste2");
        aluno2.setActive(true);
        aluno2.setPrograma(programa);

        AlunoDTO alunoDTO2 = new AlunoDTO();
        alunoDTO2.setId(id);
        alunoDTO2.setName("t");
        alunoDTO2.setClasse("teste2");
        alunoDTO2.setActive(true);
        alunoDTO2.setProgramaId(id);
        alunoDTO2.setProgramaName("testeteste");

        Assertions.assertThrows(BadRequestException.class, () -> alunoService.alteraAluno(id, alunoDTO2));

    }

    @Test
    public void testDeleteAlunoNotFound() {

        Long id = 1L;

        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, ()-> alunoService.removeAluno(id));


    }

    @Test
    public void testReativaAlunoNotFound() {

        Long id = 1L;

        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, ()-> alunoService.reativaAluno(id));
    }

}