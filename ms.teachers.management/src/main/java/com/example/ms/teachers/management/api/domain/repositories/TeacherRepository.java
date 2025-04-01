package com.example.ms.teachers.management.api.domain.repositories;

import com.example.ms.teachers.management.api.domain.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
