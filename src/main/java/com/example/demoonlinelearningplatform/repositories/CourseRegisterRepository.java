package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.CourseRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegisterRepository extends JpaRepository<CourseRegister, Long> {

    boolean existsAllByIdCourseAndIdUserRegister(Long idCourse, Long idUserRegister);

    List<CourseRegister> findAllByIdUserRegister(Long idUserRegister);

    CourseRegister findByIdCourseAndIdUserRegister(Long idCourse, Long idUserRegister);
}
