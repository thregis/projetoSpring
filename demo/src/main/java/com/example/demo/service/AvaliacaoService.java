package com.example.demo.service;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.mapper.AvaliacaoMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Transactional(readOnly = true)
    public Optional<Page<AvaliacaoDTO>> getAvaliacoes(Pageable pageable) {
        return Optional.of(avaliacaoRepository.findByActive(true, pageable)
                .map(avaliacaoMapper::toAvaliacaoDTO));
    }

    @Transactional(readOnly = true)
    public Optional<Page<AvaliacaoDTO>> getAvaliacoesInativas(Pageable pageable) {
        return Optional.of(avaliacaoRepository.findByActive(false, pageable)
                .map(avaliacaoMapper::toAvaliacaoDTO));
    }

    @Transactional
    public Optional<AvaliacaoDTO> getAvaliacaoByIndex(Long id) {
        if (avaliacaoRepository.findById(id).isPresent() && avaliacaoRepository.findById(id).get().getActive()) {
            return Optional.of(avaliacaoRepository.findById(id)
                    .map(avaliacaoMapper::toAvaliacaoDTO)).get();
        }
        throw new NotFoundException("Não há avaliação ativa com o ID informado");
    }

    @Transactional
    public Optional<AvaliacaoDTO> criaAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        if (avaliacaoDTO.getNota() == null || !mentoriaService.getMentoriaByIndex(avaliacaoDTO.getMentoriaId()).isPresent() || !disciplinaService.getDisciplinaByIndex(avaliacaoDTO.getDisciplinaId()).isPresent()) {
            throw new BadRequestException("Nota da avaliação precisa ser inserida");
        }
        avaliacaoDTO.setActive(true);
        return Optional.of(avaliacaoMapper
                .toAvaliacaoDTO(avaliacaoRepository.save(avaliacaoMapper.toAvaliacao(avaliacaoDTO))));
    }

    @Transactional
    public Optional<AvaliacaoDTO> alteraAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (!avaliacao.isPresent() || !avaliacao.get().getActive()) {
            throw new NotFoundException("Não há avaliação ativa com o ID informado.");
        } else {
            if (avaliacaoDTO.getNota() == null || !mentoriaService.getMentoriaByIndex(avaliacaoDTO.getMentoriaId()).isPresent() || !disciplinaService.getDisciplinaByIndex(avaliacaoDTO.getDisciplinaId()).isPresent()) {
                throw new BadRequestException("Dados inválidos foram inseridos");
            }
            avaliacaoDTO.setId(id);
            avaliacaoDTO.setActive(true);
            return Optional.of(avaliacaoMapper
                    .toAvaliacaoDTO(avaliacaoRepository.save(avaliacaoMapper.toAvaliacao(avaliacaoDTO))));
        }
    }

    @Transactional
    public Optional<AvaliacaoDTO> removeAvaliacao(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (avaliacao.isPresent() && avaliacao.get().getActive()) {
            avaliacao.get().setActive(false);
            return Optional.of(avaliacaoMapper.toAvaliacaoDTO(avaliacaoRepository.save(avaliacao.get())));
        }
        throw new NotFoundException("Não há avaliação ativa com o ID informado");
    }

    @Transactional
    public Optional<AvaliacaoDTO> reativaAvaliacao(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (avaliacao.isPresent() && !avaliacao.get().getActive() && avaliacao.get().getMentoria().getActive() && avaliacao.get().getDisciplina().getActive()) {
            avaliacao.get().setActive(true);
            return Optional.of(avaliacaoMapper.toAvaliacaoDTO(avaliacaoRepository.save(avaliacao.get())));
        }
        throw new NotFoundException("Não há avaliação inativa com o ID informado");
    }

    @Transactional
    public void setActiveByMentoria(Boolean active, Long id) {
        avaliacaoRepository.setActiveByMentoria(active, id);
    }

    @Transactional
    public void setActiveByAluno(Boolean active, Long id){
        avaliacaoRepository.setActiveByAluno(active, id);
    }

    @Transactional
    public void setActiveByMentor(Boolean active, Long id){
        avaliacaoRepository.setActiveByMentor(active, id);
    }
}
