package com.example.jdbctemplate;

import com.example.jdbctemplate.dao.DAO;
import com.example.jdbctemplate.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JdbcTemplateApplication {

//    private static DAO<Course> dao;
//
//    public JdbcTemplateApplication(DAO<Course> dao) {
//        this.dao=dao;
//    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateApplication.class, args);

//        System.out.println("\n Create course -----------------------\n");
//        Course springCourse = Course.builder()
//                .title("Spring Boot")
//                .description("New Course")
//                .link("https://www.danvega.dev/corses")
//                .build();
//        dao.create(springCourse);
//
//        System.out.println("\n Get course -----------------------\n");
//        dao.get(1);
//
//        System.out.println("\n Update course -----------------------\n");
//        springCourse.setDescription("SuperCourse");
//        dao.update(springCourse, 6);
//
//        System.out.println("\n Delete course -----------------------\n");
//        dao.delete(5);
//
//        System.out.println("\n All courses -----------------------\n");
//        List<Course> courses = dao.list();
//        for (Course course : courses) {
//            System.out.println(course);
//        }
    }

}
