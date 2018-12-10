package com.cafe24.websample.web.welcome;

import com.cafe24.websample.web.welcome.vo.WelcomeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WelcomeService {

    private static final Logger log = LoggerFactory.getLogger(WelcomeService.class);

    @Autowired
    private WelcomeMapper welcomeMapper;


    public List<WelcomeVO> selectWelcomeList() throws Exception {
        WelcomeVO welcomeVO = new WelcomeVO();
        List<WelcomeVO> WelcomeList = welcomeMapper.selectWelcomeList(welcomeVO);

        for(WelcomeVO vo : WelcomeList) {
            log.debug("name : " + vo.getUsername() +  " / created at : " + vo.getCreatedAt());
        }
        return WelcomeList;
    }

    //@Async("fooExecutor")
    public List<WelcomeVO>  multiThreadTest() throws  Exception {

        WelcomeVO welcomeVO = new WelcomeVO();
        List<WelcomeVO> WelcomeList = welcomeMapper.selectWelcomeList(welcomeVO);

        for(WelcomeVO vo : WelcomeList) {
            log.debug("name : " + vo.getUsername() +  " / created at : " + vo.getCreatedAt());
        }
        return WelcomeList;
    }

    @Transactional
    public String transactionalTest() throws  Exception {
        String ret = "TEST";
        WelcomeVO welcomeVO = new WelcomeVO();
        List<WelcomeVO> WelcomeList = welcomeMapper.selectWelcomeList(welcomeVO);

        for(WelcomeVO vo : WelcomeList) {
            log.debug("name : " + vo.getUsername() +  " / created at : " + vo.getCreatedAt());
        }
        return ret;
    }
}
