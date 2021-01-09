package ctbu.qx.mapper;

import ctbu.qx.pojo.Selection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectionMapper {
    Selection selectSelection(@Param("courseId") int courseId, @Param("studentId")int studentId);
    List<Selection> selectSelectionByStudentId(int studentId);
    List<Selection> selectSelectionByCourseId(int courseId);
    void insertSelection(Selection selection);
    void deleteSelection(@Param("courseId") int courseId, @Param("studentId")int studentId);
    void deleteSelectionByStudentId(int studentId);
    void deleteSelectionByCourseId(int courseId);

    void insertTrigger(Selection selection);
    void deleteTrigger(int courseId, int studentId);
    void deleteByStudentIdTrigger(int studentId);
    void deleteByCourseIdTrigger(int courseId);
}
