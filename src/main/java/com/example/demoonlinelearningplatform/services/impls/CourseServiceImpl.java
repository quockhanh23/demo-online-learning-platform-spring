package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.commons.RoleConstant;
import com.example.demoonlinelearningplatform.entities.Course;
import com.example.demoonlinelearningplatform.entities.CourseLog;
import com.example.demoonlinelearningplatform.entities.Role;
import com.example.demoonlinelearningplatform.entities.User;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.CourseLogRepository;
import com.example.demoonlinelearningplatform.repositories.CourseRepository;
import com.example.demoonlinelearningplatform.repositories.UserRepository;
import com.example.demoonlinelearningplatform.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseLogRepository courseLogRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Course createCourse(Course request, Long idUser) {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) throw new InvalidException("Người dùng không tồn tại");
        Set<Role> roles = userOptional.get().getRoles();
        Role role = roles.stream()
                .filter(item -> item.getRoleName().equals(RoleConstant.ROLE_TEACHER)
                        || item.getRoleName().equals(RoleConstant.ROLE_ADMIN))
                .findFirst().orElse(null);
        if (Objects.isNull(role)) throw new InvalidException("Không có quyền");
        validateCourse(request);
        request.setStatus(CommonConstant.ACTIVE);
        try {
            Course course = courseRepository.save(request);
            createCourseLog(course, idUser);
            return course;
        } catch (Exception e) {
            throw new InvalidException(e.getMessage());
        }
    }

    void validateCourse(Course request) {
        if (Objects.isNull(request)) throw new InvalidException("Course Request null");
        if (request.getCourseName().length() > 200) throw new InvalidException("Tên khóa học không quá 200 kí tự");
        if (request.getCourseDescription().length() > 500)
            throw new InvalidException("Mô tả khóa học không quá 500 kí tự");
    }

    @Override
    public Course getDetailCourse(Long idCourse) {
        Optional<Course> courseOptional = courseRepository.findById(idCourse);
        if (courseOptional.isEmpty()) {
            throw new InvalidException("Không tìm thấy khóa học");
        }
        return courseOptional.get();
    }

    @Override
    @Transactional
    public Course updateCourse(Course request, Long idUser) {
        validateCourse(request);
        Optional<Course> courseOptional = courseRepository.findById(request.getId());
        if (courseOptional.isEmpty()) {
            throw new InvalidException("Không tìm thấy khóa học");
        }
        courseOptional.get().setCourseName(request.getCourseName());
        courseOptional.get().setCourseDescription(request.getCourseDescription());
        courseOptional.get().setStartDate(request.getStartDate());
        courseOptional.get().setEndDate(request.getEndDate());
        courseOptional.get().setUpdatedDate(new Date());
        createCourseLog(request, idUser);
        return courseOptional.get();
    }

    @Override
    public Page<Course> getAllCoursePage(Pageable pageable, String searchText) {
        Page<Course> coursePage;
        if (StringUtils.isEmpty(searchText)) {
            coursePage = courseRepository.getAllCoursePage(pageable);
        } else {
            coursePage = courseRepository.getAllCoursePage(pageable, searchText);
        }
        return coursePage;
    }

    @Override
    public Page<Course> getAllCourseByDepartment(Pageable pageable, String searchText, Long idDepartment) {
        Page<Course> coursePage;
        if (StringUtils.isEmpty(searchText)) {
            coursePage = courseRepository.getAllCourseByDepartment(pageable, idDepartment);
        } else {
            coursePage = courseRepository.getAllCourseByDepartment(pageable, searchText, idDepartment);
        }
        return coursePage;
    }

    private void createCourseLog(Course request, Long idUser) {
        CourseLog courseLog = CourseLog.builder()
                .courseName(request.getCourseName())
                .courseDescription(request.getCourseDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .createdDate(new Date())
                .updatedDate(new Date())
                .idUserAction(idUser)
                .idCourse(request.getId())
                .build();
        courseLogRepository.save(courseLog);
    }
}
