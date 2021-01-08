package ctbu.qx.mapper;

import ctbu.qx.pojo.Course;

import java.util.List;


public interface CourseMapper {
    Course selectCourse(int courseId);
    List<Course> selectAllCourse();
    List<Course> selectAllCourseByKind(String courseKind);
    void insertCourse(Course course);
    void deleteCourse(int courseId);
    void updateCourse(Course course);
}
