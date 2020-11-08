package com.example.demo.service;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.dto.mapper.MentoriaMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
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

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MentorService mentorService;

    public Optional<List<MentoriaDTO>> getMentorias() {
        return Optional.of(mentoriaRepository.findByActive(true)
                .stream()
                .map(mentoriaMapper::toMentoriaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<List<MentoriaDTO>> getMentoriasInativas() {
        return Optional.of(mentoriaRepository.findByActive(false)
                .stream()
                .map(mentoriaMapper::toMentoriaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<MentoriaDTO> getMentoriaByIndex(Long id) {
        if (mentoriaRepository.findById(id).isPresent() && mentoriaRepository.findById(id).get().getActive()) {
            return Optional.of(mentoriaRepository.findById(id)
                    .map(mentoriaMapper::toMentoriaDTO)).get();
        } else {
            throw new NotFoundException("Não há mentoria ativa com o ID informado.");
        }
    }

    public Optional<MentoriaDTO> criaMentoria(MentoriaDTO mentoriaDTO) {
        if (alunoService.getAlunoByIndex(mentoriaDTO.getAlunoId()).isPresent() && mentorService.getMentorByIndex(mentoriaDTO.getMentorId()).isPresent()) {

            mentoriaDTO.setActive(true);
            return Optional.of(mentoriaMapper
                    .toMentoriaDTO(mentoriaRepository.save(mentoriaMapper.toMentoria(mentoriaDTO))));
        } else {
            throw new BadRequestException("Valores inválidos inseridos.");
        }

    }

    public Optional<MentoriaDTO> alteraMentoria(Long id, MentoriaDTO mentoriaDTO) {
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if (!mentoria.isPresent() || !mentoria.get().getActive()) {
            throw new NotFoundException("Não há mentoria ativa com o ID informado.");
        } else {
            if (alunoService.getAlunoByIndex(mentoriaDTO.getAlunoId()).isPresent() && mentorService.getMentorByIndex(mentoriaDTO.getMentorId()).isPresent()) {
                mentoriaDTO.setId(id);
                mentoriaDTO.setActive(true);
                return Optional.of(mentoriaMapper
                        .toMentoriaDTO(mentoriaRepository.save(mentoriaMapper.toMentoria(mentoriaDTO))));
            }
            throw new BadRequestException("Valores inválidos informados");
        }

    }

    public Optional<MentoriaDTO> removeMentoria(Long id) {
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);

        if (mentoria.isPresent() && mentoria.get().getActive()) {
            mentoria.get().setActive(false);
            avaliacaoService.setActiveByMentoria(false, id);
            return Optional.of(mentoriaMapper.toMentoriaDTO(mentoriaRepository.save(mentoria.get())));
        } else {
            throw new NotFoundException("Não há mentoria ativa com o ID informado.");
        }

    }

    public Optional<MentoriaDTO> reativaMentoria(Long id) {
        Optional<Mentoria> mentoria = mentoriaRepository.findById(id);
        if (mentoria.isPresent() && !mentoria.get().getActive() && mentoria.get().getMentor().getActive() && mentoria.get().getAluno().getActive()) {
            mentoria.get().setActive(true);
            return Optional.of(mentoriaMapper.toMentoriaDTO(mentoriaRepository.save(mentoria.get())));
        } else if(mentoria.isPresent() && !mentoria.get().getActive() && (!mentoria.get().getMentor().getActive() || !mentoria.get().getMentor().getActive())){
            throw new BadRequestException("Aluno ou mentor inválidos.");
        } else{
            throw new NotFoundException("Não há mentoria inativa com o ID informado");
        }

    }

    public void setActiveByAluno(Boolean active, Long id) {
        mentoriaRepository.setActiveByAluno(active, id);
    }

    public void setActiveByMentor(Boolean active, Long id) {
        mentoriaRepository.setActiveByMentor(active, id);
    }

}
