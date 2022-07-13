package by.roman.company.Service.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.Converter.implement.CourseConverterImpl;
import by.roman.company.DTO.CourseDTO;
import by.roman.company.Entity.Course;
import by.roman.company.Repository.CourseRepository;
import by.roman.company.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.roman.company.Service.Constant.ONE;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final Converter<Course, CourseDTO> converter = new CourseConverterImpl();

    @Override
    public List<CourseDTO> findAllCourses() {
        return converter.toListDTO(courseRepository.findAll());
    }

    @Override
    public Course saveCourse(CourseDTO course) {
        return courseRepository.save(converter.toEntity(course));
    }

    @Override
    public CourseDTO findCourseById(Integer id) {
        return converter.toDTO(courseRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Page<CourseDTO> findAllCourseWithSort(String field, String direction, int pageNumber, int pageSize) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Page<Course> courses = courseRepository.findAll(PageRequest.of(pageNumber - ONE, pageSize, sort));
        return courses.map(converter::toDTO);
    }

}
