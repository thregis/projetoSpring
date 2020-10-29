package com.example.demo.controller;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.model.Programa;
import com.example.demo.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/{programa}")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @GetMapping
    public ResponseEntity<Optional<List<ProgramaDTO>>> getProgramas(){
        return new ResponseEntity<Optional<List<ProgramaDTO>>>(programaService.getProgramas(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProgramaDTO>> getPrograma(@PathVariable Long id)/* throws Exception */{
        return ResponseEntity.ok(programaService.getProgramaByIndex(id));
    }

    @PostMapping
    public ResponseEntity<Optional<ProgramaDTO>> postPrograma(@RequestBody ProgramaDTO programaDTO){
        programaService.addPrograma(programaDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Optional<ProgramaDTO>> updatePrograma(@PathVariable Long id, @RequestBody ProgramaDTO programaDTO)/* throws Exception */{
        programaService.alteraPrograma(id, programaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<ProgramaDTO>> deletePrograma(@PathVariable Long id){
        programaService.removePrograma(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reativacao/{id}")
    public ResponseEntity<Optional<ProgramaDTO>> reativaPrograma(@PathVariable Long id){
        programaService.reativaPrograma(id);
        return ResponseEntity.ok().build();
    }
}
