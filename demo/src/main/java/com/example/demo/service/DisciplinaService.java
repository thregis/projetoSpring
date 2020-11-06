package com.example.demo.service;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.mapper.DisciplinaMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
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

    public Optional<List<DisciplinaDTO>> getDisciplinasInativas() {
        return Optional.of(disciplinaRepository.findByActive(false)
                .stream()
                .map(disciplinaMapper::toDisciplinaDTO)
                .collect(Collectors.toList()));
    }

    public Optional<DisciplinaDTO> getDisciplinaByIndex(Long id) {
        if (disciplinaRepository.findById(id).isPresent() && disciplinaRepository.findById(id).get().getActive()) {
            return Optional.of(disciplinaRepository.findById(id)
                    .map(disciplinaMapper::toDisciplinaDTO)).get();
        } else {
            throw new NotFoundException("Não há disciplina ativa com o ID informado.");
        }
    }

    public Optional<DisciplinaDTO> criaDisciplina(DisciplinaDTO disciplinaDTO) {
        if (disciplinaDTO.getName().length() < 2 || disciplinaDTO.getName().length() > 50 || disciplinaDTO.getDescricao().length() > 140) {

            throw new BadRequestException("Valores inválidos informados.");

        } else {
            disciplinaDTO.setActive(true);
            return Optional.of(disciplinaMapper
                    .toDisciplinaDTO(disciplinaRepository.save(disciplinaMapper.toDisciplina(disciplinaDTO))));
        }

    }

    public Optional<DisciplinaDTO> alteraDisciplina(Long id, DisciplinaDTO disciplinaDTO) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

        if (!disciplina.isPresent() || !disciplina.get().getActive()) {
            throw new NotFoundException("Não há disciplina ativa com o ID informado.");
        } else {
            if (disciplinaDTO.getName().length() < 2 || disciplinaDTO.getName().length() > 50 || disciplinaDTO.getDescricao().length() > 140) {
                throw new BadRequestException("Valores inválidos informados");
            }else {
                disciplinaDTO.setId(id);
                disciplinaDTO.setActive(true);
                return Optional.of(disciplinaMapper
                        .toDisciplinaDTO(disciplinaRepository.save(disciplinaMapper.toDisciplina(disciplinaDTO))));
            }
        }
    }

    public Optional<DisciplinaDTO> removeDisciplina(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

        if (disciplina.isPresent() && disciplina.get().getActive()) {
            disciplina.get().setActive(false);
            return Optional.of(disciplinaMapper
                    .toDisciplinaDTO(disciplinaRepository.save(disciplina.get())));
        }else{
            throw new NotFoundException("Não há disciplina ativa com o ID informado.");
        }

    }

    public Optional<DisciplinaDTO> reativaDisciplina(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

        if (disciplina.isPresent() && !disciplina.get().getActive()) {
            disciplina.get().setActive(true);
            return Optional.of(disciplinaMapper
                    .toDisciplinaDTO(disciplinaRepository.save(disciplina.get())));
        }else{
            throw new NotFoundException("Não há disciplina inativa com o ID informado.");
        }

    }
}
