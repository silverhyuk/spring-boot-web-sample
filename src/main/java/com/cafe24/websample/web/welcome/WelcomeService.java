package com.cafe24.websample.web.welcome;

import com.cafe24.websample.web.welcome.vo.WelcomeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class WelcomeService {

    private final WelcomeMapper welcomeMapper;

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
