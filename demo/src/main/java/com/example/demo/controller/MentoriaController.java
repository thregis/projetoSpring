//package com.example.demo.controller;
//
//import com.example.demo.model.Mentor;
//import com.example.demo.model.Mentoria;
//import com.example.demo.service.MentoriaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
////@RestController
////@RequestMapping("/mentoria")
//public class MentoriaController {
//
////    @Autowired
////    MentoriaService mentoriaService;
////
////    @GetMapping
////    public ResponseEntity<List<Mentoria>> getMentorias(){
////        return ResponseEntity.ok(mentoriaService.getMentorias());
////    }
////
////    @GetMapping("/{id}")
////    public ResponseEntity<Mentoria> getMentoria(@PathVariable Long id) throws Exception{
////        return ResponseEntity.ok().body(mentoriaService.getMentoriaByIndex(id));
////    }
////
////    @PostMapping
////    public ResponseEntity<Mentoria> postMentoria(@RequestBody Mentoria mentoria){
////        mentoriaService.criaMentoria(mentoria);
////        return ResponseEntity.ok().build();
////    }
////
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Mentoria> deleteMentoria(@PathVariable Long id){
////        mentoriaService.removeMentoria(id);
////        return ResponseEntity.ok().build();
////    }
////
////    @PutMapping("/{id}")
////    @ResponseStatus(code = HttpStatus.OK)
////    public ResponseEntity<Mentoria> updateMentoria(@PathVariable Long id, @RequestBody Mentoria mentoria) throws Exception {
////        mentoriaService.alteraMentoria(id, mentoria);
////        return ResponseEntity.ok().build();
////    }
//}
