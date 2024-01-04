package com.esdproject.academiq.students;

import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentsRepository extends JpaRepository<Students, Integer>  {

}