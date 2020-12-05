package com.example.demo.controller;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.service.DisciplinaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value= "Api de Disciplina")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @ApiOperation(value="Lista de disciplinas ativas", notes = "Retorna uma página contendo todas as disciplinas ativas.")
    @GetMapping
    public ResponseEntity<Page<DisciplinaDTO>> getDisciplinas(@PageableDefault(size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(disciplinaService.getDisciplinas(pageable).get());
    }
    @ApiOperation(value = "Lusta de disciplinas inativas", notes = "Retorna uma página contendo todas as disciplinas inativas.")
    @GetMapping("/reativacao")
    public ResponseEntity<Page<DisciplinaDTO>> getDisciplinasInativas(@PageableDefault(size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(disciplinaService.getDisciplinasInativas(pageable).get());
    }

    @ApiOperation(value = "Busca de disciplina por ID", notes = "Retorna a disciplina com o ID selecionado.")
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> getDisciplina(@PathVariable Long id){
        return ResponseEntity.ok(disciplinaService.getDisciplinaByIndex(id).get());
    }

    @ApiOperation(value = "Criação de disciplina", notes = "Permite a criação de disciplina.")
    @PostMapping
    public ResponseEntity<DisciplinaDTO> postDisciplina(@RequestBody @Validated DisciplinaDTO disciplinaDTO){
        disciplinaService.criaDisciplina(disciplinaDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Edição de disciplina", notes = "Altera as informações da disciplina com ID selecionado.")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<DisciplinaDTO> updateDisciplina(@PathVariable Long id, @RequestBody @Validated DisciplinaDTO disciplinaDTO)/* throws Exception */{
        disciplinaService.alteraDisciplina(id, disciplinaDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Remoção lógica de disciplina", notes = "Altera o atributo active da disciplina para false. OBS: não remove avaliação.")
    @DeleteMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> deleteDisciplina(@PathVariable Long id){
        disciplinaService.removeDisciplina(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Reativação lógica de disciplina", notes = "Reativa a disciplina com o ID selecionado, alterando o atributo active para true.")
    @PostMapping("/reativacao/{id}")
    public ResponseEntity<DisciplinaDTO> reativaDisciplina(@PathVariable Long id){
        disciplinaService.reativaDisciplina(id);
        return ResponseEntity.ok().build();
    }
}
