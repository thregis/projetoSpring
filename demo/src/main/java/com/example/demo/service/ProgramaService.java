package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.dto.mapper.ProgramaMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Programa;
import com.example.demo.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ProgramaMapper programaMapper;

    @Autowired
    private MentorService mentorService;

    @Transactional(readOnly = true)
    public Optional<List<ProgramaDTO>> getProgramas() {
        return Optional.of(programaRepository.findByActive(true)
                .stream()
                .map(programaMapper::toProgramaDTO)
                .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public Optional<List<ProgramaDTO>> getProgramasInativos() {
        return Optional.of(programaRepository.findByActive(false)
                .stream()
                .map(programaMapper::toProgramaDTO)
                .collect(Collectors.toList()));
    }

    @Transactional
    public Optional<ProgramaDTO> getProgramaByIndex(Long id)/* throws Exception */ {
        if (programaRepository.findById(id).isPresent() && programaRepository.findById(id).get().getActive()) {
            return Optional.of(programaRepository.findById(id)
                    .map(programaMapper::toProgramaDTO)).get();
        }
        throw new NotFoundException("Não há programa ativo com o ID informado.");
    }

    @Transactional
    public Optional<ProgramaDTO> criaPrograma(ProgramaDTO programaDTO) {
        if (programaDTO.getName().length() < 3 || programaDTO.getName().length() > 50) {
            throw new BadRequestException("Valores inválidos informados");
        }
        programaDTO.setActive(true);
        return Optional.of(programaMapper
                .toProgramaDTO(programaRepository.save(programaMapper.toPrograma(programaDTO))));
    }

    @Transactional
    public Optional<ProgramaDTO> alteraPrograma(Long id, ProgramaDTO programaDTO)/* throws Exception */ {
        Optional<Programa> programa = programaRepository.findById(id);

        if (!programa.isPresent() || !programa.get().getActive()) {
            throw new NotFoundException("Não há programa ativo com o ID informado");
        } else {
            if (programaDTO.getName().length() < 3 || programaDTO.getName().length() > 50) {
                throw new BadRequestException("Valores inválidos informados");
            }
            programaDTO.setId(id);
            programaDTO.setActive(true);
            return Optional.of(programaMapper
                    .toProgramaDTO(programaRepository.save(programaMapper.toPrograma(programaDTO))));
        }
    }

    @Transactional
    public Optional<ProgramaDTO> removePrograma(Long id) {
        Optional<Programa> programa = programaRepository.findById(id);

        if (programa.isPresent() && programa.get().getActive()/* && programa.get().getMentores()==null*/) {
            programa.get().setActive(false);
            return Optional.of(programaMapper
                    .toProgramaDTO(programaRepository.save(programa.get())));
        }
        throw new NotFoundException("Não há programa ativo com o ID informado");
        //Pensar como lançar exceção pra não deixar excluir se tiver alunos vinculados ao programa
    }

    @Transactional
    public Optional<ProgramaDTO> reativaPrograma(Long id) {
        Optional<Programa> programa = programaRepository.findById(id);
        if (programa.isPresent() && !programa.get().getActive()) {
            programa.get().setActive(true);
            return Optional.of(programaMapper
                    .toProgramaDTO(programaRepository.save(programa.get())));
        }
        throw new NotFoundException("Não há programa inativo com o ID informado");
    }
}
