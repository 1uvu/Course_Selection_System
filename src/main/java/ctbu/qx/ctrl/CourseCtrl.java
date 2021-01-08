package ctbu.qx.ctrl;

import ctbu.qx.pojo.Course;

public interface CourseCtrl {
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int courseId);
    String showOneCourse(int courseId);
    String showAllCourse();  // 查看所有课程
    String showAllPubCourse();  // 查看所有公共课程
    String showAllReqCourse();  // 查看所有必修课程
    String showAllSelCourse();  // 查看所有选修课程
}
