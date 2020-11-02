package com.example.demo.service;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.model.Mentoria;
import com.example.demo.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MentoriaService {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private MentoriaMapper mentoriaMapper;

    public Optional<List<MentoriaDTO>> getMentorias(){
        return Optional.of(mentoriaRepository.findByActive(true)
                .stream()
                .map(mentoriaMapper::toMentoriaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<List<MentoriaDTO>> getMentoriasInativas(){
        return Optional.of(mentoriaRepository.findByActive(false)
                .stream()
                .map(mentoriaMapper::toMentoriaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<MentoriaDTO> getMentoriaByIndex(Long id){
        if (mentoriaRepository.findById(id).get().getActive()) {
            return Optional.of(mentoriaRepository.findById(id)
                    .map(mentoriaMapper::toMentoriaDTO))
                    .orElse(Optional.empty());
        } else {
            return Optional.empty();
        }
    }

    public Optional<MentoriaDTO> criaMentoria(MentoriaDTO mentoriaDTO) {
        mentoriaDTO.setActive(true);
        return Optional.of(mentoriaMapper
                .toMentoriaDTO(mentoriaRepository.save(mentoriaMapper.toMentoria(mentoriaDTO))));
    }

    public Optional<MentoriaDTO> alteraMentoria(Long id, MentoriaDTO mentoriaDTO){
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if (!mentoria.isPresent()){
            return Optional.empty();
        }
        mentoriaDTO.setId(id);
        mentoriaDTO.setActive(true);
        return Optional.of(mentoriaMapper
                .toMentoriaDTO(mentoriaRepository.save(mentoriaMapper.toMentoria(mentoriaDTO))));

    }

    public Optional<MentoriaDTO> removeMentoria(Long id) {
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if(mentoria.isPresent()){
            mentoria.get().setActive(false);
        }
        return Optional.of(mentoriaMapper.toMentoriaDTO(mentoriaRepository.save(mentoria.get())));
    }

    public Optional<MentoriaDTO> reativaMentoria(Long id) {
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if(mentoria.isPresent()){
            mentoria.get().setActive(true);
        }
        return Optional.of(mentoriaMapper.toMentoriaDTO(mentoriaRepository.save(mentoria.get())));
    }

    public void setActiveByAluno(Boolean active, Long id){
        mentoriaRepository.setActiveByAluno(active, id);
    }
    public void setActiveByMentor(Boolean active, Long id){
        mentoriaRepository.setActiveByMentor(active, id);
    }

}
