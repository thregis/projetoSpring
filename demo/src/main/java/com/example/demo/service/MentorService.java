package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Mentor;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentorService {

    // private List<String> mentores = new ArrayList<String>(List.of("Jeremias"));

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private MentorMapper mentorMapper;


    @Transactional(readOnly = true)
    public Optional<Page<MentorDTO>> getMentores(Pageable pageable) {

        return Optional.of(mentorRepository.findByActive(true, pageable)
                .map(mentorMapper::toMentorDTO));
    }

    @Transactional(readOnly = true)
    public Optional<List<MentorDTO>> getMentoresList(){

        return Optional.of(mentorRepository.findListByActive(true)
                .stream()
                .map(mentorMapper::toMentorDTO)
                .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public Optional<Page<MentorDTO>> getMentoresInativos(Pageable pageable) {

        return Optional.of(mentorRepository.findByActive(false, pageable)
                .map(mentorMapper::toMentorDTO));
    }

    @Transactional
    public Optional<MentorDTO> getMentorByIndex(Long id)/*throws Exception*/ {
//        Optional<MentorDTO> mentorDTO = mentorRepository.findById(id).map(MentorMapper::toMentorDTO);
//        if (!mentorDTO.isPresent()){
//            throw new Exception ("id "+id+" não encontrado.");
//        }
//        return mentorDTO.get();
        if (mentorRepository.findById(id).isPresent() && mentorRepository.findById(id).get().getActive()) {
            return Optional.of(mentorRepository.findById(id)
                    .map(mentorMapper::toMentorDTO)).get();
        }
        throw new NotFoundException("Não há mentor ativo com o ID informado.");
    }

    @Transactional
    public Optional<MentorDTO> criaMentor(MentorDTO mentorDTO) {

        if ((mentorDTO.getName().length() >= 3 && mentorDTO.getName().length() <= 50) && mentorDTO.getPais().length() <= 50 && mentorDTO.getEscola().length() <= 50) {
            mentorDTO.setActive(true);
            return Optional.of(mentorMapper
                    .toMentorDTO(mentorRepository.save(mentorMapper.toMentor(mentorDTO))));
        }
        throw new BadRequestException("Valores inválidos informados.");
    }

    @Transactional
    public Optional<MentorDTO> removeMentorByIndex(Long id) {
        Optional<Mentor> mentor = mentorRepository.findById(id);

        if ((mentor.isPresent()) && (mentor.get().getActive())) {
            mentor.get().setActive(false);
            mentoriaService.setActiveByMentor(false, id);
            avaliacaoService.setActiveByMentor(false, id);
            return Optional.of(mentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));// mentorRepository.deleteById(id);
        }
        throw new NotFoundException("Não há mentor ativo com o ID informado.");
    }

    @Transactional
    public Optional<MentorDTO> alteraMentor(Long id, MentorDTO mentorDTO) {
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if ((!mentor.isPresent()) || (!mentor.get().getActive())) {
            throw new NotFoundException("Não há mentor ativo com o ID informado.");
        } else {
            if (mentorDTO.getName().length() < 3 || mentorDTO.getName().length() > 50 || mentorDTO.getPais().length() > 50 || mentorDTO.getEscola().length() > 50) {
                throw new BadRequestException("Valores inválidos informados.");
            }
            mentorDTO.setId(id);
            mentorDTO.setActive(true);
            return Optional.of(mentorMapper
                    .toMentorDTO(mentorRepository.save(mentorMapper.toMentor(mentorDTO))));
        }
    }

    @Transactional
    public Optional<MentorDTO> reativaMentor(Long id) {
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if ((mentor.isPresent()) && (!mentor.get().getActive())) {
            mentor.get().setActive(true);
            return Optional.of(mentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));
        }
        throw new NotFoundException("Não há mentor inativo com o ID informado.");
    }
}

