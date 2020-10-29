package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    public Optional<List<ProgramaDTO>> getProgramas() {
        return Optional.of(programaRepository.findByActive(true)
                                .stream()
                                .map(ProgramaMapper::toProgramaDTO)
                                .collect(Collectors.toList()));
    }

    public Optional<ProgramaDTO> getProgramaByIndex(Long id)/* throws Exception */{
        if(programaRepository.findById(id).get().getActive()){
            return Optional.of(programaRepository.findById(id)
                            .map(ProgramaMapper::toProgramaDTO))
                            .orElse(Optional.empty());
        }
        return Optional.empty();
    }

    public Optional<ProgramaDTO> addPrograma(ProgramaDTO programaDTO) {
        programaDTO.setActive(true);
        return Optional.of(ProgramaMapper
                .toProgramaDTO(programaRepository.save(ProgramaMapper.toPrograma(programaDTO))));
    }

    public Optional<ProgramaDTO> alteraPrograma(Long id, ProgramaDTO programaDTO)/* throws Exception */{
        Optional<Programa> programa = programaRepository.findById(id);
        if(!programa.isPresent()){
            return Optional.empty();
        }else {
            programaDTO.setId(id);
            programaDTO.setActive(true);
            return Optional.of(ProgramaMapper
                    .toProgramaDTO(programaRepository.save(ProgramaMapper.toPrograma(programaDTO))));
        }
    }

    public Optional<ProgramaDTO> removePrograma(Long id) {
        Optional<Programa> programa = programaRepository.findById(id);
        if(programa.isPresent()){
           programa.get().setActive(false);
        }
        return Optional.of(ProgramaMapper
                .toProgramaDTO(programaRepository.save(programa.get())));
    }

    public Optional<ProgramaDTO> reativaPrograma(Long id){
        Optional<Programa> programa = programaRepository.findById(id);
        if(programa.isPresent()){
            programa.get().setActive(true);
        }
        return Optional.of(ProgramaMapper
                .toProgramaDTO(programaRepository.save(programa.get())));
    }
}
