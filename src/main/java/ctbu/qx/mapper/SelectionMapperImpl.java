package ctbu.qx.mapper;

import ctbu.qx.mapper.SelectionMapper;
import ctbu.qx.pojo.Selection;
import ctbu.qx.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.Date;
import java.util.List;

public class SelectionMapperImpl extends SqlSessionDaoSupport implements SelectionMapper {
    public Selection selectSelection(int courseId, int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        try {
            return mapper.selectSelection(courseId, studentId);
        } catch (Exception e) {
            return new Selection(-1, -1, new Date());
        }
    }

    @Override
    public List<Selection> selectSelectionByStudentId(int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        return mapper.selectSelectionByStudentId(studentId);
    }

    @Override
    public List<Selection> selectSelectionByCourseId(int courseId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        return mapper.selectSelectionByStudentId(courseId);
    }

    public void insertSelection(Selection selection) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        mapper.insertSelection(selection);
    }

    public void deleteSelection(int courseId, int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        mapper.deleteSelection(courseId, studentId);
    }

    @Override
    public void deleteSelectionByStudentId(int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        mapper.deleteSelectionByStudentId(studentId);
    }

    @Override
    public void deleteSelectionByCourseId(int courseId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        mapper.deleteSelectionByCourseId(courseId);
    }
}
