package com.example.demo.controller;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.service.AvaliacaoService;
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

@Api(value="API de avaliação")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @ApiOperation(value = "Página de avaliações ativas", notes = "Retorna uma página contendo avaliações ativas.")
    @GetMapping
    public ResponseEntity<Page<AvaliacaoDTO>> getAvaliacoes(@PageableDefault(size=5, sort="mentoriaAlunoName", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(avaliacaoService.getAvaliacoes(pageable).get());
    }

    @ApiOperation(value = "Página de avaliações inativas", notes = "Retorna uma página contendo avaliações inativas.")
    @GetMapping("/reativacao")
    public ResponseEntity<Page<AvaliacaoDTO>> getAvaliacoesInativas(@PageableDefault(size=5, sort="mentoriaAlunoName", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(avaliacaoService.getAvaliacoesInativas(pageable).get());
    }

    @ApiOperation(value = "Busca de avaliação por ID", notes = "Retorna a avaliação com o ID selecionado.")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> getAvaliacao(@PathVariable Long id){
        return ResponseEntity.ok(avaliacaoService.getAvaliacaoByIndex(id).get());
    }

    @ApiOperation(value = "Criaçao de avaliação", notes = "Permite a criação de avaliação.")
    @PostMapping
    public ResponseEntity<AvaliacaoDTO> postAvaliacao(@RequestBody @Validated AvaliacaoDTO avaliacaoDTO){
        avaliacaoService.criaAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Edição de avaliação", notes = "Altera as informações da avaliação com ID selecionado.")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@PathVariable Long id, @RequestBody @Validated AvaliacaoDTO avaliacaoDTO){
        avaliacaoService.alteraAvaliacao(id, avaliacaoDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remoção lógica de avaliação", notes = "Altera o atributo active da avaliação selecionada para false.")
    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> deleteAvaliacao(@PathVariable Long id){
        avaliacaoService.removeAvaliacao(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Reativação lógica de avaliação", notes = "Reativa a avaliação do ID selecionado, alterando o atributo active para true.")
    @PostMapping("/reativacao/{id}")
    public ResponseEntity<AvaliacaoDTO> reativaAvaliacao(@PathVariable Long id){
        avaliacaoService.reativaAvaliacao(id);
        return ResponseEntity.ok().build();
    }
}
