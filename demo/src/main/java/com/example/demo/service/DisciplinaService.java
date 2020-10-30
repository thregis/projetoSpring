package com.example.demo.service;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.mapper.DisciplinaMapper;
import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    public Optional<List<DisciplinaDTO>> getDisciplinas() {
        return Optional.of(disciplinaRepository.findByActive(true)
                .stream()
                .map(disciplinaMapper::toDisciplinaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<DisciplinaDTO> getDisciplinaByIndex(Long id){
        if(disciplinaRepository.findById(id).get().getActive()){
            return Optional.of(disciplinaRepository.findById(id)
                    .map(disciplinaMapper::toDisciplinaDTO))
                    .orElse(Optional.empty());
        }
        return Optional.empty();
    }

    public Optional<DisciplinaDTO> criaDisciplina(DisciplinaDTO disciplinaDTO) {
        disciplinaDTO.setActive(true);
        return Optional.of(disciplinaMapper
                .toDisciplinaDTO(disciplinaRepository.save(disciplinaMapper.toDisciplina(disciplinaDTO))));
    }

    public Optional<DisciplinaDTO> alteraDisciplina(Long id, DisciplinaDTO disciplinaDTO){
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        if(!disciplina.isPresent()){
            return Optional.empty();
        }else {
            disciplinaDTO.setId(id);
            disciplinaDTO.setActive(true);
            return Optional.of(disciplinaMapper
                    .toDisciplinaDTO(disciplinaRepository.save(disciplinaMapper.toDisciplina(disciplinaDTO))));
        }
    }

    public Optional<DisciplinaDTO> removeDisciplina(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        if(disciplina.isPresent()){
            disciplina.get().setActive(false);
        }
        return Optional.of(disciplinaMapper
                .toDisciplinaDTO(disciplinaRepository.save(disciplina.get())));
    }

    public Optional<DisciplinaDTO> reativaDisciplina(Long id){
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        if(disciplina.isPresent()){
            disciplina.get().setActive(true);
        }
        return Optional.of(disciplinaMapper
                .toDisciplinaDTO(disciplinaRepository.save(disciplina.get())));
    }
}
