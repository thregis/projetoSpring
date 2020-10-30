package com.example.demo;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.service.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class FirstMockTest {

    @Mock
    AlunoRepository alunoRepository;

    @InjectMocks
    AlunoService alunoService;

    //1 - Crio os mocks (farsas) e deixo mapeado (nenhua execução acontece)
    //2 - Eu chamo o serviço
    //4 - A execução do mock acontece


    @Test
    public void testGetAluno(){
        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(2L);

        Aluno aluno = new Aluno();
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setId(1L);
        aluno.setActive(true);
        aluno.setPrograma(programa);

        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));

        Optional<AlunoDTO> alunoByIndex = this.alunoService.getAlunoByIndex(id); //CHAMADA DO MÉTODO A TESTAR

        Assertions.assertTrue(alunoByIndex.isPresent());

        AlunoDTO alunoDTO = alunoByIndex.get();
        Assertions.assertEquals("t", alunoDTO.getName());
        Assertions.assertEquals("teste", alunoDTO.getClasse());
        Assertions.assertEquals(true, alunoDTO.getActive());
        Assertions.assertEquals(2L, alunoDTO.getProgramaId());
        Assertions.assertEquals(1L, alunoDTO.getId());


//        Assertions.assertEquals("t", alunoByIndex.get().getName());         era pior porque se o optional fosse null dava ruim
//        Assertions.assertEquals("t", alunoByIndex.get().getClasse());
//        Assertions.assertEquals("t", alunoByIndex.get().getActive());
//        Assertions.assertEquals("t", alunoByIndex.get().getProgramaId());

//        Assertions.assertAll(                                             Com lambda. não testei.
//                () -> Assertions.assertTrue(alunoByIndex.isPresent()),
//                () -> Assertions.assertEquals("2t", alunoByIndex.get().getName()),
//                () -> Assertions.assertEquals("teste", alunoByIndex.get().getClasse())
//        );

//  EXCEÇÃO
//        Assertions.assertThrows(Exception.class, () -> this.alunoService.getAlunoByIndex(id));
    }

//    @Test
//    public void testGetAllAlunos(){
//        var id = 1L;
//        Mockito.when(alunoRepository.findAll()).thenReturn();
//        List<AlunoDTO> all = alunoService.getAlunos(id);
//    }


    @Test
    public void testGetAlunoParaRetornoNuloDoBanco(){
        Long id = 1L;
        Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.empty());
//        AlunoDTO alunoByIndex = this.alunoService.getAlunoByIndex(id);
//        Assertions.assertAll(
//                () -> Assertions.assertEquals("t", alunoByIndex.getName())
//        );
    }

}
