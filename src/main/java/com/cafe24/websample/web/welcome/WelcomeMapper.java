package com.cafe24.websample.web.welcome;

import com.cafe24.websample.web.welcome.WelcomeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WelcomeMapper {
    private static final String NAMESPACE = "com.cafe24.websample.web.welcome.WelcomeMapper";
    @Autowired
    private SqlSessionTemplate sqlSession;

    public List<WelcomeVO> selectWelcomeList(WelcomeVO vo) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".selectWelcomeList", vo);
    }
}
