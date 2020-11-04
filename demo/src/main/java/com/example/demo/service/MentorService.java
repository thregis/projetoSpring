package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Mentor;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MentorService {

   // private List<String> mentores = new ArrayList<String>(List.of("Jeremias"));

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private MentorMapper mentorMapper;


    public Optional<List<MentorDTO>> getMentores() {

        return Optional.of(mentorRepository.findByActive(true)
                                .stream()
                                .map(mentorMapper::toMentorDTO)
                                .collect(Collectors.toList()));
    }

    public Optional<List<MentorDTO>> getMentoresInativos() {

        return Optional.of(mentorRepository.findByActive(false)
                .stream()
                .map(mentorMapper::toMentorDTO)
                .collect(Collectors.toList()));
    }

    public Optional<MentorDTO> getMentorByIndex(Long id)/*throws Exception*/{

//        Optional<MentorDTO> mentorDTO = mentorRepository.findById(id).map(MentorMapper::toMentorDTO);
//        if (!mentorDTO.isPresent()){
//            throw new Exception ("id "+id+" não encontrado.");
//        }
//        return mentorDTO.get();
        if (mentorRepository.findById(id).get().getActive()){
            return Optional.of(mentorRepository.findById(id)
                    .map(mentorMapper::toMentorDTO))
                    .orElse(Optional.empty());
        } else{
            return Optional.empty();
        }
    }

    public Optional<MentorDTO> criaMentor(MentorDTO mentorDTO)/* throws Exception*/{

        mentorDTO.setActive(true);
        return Optional.of(mentorMapper
                .toMentorDTO(mentorRepository.save(mentorMapper.toMentor(mentorDTO))));
    }

    public Optional<MentorDTO> removeMentorByIndex(Long id){
        Optional<Mentor> mentor = mentorRepository.findById(id);

        if(mentor.isPresent()) {
            mentor.get().setActive(false);
            mentoriaService.setActiveByMentor(false, id);
            return Optional.of(mentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));// mentorRepository.deleteById(id);
        }else{
            throw new NotFoundException();
        }

    }

    public Optional<MentorDTO> alteraMentor(Long id, MentorDTO mentorDTO){
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if (!mentor.isPresent()){
            return Optional.empty();
        }else{
            mentorDTO.setId(id);
            mentorDTO.setActive(true);
            return Optional.of(mentorMapper
                    .toMentorDTO(mentorRepository.save(mentorMapper.toMentor(mentorDTO))));
        }
    }
    public Optional<MentorDTO> reativaMentor(Long id){
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if(mentor.isPresent()) {
            mentor.get().setActive(true);
        }
        return Optional.of(mentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));
    }
    }

