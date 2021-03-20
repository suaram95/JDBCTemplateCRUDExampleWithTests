package com.example.jdbctemplate.dao;

import com.example.jdbctemplate.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DataJdbcTest
public class CourseDAOTest {

    private JdbcTemplate jdbcTemplate;
    private CourseJdbcDAO dao;

    @Autowired
    public CourseDAOTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        dao = new CourseJdbcDAO(jdbcTemplate);
    }

    @Test
    public void listCourses_ShouldReturnAllCourses() {
        List<Course> courses = dao.list();
        assertEquals(5, courses.size());
    }

    @Test
    public void getCourseWithValidId_ShouldReturnCourse() {
        Optional<Course> course = dao.get(1);
        assertTrue(course.isPresent());
    }

    @Test
    public void getCourseWithInvalidId() {
        Optional<Course> course = dao.get(99);
        assertFalse(course.isPresent());
    }

    @Test
    public void validCourse_ShouldBeCreated() {
        Course course = Course.builder()
                .title("test_title")
                .description("test_desc")
                .link("test_link")
                .build();

        dao.create(course);
        List<Course> cources = dao.list();

        assertEquals(6, cources.size());
        assertEquals("test_title", cources.get(5).getTitle());
        assertEquals("test_desc", cources.get(5).getDescription());
        assertEquals("test_link", cources.get(5).getLink());
    }

    @Test
    public void updateCourse_ShouldBeUpdated() {
        Course course = dao.get(1).get();
        course.setTitle("New Spring Framework course");
        dao.update(course, 1);

        Course updatedCourse = dao.get(1).get();
        assertEquals("New Spring Framework course", updatedCourse.getTitle());

    }

    @Test
    public void deleteCourse_ShouldRemoveCourse(){
        dao.delete(1);
        List<Course> list = dao.list();
        assertEquals(4, list.size());
    }
}
