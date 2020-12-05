package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Api(value="API de Aluno")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @ApiOperation(value="Página de alunos ativos", notes = "Retorna uma página contendo alunos ativos.")
    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> getAlunos(@PageableDefault(size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable){
       return ResponseEntity.ok(alunoService.getAlunos(pageable).get());
    }

    @ApiOperation(value = "Lista de alunos ativos", notes = "Retorna todos os alunos ativos. Utilizado para select no front.")
    @GetMapping("/lista")
    public ResponseEntity<List<AlunoDTO>> getAlunosList(){
        return ResponseEntity.ok(alunoService.getAlunosList().get());
    }

    @ApiOperation(value="Página de alunos inativos", notes = "Retorna uma página contendo alunos inativos.")
    @GetMapping("/reativacao")
    public ResponseEntity<Page<AlunoDTO>> getAlunosInativos(@PageableDefault(size=5, sort="name", direction=Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(alunoService.getAlunosInativos(pageable).get());
    }
    //O ResponseEntity não é necessário, mas permite passar outras respostas, ou header, etc

    @ApiOperation(value="Busca de aluno por ID", notes = "Retorna o aluno com o ID selecionado.")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable Long id)/* throws Exception*/{
        return ResponseEntity.ok().body(alunoService.getAlunoByIndex(id).get());
    }

    // Pode ou não ter endpoint com os métodos. se não tiver, quando houver
    //requisição do tipo anotado, ele faz aquilo de boa.
    //se tivesse um outro endpoint no GetMapping, seria /aluno/novoendpoint

    @ApiOperation(value="Criação de aluno", notes = "Permite a criação de aluno.")
    @PostMapping
    public ResponseEntity<AlunoDTO> criaAluno(@RequestBody @Validated AlunoDTO alunoDTO){
        alunoService.criaAluno(alunoDTO);
        return ok().build();
    }

    @ApiOperation(value="Remoção lógica de aluno", notes = "Altera o atributo active do aluno selecionado para false.  Se houver mentoria associada, é também removida.")
    @DeleteMapping("/{id}")
    public ResponseEntity<AlunoDTO> deleteAluno(@PathVariable Long id){
        alunoService.removeAluno(id);
        return ok().build();
    }

    @ApiOperation(value="Edição de aluno", notes = "Altera as informações do aluno com ID selecionado.")
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable Long id, @RequestBody @Validated AlunoDTO alunoDTO)/* throws Exception*/{
        alunoService.alteraAluno(id, alunoDTO);
        return ok().build();
    }

    @ApiOperation(value="Reativação lógica de aluno", notes = "Reativa o aluno do ID selecionado, alterando o atributo active para true.")
    @PostMapping("/reativacao/{id}")
    public ResponseEntity<AlunoDTO> reativaAluno(@PathVariable Long id){
        alunoService.reativaAluno(id);
        return ok().build();
    }
}
