package com.example.demo.service;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.mapper.AvaliacaoMapper;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
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

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private DisciplinaService disciplinaService;

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
        if (avaliacaoRepository.findById(id).isPresent() && avaliacaoRepository.findById(id).get().getActive()) {
            return Optional.of(avaliacaoRepository.findById(id)
                    .map(avaliacaoMapper::toAvaliacaoDTO)).get();
        } else {
            throw new NotFoundException("Não há avaliação ativa com o ID informado");
        }
    }

    public Optional<AvaliacaoDTO> criaAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        if(avaliacaoDTO.getNota()== null || !mentoriaService.getMentoriaByIndex(avaliacaoDTO.getMentoriaId()).isPresent() || !disciplinaService.getDisciplinaByIndex(avaliacaoDTO.getDisciplinaId()).isPresent()){
            throw new BadRequestException("Nota da avaliação precisa ser inserida");
        }else{
            avaliacaoDTO.setActive(true);
            return Optional.of(avaliacaoMapper
                    .toAvaliacaoDTO(avaliacaoRepository.save(avaliacaoMapper.toAvaliacao(avaliacaoDTO))));
        }
    }

    public Optional<AvaliacaoDTO> alteraAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO){
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (!avaliacao.isPresent()|| !avaliacao.get().getActive()){
            throw new NotFoundException("Não há avaliação ativa com o ID informado.");
        }else{
            if(avaliacaoDTO.getNota()== null || !mentoriaService.getMentoriaByIndex(avaliacaoDTO.getMentoriaId()).isPresent() || !disciplinaService.getDisciplinaByIndex(avaliacaoDTO.getDisciplinaId()).isPresent()){
                throw new BadRequestException("Nota da avaliação precisa ser inserida");
            }else{
                avaliacaoDTO.setId(id);
                avaliacaoDTO.setActive(true);
                return Optional.of(avaliacaoMapper
                        .toAvaliacaoDTO(avaliacaoRepository.save(avaliacaoMapper.toAvaliacao(avaliacaoDTO))));
            }
        }
    }

    public Optional<AvaliacaoDTO> removeAvaliacao(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if(avaliacao.isPresent() && avaliacao.get().getActive()){
            avaliacao.get().setActive(false);
            return Optional.of(avaliacaoMapper.toAvaliacaoDTO(avaliacaoRepository.save(avaliacao.get())));
        }else{
            throw new NotFoundException("Não há avaliação ativa com o ID informado");
        }
    }

    public Optional<AvaliacaoDTO> reativaAvaliacao(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if(avaliacao.isPresent() && !avaliacao.get().getActive() && avaliacao.get().getMentoria().getActive() && avaliacao.get().getDisciplina().getActive()){
            avaliacao.get().setActive(true);
            return Optional.of(avaliacaoMapper.toAvaliacaoDTO(avaliacaoRepository.save(avaliacao.get())));
        }else{
            throw new NotFoundException("Não há avaliação inativa com o ID informado");
        }

    }

    public void setActiveByMentoria(Boolean active, Long id){
        avaliacaoRepository.setActiveByMentoria(active, id);
    }
}
