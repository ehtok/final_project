package by.roman.company.Converter.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.DTO.CourseDTO;
import by.roman.company.Entity.Course;
import by.roman.company.Enum.LocationEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CourseConverterImpl implements Converter<Course, CourseDTO> {

    @Override
    public CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .dateStart(String.valueOf(course.getDateStart()))
                .dateFinish(String.valueOf(course.getDateFinish()))
                .description(course.getDescription())
                .location(course.getLocation().getValue())
                .companyName(course.getCompany() == null ? null : course.getCompany().getName())
                .build();
        return courseDTO;
    }

    @Override
    public List<CourseDTO> toListDTO(List<Course> courses) {
        return courses.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Course toEntity(CourseDTO courseDTO) {
        Course course = Course.builder()
                .id(courseDTO.getId())
                .name(courseDTO.getName())
                .dateStart(LocalDate.parse(courseDTO.getDateStart()))
                .dateFinish(LocalDate.parse(courseDTO.getDateFinish()))
                .description(courseDTO.getDescription())
                .location(LocationEnum.valueOf(courseDTO.getLocation()))
                .company(courseDTO.getCompany())
                .build();
        return course;
    }
}
