package ctbu.qx.pojo;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    private final int courseId;
    private final String courseName;
    private final String courseKind;
    private final int courseHour;
    private final int classHour;
    private final int testHour;
    private int courseCurrStudentNumber;
    private final int courseMaxStudentNumber;
    private final int courseScore;
    private final String coursePeriod;

    public Course(int courseId, String courseName, String courseKind, int courseHour, int classHour, int testHour, int courseCurrStudentNumber, int courseMaxStudentNumber, int courseScore, String coursePeriod) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseKind = courseKind;
        this.courseHour = courseHour;
        this.classHour = classHour;
        this.testHour = testHour;
        this.courseCurrStudentNumber = courseCurrStudentNumber;
        this.courseMaxStudentNumber = courseMaxStudentNumber;
        this.courseScore = courseScore;
        this.coursePeriod = coursePeriod;
    }

    public Course(int courseId, String courseName, String courseKind, int courseHour, int classHour, int testHour, int courseMaxStudentNumber, int courseScore, String coursePeriod) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseKind = courseKind;
        this.courseHour = courseHour;
        this.classHour = classHour;
        this.testHour = testHour;
        this.courseCurrStudentNumber = 0;
        this.courseMaxStudentNumber = courseMaxStudentNumber;
        this.courseScore = courseScore;
        this.coursePeriod = coursePeriod;
    }

    public void addStudentNumber() {
        this.courseCurrStudentNumber++;
    }

    public void reduceStudentNumber() {
        this.courseCurrStudentNumber--;
    }

    public void setCourseCurrStudentNumber(int courseCurrStudentNumber) {
        this.courseCurrStudentNumber = courseCurrStudentNumber;
    }

    @Override
    public String toString() {
        return "Course{" +
                "课程 Id=" + courseId +
                ", 课程名称='" + courseName + '\'' +
                ", 课程类型=" + courseKind +
                ", 课堂课时=" + courseHour +
                ", 总课时=" + classHour +
                ", 实验课时=" + testHour +
                ", 当前选课学生数目=" + courseCurrStudentNumber +
                ", 最大选课学生数=" + courseMaxStudentNumber +
                ", 学分=" + courseScore +
                ", 开课学期='" + coursePeriod + '\'' +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseKind() {
        return courseKind;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public int getClassHour() {
        return classHour;
    }

    public int getTestHour() {
        return testHour;
    }

    public int getCourseCurrStudentNumber() {
        return courseCurrStudentNumber;
    }

    public int getCourseMaxStudentNumber() {
        return courseMaxStudentNumber;
    }

    public int getCourseScore() {
        return courseScore;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }
}
