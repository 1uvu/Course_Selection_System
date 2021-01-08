package ctbu.qx.ctrl;

import ctbu.qx.mapper.CourseMapper;
import ctbu.qx.mapper.CourseMapperImpl;
import ctbu.qx.mapper.StudentMapper;
import ctbu.qx.mapper.StudentMapperImpl;
import org.apache.ibatis.annotations.Param;

public interface SelectionCtrl {
    String showStudentInfo(int studentId, CourseMapperImpl courseMapper);  // 查看当前学生选课情况，select
    String showCourseInfo(int courseId, StudentMapperImpl studentMapper);  // 查看当前课程被选情况，select
    boolean selectCourse(@Param("courseId") int courseId, @Param("studentId")int studentId);  // 选择课程
}
