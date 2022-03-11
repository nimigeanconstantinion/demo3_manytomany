package com.example.demo_manytomany.repository;

import com.example.demo_manytomany.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("select c from Course c where c.name=?1 and c.department=?2 and c.description=?3")
    Optional<Course> findCourseByNameAndAndDepartmentAndDescription(String name,String department,String description);
}
