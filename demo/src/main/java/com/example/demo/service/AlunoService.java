package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private AlunoMapper alunoMapper;

    //private List<String> alunos = new ArrayList<String>(List.of("Paulo", "Paulo Amaral", "Paulo Silva", "Paulo Amaral Silva"));

    @Transactional(readOnly = true)
    public Optional<List<AlunoDTO>> getAlunos() {

        return Optional.of(alunoRepository.findByActive(true)
                .stream()
                .map(alunoMapper::toAlunoDTO)
                .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public Optional<List<AlunoDTO>> getAlunosInativos() {

        return Optional.of(alunoRepository.findByActive(false)
                .stream()
                .map(alunoMapper::toAlunoDTO)
                .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public Optional<AlunoDTO> getAlunoByIndex(Long id)/* throws Exception */ {

        if (alunoRepository.findById(id).isPresent() && alunoRepository.findById(id).get().getActive()) {
            return Optional.of(alunoRepository.findById(id)
                    .map(alunoMapper::toAlunoDTO)).get();
        }
        //early return java - tecnica para eliminação de elses
        throw new NotFoundException("Não há aluno ativo com o ID informado.");
    }

    @Transactional
    public Optional<AlunoDTO> criaAluno(AlunoDTO alunoDTO) {
        if (alunoDTO.getName().length() < 3 || alunoDTO.getName().length() > 50 || alunoDTO.getClasse().length() > 50) {
            throw new BadRequestException("Valores inválidos informados");
        }
        alunoDTO.setActive(true);
        return Optional.of(alunoMapper
                .toAlunoDTO(alunoRepository.save(alunoMapper.toAluno(alunoDTO))));

    }

    @Transactional
    public Optional<AlunoDTO> removeAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (aluno.isPresent() && (aluno.get().getActive())) {
            aluno.get().setActive(false);
            mentoriaService.setActiveByAluno(false, id);
            avaliacaoService.setActiveByAluno(false, id);
            return Optional.of(alunoMapper.toAlunoDTO(alunoRepository.save(aluno.get())));
        }
        throw new NotFoundException("Não há aluno ativo com o ID informado");
    }

    @Transactional
    public Optional<AlunoDTO> alteraAluno(Long id, AlunoDTO alunoDTO)/* throws Exception*/ {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (!aluno.isPresent() || !aluno.get().getActive()) {
            throw new NotFoundException("Não há aluno ativo com o ID informado.");
        } else {
            if (alunoDTO.getName().length() < 3 || alunoDTO.getName().length() > 50 || alunoDTO.getClasse().length() > 50) {
                throw new BadRequestException("Valores inválidos informados");
            }
            alunoDTO.setId(id);
            alunoDTO.setActive(true);
            return Optional.of(alunoMapper
                    .toAlunoDTO(alunoRepository.save(alunoMapper.toAluno(alunoDTO))));
        }
    }

    @Transactional
    public Optional<AlunoDTO> reativaAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent() && !aluno.get().getActive()) {
            aluno.get().setActive(true);
            return Optional.of(alunoMapper.toAlunoDTO(alunoRepository.save(aluno.get())));
        }
        throw new NotFoundException("Não há aluno inativo com o ID informado.");
    }
}

