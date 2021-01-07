package ctbu.qx.mapper;

import ctbu.qx.pojo.Selection;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class SelectionMapperImpl extends SqlSessionDaoSupport implements SelectionMapper {
    public Selection selectSelection(int courseId, int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        return mapper.selectSelection(courseId, studentId);
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
}
