package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ProgramaMapper programaMapper;

    public Optional<List<ProgramaDTO>> getProgramas() {
        return Optional.of(programaRepository.findByActive(true)
                                .stream()
                                .map(programaMapper::toProgramaDTO)
                                .collect(Collectors.toList()));
    }

    public Optional<List<ProgramaDTO>> getProgramasInativos() {
        return Optional.of(programaRepository.findByActive(false)
                .stream()
                .map(programaMapper::toProgramaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<ProgramaDTO> getProgramaByIndex(Long id)/* throws Exception */{
        if(programaRepository.findById(id).get().getActive()){
            return Optional.of(programaRepository.findById(id)
                            .map(programaMapper::toProgramaDTO))
                            .orElse(Optional.empty());
        }
        return Optional.empty();
    }

    public Optional<ProgramaDTO> criaPrograma(ProgramaDTO programaDTO) {
        programaDTO.setActive(true);
        return Optional.of(programaMapper
                .toProgramaDTO(programaRepository.save(programaMapper.toPrograma(programaDTO))));
    }

    public Optional<ProgramaDTO> alteraPrograma(Long id, ProgramaDTO programaDTO)/* throws Exception */{
        Optional<Programa> programa = programaRepository.findById(id);
        if(!programa.isPresent()){
            return Optional.empty();
        }else {
            programaDTO.setId(id);
            programaDTO.setActive(true);
            return Optional.of(programaMapper
                    .toProgramaDTO(programaRepository.save(programaMapper.toPrograma(programaDTO))));
        }
    }

    public Optional<ProgramaDTO> removePrograma(Long id) {
        Optional<Programa> programa = programaRepository.findById(id);
        if(programa.isPresent()){
           programa.get().setActive(false);
        }
        return Optional.of(programaMapper
                .toProgramaDTO(programaRepository.save(programa.get())));
    }

    public Optional<ProgramaDTO> reativaPrograma(Long id){
        Optional<Programa> programa = programaRepository.findById(id);
        if(programa.isPresent()){
            programa.get().setActive(true);
        }
        return Optional.of(programaMapper
                .toProgramaDTO(programaRepository.save(programa.get())));
    }
}
