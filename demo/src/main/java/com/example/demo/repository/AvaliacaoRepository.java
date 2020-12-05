package com.example.demo.repository;

import com.example.demo.model.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    //List<Avaliacao> findByActive(Boolean active);
    Page<Avaliacao> findByActive(Boolean active, Pageable pageable);

    @Modifying
    @Query(value = "update avaliacao set avaliacaoActive = ?1 where avaliacaoMentoria in (select mentoriaId from mentoria where mentoriaId = ?2)", nativeQuery = true)
    void setActiveByMentoria(Boolean active, Long id);

    @Modifying
    @Query(value = "update avaliacao set avaliacaoActive = ?1 where avaliacaoMentoria in (select mentoriaId from mentoria where mentoriaAluno = ?2)", nativeQuery = true)
    void setActiveByAluno(Boolean active, Long id);

    @Modifying
    @Query(value = "update avaliacao set avaliacaoActive = ?1 where avaliacaoMentoria in (select mentoriaId from mentoria where mentoriaMentor = ?2)", nativeQuery = true)
    void setActiveByMentor(Boolean active, Long id);
}
