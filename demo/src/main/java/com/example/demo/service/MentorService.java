package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.model.Mentor;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

//    @Autowired
//    private ProgramaService programaservice;


    public Optional<List<MentorDTO>> getMentores() {

        return Optional.of(mentorRepository.findByActive(true)
                                .stream()
                                .map(MentorMapper::toMentorDTO)
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
                    .map(MentorMapper::toMentorDTO))
                    .orElse(Optional.empty());
        } else{
            return Optional.empty();
        }
    }

    public Optional<MentorDTO> criaMentor(MentorDTO mentorDTO) throws Exception{

//        if(programaservice.getProgramaByIndex(mentorDTO.getProgramaId())==null){
//            mentorDTO.setProgramaId(null);
//            mentorRepository.save(MentorMapper.toMentor(mentorDTO));
//            throw new Exception("id de programa adicionado não é válido.");
//        }else {
//            mentorRepository.save(MentorMapper.toMentor(mentorDTO));
//        }

//        Mentor mentor = MentorMapper.toMentor(mentorDTO);
//        Mentor savedMentor = mentorRepository.save(mentor);
//        return MentorMapper.toMentorDTO(savedMentor);

        //mentorRepository.save(MentorMapper.toMentor(mentorDTO));
        mentorDTO.setActive(true);
        return Optional.of(MentorMapper
                .toMentorDTO(mentorRepository.save(MentorMapper.toMentor(mentorDTO))));
    }

    public Optional<MentorDTO> removeMentorByIndex(Long id){
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if(mentor.isPresent()) {
            mentor.get().setActive(false);
            mentoriaService.setActiveByMentor(false, id);

        }
//        mentorRepository.findById(id).map(MentorMapper::toMentorDTO);
//        if (!mentorDTO.isPresent()){
//            throw new Exception ("id "+id+" não encontrado.");
//        }
        return Optional.of(MentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));// mentorRepository.deleteById(id);
    }

    public Optional<MentorDTO> alteraMentor(Long id, MentorDTO mentorDTO){
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if (!mentor.isPresent()){
            return Optional.empty();
        }else{
            mentorDTO.setId(id);
            mentorDTO.setActive(true);
            return Optional.of(MentorMapper
                    .toMentorDTO(mentorRepository.save(MentorMapper.toMentor(mentorDTO))));
        }
    }
    public Optional<MentorDTO> reativaMentor(Long id){
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if(mentor.isPresent()) {
            mentor.get().setActive(true);
        }
        return Optional.of(MentorMapper.toMentorDTO(mentorRepository.save(mentor.get())));
    }
    }


    //COM BANCO E DTO
//    public MentorDTO getAlunoByIndex(Long id){
//        Mentor mentor = alunoRepository.findById(id).orElse(null);
//        MentorDTO mentorDTO = new MentorDTO();
//        mentorDTO.setId(aluno.getId());
//        mentorDTO.setClasse(aluno.getC.asse());
//        mentorDTO.setName(aluno.getName());
//        mentorDTO.setAlunoName(mentor.getAluno().getName()); // aqui é o inverso. ele tava fazendo em aluno pra mostrar os mentores etc
//        return mentorDTO;
//    }


