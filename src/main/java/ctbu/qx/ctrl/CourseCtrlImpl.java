package ctbu.qx.ctrl;

import ctbu.qx.mapper.CourseMapperImpl;
import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Student;

import java.util.List;

public class CourseCtrlImpl implements CourseCtrl {
    private final CourseMapperImpl courseMapper;

    public CourseCtrlImpl(CourseMapperImpl courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public boolean addCourse(Course course) {
        Course _course = courseMapper.selectCourse(course.getCourseId());
        if (_course.getCourseId() == -1) return false;
        else courseMapper.insertCourse(course);
        return true;
    }

    @Override
    public boolean updateCourse(Course course) {
        Course _course = courseMapper.selectCourse(course.getCourseId());
        if (_course.getCourseId() == -1) return false;
        else courseMapper.updateCourse(course);
        return true;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        Course _course = courseMapper.selectCourse(courseId);
        if (_course.getCourseId() == -1) return false;
        else courseMapper.deleteCourse(courseId);
        return true;
    }

    @Override
    public String showAllCourse() {
        List<Course> courseList = courseMapper.selectAllCourse();
        StringBuilder rtn = new StringBuilder();
        for (Course course: courseList) {
            rtn.append(course.toString()).append("\n");
        }

        return rtn.toString();
    }

    @Override
    public String showOneCourse(int courseId) {
        Course course = courseMapper.selectCourse(courseId);
        StringBuilder rtn = new StringBuilder();

        if (course.getCourseId() != -1) {
            rtn.append("基本信息如下：\n").append(course.toString()).append("\n").append("该课程当前学生情况如下：\n");
        }
        return rtn.toString();
    }

    @Override
    public String showAllPubCourse() {
        List<Course> courseList = courseMapper.selectAllCourseByKind("公共课");
        StringBuilder rtn = new StringBuilder();
        for (Course course: courseList) {
            rtn.append(course.toString()).append("\n");
        }

        return rtn.toString();
    }

    @Override
    public String showAllReqCourse() {
        List<Course> courseList = courseMapper.selectAllCourseByKind("必修课");
        StringBuilder rtn = new StringBuilder();
        for (Course course: courseList) {
            rtn.append(course.toString()).append("\n");
        }

        return rtn.toString();
    }

    @Override
    public String showAllSelCourse() {
        List<Course> courseList = courseMapper.selectAllCourseByKind("选修课");
        StringBuilder rtn = new StringBuilder();
        for (Course course: courseList) {
            rtn.append(course.toString()).append("\n");
        }

        return rtn.toString();
    }

    public CourseMapperImpl getCourseMapper() {
        return courseMapper;
    }
}
