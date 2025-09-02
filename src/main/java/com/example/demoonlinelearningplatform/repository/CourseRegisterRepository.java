package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.CourseRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegisterRepository extends JpaRepository<CourseRegister, Long> {

    boolean existsAllByIdCourseAndIdUserRegister(Long idCourse, Long idUserRegister);

    List<CourseRegister> findAllByIdUserRegister(Long idUserRegister);
}
