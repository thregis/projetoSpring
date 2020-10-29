package com.example.demo.service;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.model.Mentoria;
import com.example.demo.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentoriaService {

    @Autowired
    MentoriaRepository mentoriaRepository;

    public Optional<List<MentoriaDTO>> getMentorias(){
        return Optional.of(mentoriaRepository.findByActive(true)
                .stream()
                .map(MentoriaMapper::toMentoriaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<MentoriaDTO> getMentoriaByIndex(Long id){
        if (mentoriaRepository.findById(id).get().getActive()) {
            return Optional.of(mentoriaRepository.findById(id)
                    .map(MentoriaMapper::toMentoriaDTO))
                    .orElse(Optional.empty());
        } else {
            return Optional.empty();
        }
    }

    public Optional<MentoriaDTO> criaMentoria(MentoriaDTO mentoriaDTO) {
        mentoriaDTO.setActive(true);
        return Optional.of(MentoriaMapper
                .toMentoriaDTO(mentoriaRepository.save(MentoriaMapper.toMentoria(mentoriaDTO))));
    }

    public Optional<MentoriaDTO> alteraMentoria(Long id, MentoriaDTO mentoriaDTO){
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if (!mentoria.isPresent()){
            return Optional.empty();
        }
        mentoriaDTO.setId(id);
        mentoriaDTO.setActive(true);
        return Optional.of(MentoriaMapper
                .toMentoriaDTO(mentoriaRepository.save(MentoriaMapper.toMentoria(mentoriaDTO))));

    }

    public Optional<MentoriaDTO> removeMentoria(Long id) {
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if(mentoria.isPresent()){
            mentoria.get().setActive(false);
        }
        return Optional.of(MentoriaMapper.toMentoriaDTO(mentoriaRepository.save(mentoria.get())));
    }

    public Optional<MentoriaDTO> reativaMentoria(Long id) {
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if(mentoria.isPresent()){
            mentoria.get().setActive(true);
        }
        return Optional.of(MentoriaMapper.toMentoriaDTO(mentoriaRepository.save(mentoria.get())));
    }

}
