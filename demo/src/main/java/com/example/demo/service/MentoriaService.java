//package com.example.demo.service;
//
//import com.example.demo.model.Aluno;
//import com.example.demo.model.Mentoria;
//import com.example.demo.repository.MentoriaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
////@Service
//public class MentoriaService {
//
////    @Autowired
////    MentoriaRepository mentoriaRepository;
////
////    public List<Mentoria> getMentorias(){
////        return mentoriaRepository.findAll();
////    }
////
////    public Mentoria getMentoriaByIndex(Long id) throws Exception {
////        Optional<Mentoria> mentoria= mentoriaRepository.findById(id);
////        if (!mentoria.isPresent()){
////            throw new Exception ("id "+id+" não encontrado.");
////        }
////        return mentoria.get();
////    }
////
////    public void criaMentoria(Mentoria mentoria) {
////        mentoriaRepository.save(mentoria);
////    }
////
////    public void removeMentoria(Long id) {
////        mentoriaRepository.deleteById(id);
////    }
////
////    public void alteraMentoria(Long id, Mentoria mentoria) throws Exception{
////        Optional<Mentoria> mentoriaOpc = mentoriaRepository.findById(id);
////        if (!mentoriaOpc.isPresent()){
////            throw new Exception("id "+id+" não encontrado.");
////        }
////        mentoria.setId(id);
////        mentoriaRepository.save(mentoria);
////    }
//
//}
