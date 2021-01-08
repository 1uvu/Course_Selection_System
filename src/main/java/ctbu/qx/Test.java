package ctbu.qx;

import ctbu.qx.mapper.CourseMapperImpl;
import ctbu.qx.mapper.SelectionMapperImpl;
import ctbu.qx.mapper.StudentMapperImpl;
import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Selection;
import ctbu.qx.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Test {
    public void test() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");
        StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
        SelectionMapperImpl selectionMapper = (SelectionMapperImpl) context.getBean("selectionMapper");

//        Course course = courseMapper.selectCourse(2);
//
//        courseMapper.deleteCourse(2);
//
//        courseMapper.insertCourse(course);
//        System.out.println(course);

        Selection selection = selectionMapper.selectSelection(1, 2);
        Course course = courseMapper.selectCourse(selection.getCourseId());
        Student student = studentMapper.selectStudent(selection.getStudentId());

        System.out.println(course.toString());
        System.out.println(student.toString());
        System.out.println(selection.toString());
        System.out.println(courseMapper.selectAllCourseByKind("公共课"));
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.test();
    }
}
