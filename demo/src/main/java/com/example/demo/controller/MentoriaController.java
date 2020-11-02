package com.example.demo.controller;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.model.Mentor;
import com.example.demo.model.Mentoria;
import com.example.demo.service.MentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mentoria")
public class MentoriaController {

    @Autowired
    MentoriaService mentoriaService;

    @GetMapping
    public ResponseEntity<Optional<List<MentoriaDTO>>> getMentorias(){
        return ResponseEntity.ok(mentoriaService.getMentorias());
    }

    @GetMapping("/reativacao")
    public ResponseEntity<Optional<List<MentoriaDTO>>> getMentoriasInativas(){
        return ResponseEntity.ok(mentoriaService.getMentoriasInativas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MentoriaDTO>> getMentoria(@PathVariable Long id){
        return ResponseEntity.ok().body(mentoriaService.getMentoriaByIndex(id));
    }

    @PostMapping
    public ResponseEntity<Optional<MentoriaDTO>> postMentoria(@RequestBody MentoriaDTO mentoriaDTO){
        mentoriaService.criaMentoria(mentoriaDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Optional<MentoriaDTO>> updateMentoria(@PathVariable Long id, @RequestBody MentoriaDTO mentoriaDTO){
        mentoriaService.alteraMentoria(id, mentoriaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<MentoriaDTO>> deleteMentoria(@PathVariable Long id){
        mentoriaService.removeMentoria(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reativacao/{id}")
    public ResponseEntity<Optional<MentoriaDTO>> reativaMentoria(@PathVariable Long id){
        mentoriaService.reativaMentoria(id);
        return ResponseEntity.ok().build();
    }


}
