package com.example.demo.repository;

import com.example.demo.model.Mentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {

    List<Mentoria> findByActive(Boolean active);

    @Modifying
    @Query(value = "update mentoria set mentoriaActive = ?1 where mentoriaAluno in (select alunoId from aluno where mentoriaId = ?2)", nativeQuery = true)
    void setActiveByAluno(Boolean active, Long id);


    @Modifying
    @Query(value = "update mentoria set mentoriaActive = ?1 where mentoriaMentor in (select mentorId from mentor where mentoriaId = ?2)", nativeQuery = true)
    void setActiveByMentor(Boolean active, Long id);

}
