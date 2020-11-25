package com.example.demo.controller;

import com.example.demo.dto.MentorDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Mentor;
import com.example.demo.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/mentor")
public class MentorController {

    // @Autowired
    //MentorRepository mentorRepository;

    @Autowired
    MentorService mentorService;

    @GetMapping
    public ResponseEntity<Optional<List<MentorDTO>>> getMentores() {
        return ResponseEntity.ok(mentorService.getMentores());
    }

    @GetMapping("/reativacao")
    public ResponseEntity<Optional<List<MentorDTO>>> getMentoresInativos() {
        return ResponseEntity.ok(mentorService.getMentoresInativos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MentorDTO>> getMentor(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(mentorService.getMentorByIndex(id));
    }

    @PostMapping
    public ResponseEntity<Optional<MentorDTO>> postMentor(@RequestBody @Validated MentorDTO mentorDTO) throws Exception {
        mentorService.criaMentor(mentorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mentor> deleteMentor(@PathVariable Long id) {
            mentorService.removeMentorByIndex(id);  //COM A ANOTAÇÃO NÃO PRECISA TRATAR PORQUE SE DER A EXCEÇÃO ELE PEGA DIRETO DE LÁ.
            return  ResponseEntity.ok().build();

//        try {           // SEM ANOTAÇÃO DE RESPONSESTATUS NA EXCEÇÃO, OU SE QUISER PASSAR A MENSAGEM NO BODY DA RESPOSTA
//            mentorService.removeMentorByIndex(id);
//            return ResponseEntity.ok().build();
//
//        } catch (NotFoundException ex) {
//            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Optional<MentorDTO>> updateMentor(@PathVariable Long id, @RequestBody @Validated MentorDTO mentorDTO) {
        mentorService.alteraMentor(id, mentorDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reativacao/{id}")
    public ResponseEntity<Optional<MentorDTO>> reativaMentor(@PathVariable Long id) {
        mentorService.reativaMentor(id);
        return ResponseEntity.ok().build();
    }
}
