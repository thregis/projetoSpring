package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.exceptions.BadRequestException;
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
        if (mentorRepository.findById(id).isPresent() && mentorRepository.findById(id).get().getActive()){
            return Optional.of(mentorRepository.findById(id)
                    .map(mentorMapper::toMentorDTO)).get();
        } else{
            throw new NotFoundException("Não há mentor ativo com o ID informado.");
        }
    }

    public Optional<MentorDTO> criaMentor(MentorDTO mentorDTO){

        if((mentorDTO.getName().length()>=3 && mentorDTO.getName().length()<=50) && mentorDTO.getPais().length()<=50 && mentorDTO.getEscola().length()<=50){
            mentorDTO.setActive(true);
            return Optional.of(mentorMapper
                    .toMentorDTO(mentorRepository.save(mentorMapper.toMentor(mentorDTO))));
        }else{
            throw new BadRequestException("Valores inválidos informados.");
        }

    }

    public Optional<MentorDTO> removeMentorByIndex(Long id) {
        Optional<Mentor> mentor = mentorRepository.findById(id);

        if((mentor.isPresent()) && (mentor.get().getActive())) {
            mentor.get().setActive(false);
            mentoriaService.setActiveByMentor(false, id);
            return Optional.of(mentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));// mentorRepository.deleteById(id);
        }else{
            throw new NotFoundException("Não há mentor ativo com o ID informado.");
        }

    }

    public Optional<MentorDTO> alteraMentor(Long id, MentorDTO mentorDTO){
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if ((!mentor.isPresent()) || (!mentor.get().getActive())){
            throw new NotFoundException("Não há mentor ativo com o ID informado.");
        }else{
            if(mentorDTO.getName().length()<3 || mentorDTO.getName().length()>50 || mentorDTO.getPais().length()>50 || mentorDTO.getEscola().length()>50){
                throw new BadRequestException("Valores inválidos informados.");
            }
            mentorDTO.setId(id);
            mentorDTO.setActive(true);
            return Optional.of(mentorMapper
                    .toMentorDTO(mentorRepository.save(mentorMapper.toMentor(mentorDTO))));
        }
    }
    public Optional<MentorDTO> reativaMentor(Long id){
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if((mentor.isPresent()) && (!mentor.get().getActive())) {
            mentor.get().setActive(true);
            return Optional.of(mentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));
        }else {
            throw new NotFoundException("Não há mentor inativo com o ID informado.");
        }
    }
    }

