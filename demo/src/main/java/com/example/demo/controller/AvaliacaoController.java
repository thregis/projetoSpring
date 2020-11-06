package com.example.demo.controller;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<Optional<List<AvaliacaoDTO>>> getAvaliacoes(){
        return new ResponseEntity<Optional<List<AvaliacaoDTO>>>(avaliacaoService.getAvaliacoes(), HttpStatus.OK);
    }
    @GetMapping("/reativacao")
    public ResponseEntity<Optional<List<AvaliacaoDTO>>> getAvaliacoesInativa(){
        return new ResponseEntity<Optional<List<AvaliacaoDTO>>>(avaliacaoService.getAvaliacoesInativas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AvaliacaoDTO>> getAvaliacao(@PathVariable Long id){
        return ResponseEntity.ok(avaliacaoService.getAvaliacaoByIndex(id));
    }
    @PostMapping
    public ResponseEntity<Optional<AvaliacaoDTO>> postAvaliacao(@RequestBody @Validated AvaliacaoDTO avaliacaoDTO){
        avaliacaoService.criaAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Optional<AvaliacaoDTO>> updateAvaliacao(@PathVariable Long id, @RequestBody @Validated AvaliacaoDTO avaliacaoDTO){
        avaliacaoService.alteraAvaliacao(id, avaliacaoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<AvaliacaoDTO>> deleteAvaliacao(@PathVariable Long id){
        avaliacaoService.removeAvaliacao(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reativacao/{id}")
    public ResponseEntity<Optional<AvaliacaoDTO>> reativaAvaliacao(@PathVariable Long id){
        avaliacaoService.reativaAvaliacao(id);
        return ResponseEntity.ok().build();
    }
}
