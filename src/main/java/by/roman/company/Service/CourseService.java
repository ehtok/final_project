package by.roman.company.Service;

import by.roman.company.DTO.CourseDTO;
import by.roman.company.Entity.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAllCourses();

    Course saveCourse(CourseDTO course);

    CourseDTO findCourseById(Integer id);

    void deleteCourse(Integer id);

    Page<CourseDTO> findAllCourseWithSort(String field, String direction, int pageNumber, int pageSize);

    void addUserToCourse(Integer id);

    void removeUserToCourse(Integer id);

}
