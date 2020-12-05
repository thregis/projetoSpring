package com.example.demo.repository;

import com.example.demo.model.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    List<Mentor> findListByActive(Boolean active);
    Page<Mentor> findByActive(Boolean active, Pageable pageable);

}
