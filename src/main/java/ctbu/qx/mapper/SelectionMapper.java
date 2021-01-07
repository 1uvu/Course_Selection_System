package ctbu.qx.mapper;

import ctbu.qx.pojo.Selection;
import ctbu.qx.pojo.Student;
import org.apache.ibatis.annotations.Param;

public interface SelectionMapper {
    Selection selectSelection(@Param("courseId") int courseId, @Param("studentId")int studentId);
    void insertSelection(Selection selection);
    void deleteSelection(@Param("courseId") int courseId, @Param("studentId")int studentId);
}
