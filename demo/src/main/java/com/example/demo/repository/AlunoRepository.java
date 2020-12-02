package com.example.demo.repository;

import com.example.demo.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    //List<Aluno> findByActive(Boolean active);

    Page<Aluno> findByActive(Boolean active, Pageable pageable);
}
