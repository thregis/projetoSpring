package com.example.demo.service;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.mapper.AvaliacaoMapper;
import com.example.demo.model.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    public Optional<List<AvaliacaoDTO>> getAvaliacoes(){
        return Optional.of(avaliacaoRepository.findByActive(true)
                .stream()
                .map(avaliacaoMapper::toAvaliacaoDTO)
                .collect(Collectors.toList()));
    }

    public Optional<List<AvaliacaoDTO>> getAvaliacoesInativas(){
        return Optional.of(avaliacaoRepository.findByActive(false)
                .stream()
                .map(avaliacaoMapper::toAvaliacaoDTO)
                .collect(Collectors.toList()));
    }

    public Optional<AvaliacaoDTO> getAvaliacaoByIndex(Long id){
        if (avaliacaoRepository.findById(id).get().getActive()) {
            return Optional.of(avaliacaoRepository.findById(id)
                    .map(avaliacaoMapper::toAvaliacaoDTO))
                    .orElse(Optional.empty());
        } else {
            return Optional.empty();
        }
    }

    public Optional<AvaliacaoDTO> criaAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        avaliacaoDTO.setActive(true);
        return Optional.of(avaliacaoMapper
                .toAvaliacaoDTO(avaliacaoRepository.save(avaliacaoMapper.toAvaliacao(avaliacaoDTO))));
    }

    public Optional<AvaliacaoDTO> alteraAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO){
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if (!avaliacao.isPresent()){
            return Optional.empty();
        }
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setActive(true);
        return Optional.of(avaliacaoMapper
                .toAvaliacaoDTO(avaliacaoRepository.save(avaliacaoMapper.toAvaliacao(avaliacaoDTO))));

    }

    public Optional<AvaliacaoDTO> removeAvaliacao(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if(avaliacao.isPresent()){
            avaliacao.get().setActive(false);
        }
        return Optional.of(avaliacaoMapper.toAvaliacaoDTO(avaliacaoRepository.save(avaliacao.get())));
    }

    public Optional<AvaliacaoDTO> reativaAvaliacao(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if(avaliacao.isPresent()){
            avaliacao.get().setActive(true);
        }
        return Optional.of(avaliacaoMapper.toAvaliacaoDTO(avaliacaoRepository.save(avaliacao.get())));
    }

    public void setActiveByMentoria(Boolean active, Long id){
        avaliacaoRepository.setActiveByMentoria(active, id);
    }
}
