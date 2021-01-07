package ctbu.qx.mapper;

import ctbu.qx.pojo.Course;


public interface CourseMapper {
    Course selectCourse(int courseId);
    void insertCourse(Course course);
    void deleteCourse(int courseId);
    void updateCourse(Course course);
}
