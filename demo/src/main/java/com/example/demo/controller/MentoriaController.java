package com.example.demo.controller;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;
import com.example.demo.service.MentoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "Api de Mentoria")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/mentoria")
public class MentoriaController {

    @Autowired
    MentoriaService mentoriaService;

    @ApiOperation(value = "Lista de mentorias ativas", notes = "Retorna uma página contendo todas as mentorias ativas.")
    @GetMapping
    public ResponseEntity<Page<MentoriaDTO>> getMentorias(@PageableDefault (size=5, sort="mentorName", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(mentoriaService.getMentorias(pageable).get());
    }

    @ApiOperation(value = "Lista de mentorias inativas", notes = "Retorna uma página contendo todas as mentorias inativas.")
    @GetMapping("/reativacao")
    public ResponseEntity<Page<MentoriaDTO>> getMentoriasInativas(@PageableDefault (size=5, sort="mentorName", direction= Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(mentoriaService.getMentoriasInativas(pageable).get());
    }

    @ApiOperation(value = "Busca de mentoria por ID", notes = "Retorna a mentoria com o ID selecionado.")
    @GetMapping("/{id}")
    public ResponseEntity<MentoriaDTO> getMentoria(@PathVariable Long id){
        return ResponseEntity.ok().body(mentoriaService.getMentoriaByIndex(id).get());
    }

    @ApiOperation(value = "Criação de mentoria", notes = "Permite a criação de mentoria.")
    @PostMapping
    public ResponseEntity<MentoriaDTO> postMentoria(@RequestBody MentoriaDTO mentoriaDTO){
        mentoriaService.criaMentoria(mentoriaDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Edição de mentoria", notes = "Altera as informações da mentoria com ID selecionado.")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<MentoriaDTO> updateMentoria(@PathVariable Long id, @RequestBody MentoriaDTO mentoriaDTO){
        mentoriaService.alteraMentoria(id, mentoriaDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remoção lógica de mentoria", notes = "Altera o atributo active da mentoria selecionada para false. Se houver avaliação associada, é também removida.")
    @DeleteMapping("/{id}")
    public ResponseEntity<MentoriaDTO> deleteMentoria(@PathVariable Long id){
        mentoriaService.removeMentoria(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Reativação lógica de mentoria", notes = "Reativa mentoria do ID selecionado, alterando o atributo active para true.")
    @PostMapping("/reativacao/{id}")
    public ResponseEntity<MentoriaDTO> reativaMentoria(@PathVariable Long id){
        mentoriaService.reativaMentoria(id);
        return ResponseEntity.ok().build();
    }


}
