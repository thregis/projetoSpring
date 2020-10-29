package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    //private List<String> alunos = new ArrayList<String>(List.of("Paulo", "Paulo Amaral", "Paulo Silva", "Paulo Amaral Silva"));

    public Optional<List<AlunoDTO>> getAlunos(){
        return Optional.of(alunoRepository.findByActive(true)
                .stream()
                .map(AlunoMapper::toAlunoDTO)
                .collect(Collectors.toList()));
    }

    public Optional<AlunoDTO> getAlunoByIndex(Long id)/* throws Exception */ {
        if (alunoRepository.findById(id).get().getActive()) {
            return Optional.of(alunoRepository.findById(id)
                    .map(AlunoMapper::toAlunoDTO))
                    .orElse(Optional.empty());
        } else {
            return Optional.empty();
        }
    }

    public Optional<AlunoDTO> criaAluno(AlunoDTO alunoDTO) {
        alunoDTO.setActive(true);
        return Optional.of(AlunoMapper
                .toAlunoDTO(alunoRepository.save(AlunoMapper.toAluno(alunoDTO))));
    }

    public Optional<AlunoDTO> removeAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            aluno.get().setActive(false);
        }
        return Optional.of(AlunoMapper.toAlunoDTO(alunoRepository.save(aluno.get())));
    }

    public void alteraAluno(Long id, Aluno aluno) throws Exception{
        Optional<Aluno> alunoOpc = alunoRepository.findById(id);
        if (!alunoOpc.isPresent()){
            throw new Exception("id "+id+" não encontrado.");
        }
        aluno.setId(id);
        alunoRepository.save(aluno);
    }
    }

