package ctbu.qx.pojo;

import java.io.Serializable;

public class Student implements Serializable {
    private final int studentId;
    private final String studentName;
    private final int studentAge;
    private final int studentMinCourseScore;
    private int studentCurrCourseScore;
    private final int studentMaxCourseScore;
    private final int studentNeedPubCourse;
    private final int studentNeedReqCourse;
    private final int studentNeedSelCourse;

    public Student(int studentId, String studentName, int studentAge, int studentMinCourseScore, int studentCurrCourseScore, int studentMaxCourseScore, int studentNeedPubCourse, int studentNeedReqCourse, int studentNeedSelCourse) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentMinCourseScore = studentMinCourseScore;
        this.studentCurrCourseScore = studentCurrCourseScore;
        this.studentMaxCourseScore = studentMaxCourseScore;
        this.studentNeedPubCourse = studentNeedPubCourse;
        this.studentNeedReqCourse = studentNeedReqCourse;
        this.studentNeedSelCourse = studentNeedSelCourse;
    }

    public Student(int studentId, String studentName, int studentAge, int studentMinCourseScore, int studentMaxCourseScore, int studentNeedPubCourse, int studentNeedReqCourse, int studentNeedSelCourse) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentMinCourseScore = studentMinCourseScore;
        this.studentCurrCourseScore = 0;
        this.studentMaxCourseScore = studentMaxCourseScore;
        this.studentNeedPubCourse = studentNeedPubCourse;
        this.studentNeedReqCourse = studentNeedReqCourse;
        this.studentNeedSelCourse = studentNeedSelCourse;
    }

    @Override
    public String toString() {
        return "Student{" +
                "学生 Id=" + studentId +
                ", 学生名字='" + studentName + '\'' +
                ", 学生年龄=" + studentAge +
                ", 学生最少修多少学分=" + studentMinCourseScore +
                ", 学生当前修多少学分=" + studentCurrCourseScore +
                ", 学生最多修多少学分=" + studentMaxCourseScore +
                ", 学生需要最少选多少门公共课=" + studentNeedPubCourse +
                ", 学生需要最少选多少门必修课=" + studentNeedReqCourse +
                ", 学生需要最少选多少门选修课=" + studentNeedSelCourse +
                '}';
    }

    //  不提供退课功能
    public void addCourseScore(int courseScore) {
        this.studentCurrCourseScore += courseScore;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public int getStudentMinCourseScore() {
        return studentMinCourseScore;
    }

    public int getStudentCurrCourseScore() {
        return studentCurrCourseScore;
    }

    public void setStudentCurrCourseScore(int studentCurrCourseScore) {
        this.studentCurrCourseScore = studentCurrCourseScore;
    }

    public int getStudentMaxCourseScore() {
        return studentMaxCourseScore;
    }

    public int getStudentNeedPubCourse() {
        return studentNeedPubCourse;
    }

    public int getStudentNeedReqCourse() {
        return studentNeedReqCourse;
    }

    public int getStudentNeedSelCourse() {
        return studentNeedSelCourse;
    }
}
