package ctbu.qx.pojo;

import java.io.Serializable;
import java.util.Date;

public class Selection implements Serializable {
    private final int courseId;
    private final int studentId;
    private final Date selectDateTime;

    public Selection(int courseId, int studentId, Date selectDateTime) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.selectDateTime = selectDateTime;
    }

    @Override
    public String toString() {
        return "Selection{" +
                "课程 Id=" + courseId +
                ", 学生 Id=" + studentId +
                ", 选课时间=" + selectDateTime.toString() +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Date getSelectDateTime() {
        return selectDateTime;
    }
}
