package com.example.demo.controller;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
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

@Api(value="API de Programa")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @ApiOperation(value="Página de programas ativos", notes = "Retorna uma página contendo programas ativos.")
    @GetMapping
    public ResponseEntity<Page<ProgramaDTO>> getProgramas(@PageableDefault(size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(programaService.getProgramas(pageable).get());
    }

    @ApiOperation(value = "Lista de programas ativos", notes = "Retorna todos os programas ativos. Utilizado para select no front.")
    @GetMapping("/lista")
    public ResponseEntity<List<ProgramaDTO>> getProgramasList(){
        return ResponseEntity.ok(programaService.getProgramasList().get());
    }

    @ApiOperation(value="Página de programas inativos", notes = "Retorna uma página contendo programas inativos.")
    @GetMapping("/reativacao")
    public ResponseEntity<Page<ProgramaDTO>> getProgramasInativos(@PageableDefault(size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(programaService.getProgramasInativos(pageable).get());
    }

    @ApiOperation(value="Busca de programas por ID", notes = "Retorna o programa com o ID selecionado.")
    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDTO> getPrograma(@PathVariable Long id)/* throws Exception */{
        return ResponseEntity.ok(programaService.getProgramaByIndex(id).get());
    }

    @ApiOperation(value="Criação de programa", notes = "Permite a criação de programa.")
    @PostMapping
    public ResponseEntity<ProgramaDTO> postPrograma(@RequestBody @Validated ProgramaDTO programaDTO){
        programaService.criaPrograma(programaDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Edição de programa", notes = "Altera as informações do programa com ID selecionado.")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ProgramaDTO> updatePrograma(@PathVariable Long id, @RequestBody @Validated ProgramaDTO programaDTO)/* throws Exception */{
        programaService.alteraPrograma(id, programaDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Remoção lógica de programa", notes = "Altera o atributo active do programa selecionado para false.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProgramaDTO> deletePrograma(@PathVariable Long id){
        programaService.removePrograma(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Reativação lógica de programa", notes = "Reativa o programa do ID selecionado alterando o atributo active para true.")
    @PostMapping("/reativacao/{id}")
    public ResponseEntity<ProgramaDTO> reativaPrograma(@PathVariable Long id){
        programaService.reativaPrograma(id);
        return ResponseEntity.ok().build();
    }
}
