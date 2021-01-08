package ctbu.qx.ctrl;

import ctbu.qx.mapper.*;
import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Selection;
import ctbu.qx.pojo.Student;

import java.util.Date;
import java.util.List;

public class SelectionCtrlImpl implements SelectionCtrl{
    private final SelectionMapperImpl selectionMapper;

    public SelectionCtrlImpl(SelectionMapperImpl selectionMapper) {
        this.selectionMapper = selectionMapper;
    }

    @Override
    public String showStudentInfo(int studentId, CourseMapperImpl courseMapper) {
        // todo 调整
        List<Selection> selectionList = selectionMapper.selectSelectionByStudentId(studentId);
        StringBuilder rtn = new StringBuilder();
        for (Selection selection: selectionList) {
            Course _c = courseMapper.selectCourse(selection.getCourseId());
            rtn.append(_c.toString()).append("\n");
        }
        return rtn.toString();
    }

    @Override
    public String showCourseInfo(int courseId, StudentMapperImpl studentMapper) {
        // todo 调整
        List<Selection> selectionList = selectionMapper.selectSelectionByCourseId(courseId);
        StringBuilder rtn = new StringBuilder();
        for (Selection selection: selectionList) {
            Student _s = studentMapper.selectStudent(selection.getStudentId());
            rtn.append(_s.toString()).append("\n");
        }
        return rtn.toString();
    }

    @Override
    public boolean selectCourse(int courseId, int studentId) {
        Selection _selection = selectionMapper.selectSelection(courseId, studentId);
        if (_selection.getStudentId() != -1) return false;
        else {
            Selection selection = new Selection(courseId, studentId, new Date());
            selectionMapper.insertSelection(selection);
        }
        return true;
    }

    public SelectionMapperImpl getSelectionMapper() {
        return selectionMapper;
    }
}
