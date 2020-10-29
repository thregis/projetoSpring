package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping//("/whatever")
    public ResponseEntity<Optional<List<AlunoDTO>>> getAlunos(){
       return ResponseEntity.ok(alunoService.getAlunos());
    }

    //O ResponseEntity não é necessário, mas permite passar outras respostas, ou header, etc

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlunoDTO>> getAluno(@PathVariable Long id)/* throws Exception*/{
        return ResponseEntity.ok().body(alunoService.getAlunoByIndex(id));
    }

    // Pode ou não ter endpoint com os métodos. se não tiver, quando houver
    //requisição do tipo anotado, ele faz aquilo de boa.
    //se tivesse um outro endpoint no GetMapping, seria /aluno/novoendpoint

    @PostMapping
    public ResponseEntity<AlunoDTO> criaAluno(@RequestBody AlunoDTO alunoDTO){
        alunoService.criaAluno(alunoDTO);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<AlunoDTO>> deleteAluno(@PathVariable Long id){
        alunoService.removeAluno(id);
        return ok().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) throws Exception{
        alunoService.alteraAluno(id, aluno);
        return ok().build();
    }
}
