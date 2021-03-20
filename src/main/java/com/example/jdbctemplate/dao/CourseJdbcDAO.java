package com.example.jdbctemplate.dao;

import com.example.jdbctemplate.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class CourseJdbcDAO implements DAO<Course> {

    //    private static final Logger log= LoggerFactory.getLogger(CourseJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;

    public CourseJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Course> rowMapper = (rs, rowNum) -> {
        Course course = Course.builder()
                .courseId(rs.getInt("course_id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .link(rs.getString("link"))
                .build();
        return course;
    };

    @Override
    public List<Course> list() {
        String sql = "SELECT * from course";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT INTO course(title, description, link) VALUES(?,?,?)";
        int update = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink());
        if (update == 1) {
            log.info("Created new course: " + course.getTitle());
        }
    }

    @Override
    public Optional<Course> get(int id) {
        String sql = "SELECT * FROM course WHERE course_id=?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(sql, rowMapper, id);
            System.out.println(course);
        } catch (DataAccessException e) {
            log.info("Course not found: " + id);
        }
        return Optional.ofNullable(course);
    }

    @Override
    public void update(Course course, int id) {
        String sql = "update course set title = ?, description = ?, link = ? WHERE course_id = ?";
        int update = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink(), id);
        if (update == 1) {
            log.info("Course was updated: " + course.getTitle());
        }
    }

    @Override
    public void delete(int id) {
        Optional<Course> course = get(id);
        Course getCourse = course.get();
        System.out.println("Delete course: "+getCourse.getTitle());
        jdbcTemplate.update("DELETE FROM course WHERE course_id=?", id);
    }
}
