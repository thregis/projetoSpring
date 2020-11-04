package com.example.demo.controller;

import com.example.demo.dto.MentorDTO;
import com.example.demo.model.Mentor;
import com.example.demo.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mentor")
public class MentorController {

   // @Autowired
    //MentorRepository mentorRepository;

    @Autowired
    MentorService mentorService;

    @GetMapping
    public ResponseEntity<Optional<List<MentorDTO>>> getMentores(){
        return ResponseEntity.ok(mentorService.getMentores());
    }

    @GetMapping("/reativacao")
    public ResponseEntity<Optional<List<MentorDTO>>> getMentoresInativos(){
        return ResponseEntity.ok(mentorService.getMentoresInativos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MentorDTO>> getMentor(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(mentorService.getMentorByIndex(id));
    }

    @PostMapping
    public ResponseEntity<Optional<MentorDTO>> postMentor(@RequestBody @Validated MentorDTO mentorDTO) throws Exception{
        mentorService.criaMentor(mentorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mentor> deleteMentor(@PathVariable Long id){
            mentorService.removeMentorByIndex(id);  //COM A ANOTAÇÃO NÃO PRECISA TRATAR PORQUE SE DER A EXCEÇÃO ELE PEGA DIRETO DE LÁ.
            return  ResponseEntity.accepted().build();

        //        try {            SEM ANOTAÇÃO DE RESPONSESTATUS NA EXCEÇÃO
//            mentorService.removeMentorByIndex(id);
//            return ResponseEntity.accepted().build();
////            return ResponseEntity.ok().build();
//        } catch(MentorNotFoundException ex){
//            return ResponseEntity.notFound().build();
//        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Optional<MentorDTO>> updateMentor(@PathVariable Long id, @RequestBody @Validated MentorDTO mentorDTO){
        mentorService.alteraMentor(id, mentorDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reativacao/{id}")
    public ResponseEntity<Optional<MentorDTO>> reativaMentor(@PathVariable Long id){
        mentorService.reativaMentor(id);
        return ResponseEntity.ok().build();
    }
}
