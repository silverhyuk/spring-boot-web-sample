package com.cafe24.websample.web.welcome;

import com.cafe24.websample.web.welcome.vo.WelcomeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Repository
public class WelcomeMapper {
    private static final String NAMESPACE = "com.cafe24.websample.web.welcome.WelcomeMapper";

    private final SqlSessionTemplate sqlSessionTemplate;

    public List<WelcomeVO> selectWelcomeList(WelcomeVO vo) throws Exception {
        return sqlSessionTemplate.selectList(NAMESPACE + ".selectWelcomeList", vo);
    }
}
