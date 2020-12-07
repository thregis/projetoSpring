package com.example.demo.repository;

import com.example.demo.model.Disciplina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findListByActive(Boolean active);
    Page<Disciplina> findByActive(Boolean active, Pageable pageable);
}
