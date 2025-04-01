package com.example.ms.teachers.management.api.domain.repositories;

import com.example.ms.teachers.management.api.domain.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
