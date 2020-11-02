package com.example.demo.controller;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<Optional<List<DisciplinaDTO>>> getDisciplinas(){
        return new ResponseEntity<Optional<List<DisciplinaDTO>>>(disciplinaService.getDisciplinas(), HttpStatus.OK);
    }
    @GetMapping("/reativacao")
    public ResponseEntity<Optional<List<DisciplinaDTO>>> getDisciplinasInativas(){
        return new ResponseEntity<Optional<List<DisciplinaDTO>>>(disciplinaService.getDisciplinasInativas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DisciplinaDTO>> getDisciplina(@PathVariable Long id){
        return ResponseEntity.ok(disciplinaService.getDisciplinaByIndex(id));
    }
    @PostMapping
    public ResponseEntity<Optional<DisciplinaDTO>> postDisciplina(@RequestBody @Validated DisciplinaDTO disciplinaDTO){
        disciplinaService.criaDisciplina(disciplinaDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Optional<DisciplinaDTO>> updateDisciplina(@PathVariable Long id, @RequestBody @Validated DisciplinaDTO disciplinaDTO)/* throws Exception */{
        disciplinaService.alteraDisciplina(id, disciplinaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<DisciplinaDTO>> deleteDisciplina(@PathVariable Long id){
        disciplinaService.removeDisciplina(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reativacao/{id}")
    public ResponseEntity<Optional<DisciplinaDTO>> reativaDisciplina(@PathVariable Long id){
        disciplinaService.reativaDisciplina(id);
        return ResponseEntity.ok().build();
    }
}
