package com.cafe24.websample.web.welcome;


import com.cafe24.websample.common.annotation.ProgressTime;
import com.cafe24.websample.common.properties.Person;
import com.cafe24.websample.web.welcome.vo.HelloVO;
import com.cafe24.websample.web.welcome.vo.WelcomeVO;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/welcome")
@Log4j2
public class WelcomeController {

    private final WelcomeService  welcomeService;
    private final Person person;
    /**
     * @url localhost/welcome/hello.ws
     * @param model
     * @return
     * @throws Exception
     */
    @ProgressTime
    @RequestMapping(value = "/hello.ws", method = RequestMethod.GET)
    public ModelAndView hello(Model model) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("welcome");
        List<WelcomeVO> list = welcomeService.selectWelcomeList();

        mav.addObject("name", list.get(0).getUsername());
        mav.addObject("title", "Welcome");

        log.debug("person : {}{} ({})",person.getLastName(), person.getFirstName(), person.getAge());

        return mav;
    }


    @RequestMapping(value = "/hello2.ws", method = RequestMethod.GET)
    public ModelAndView hello2() throws Exception {
        return getModelAndView();
    }

    @RequestMapping(value = "/hello3.ws")
    public ModelAndView hello3(@ModelAttribute("vo") HelloVO vo, HttpServletRequest request, HttpServletResponse response ) throws Exception {
        log.debug(vo);
        return getModelAndView();
    }

    private ModelAndView getModelAndView() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<WelcomeVO> list = welcomeService.selectWelcomeList();

        mav.setViewName("welcome");
        mav.addObject("name", list.get(0).getUsername());
        mav.addObject("title", "Welcome");
        return mav;
    }

    /**
     * @url localhost/welcome/content.ws
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/content.ws", method = RequestMethod.GET)
    public ModelAndView content(Model model) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content");
        return mav;
    }

    @RequestMapping(value = "/multi.ws", method = RequestMethod.GET)
    public String multiTest(Model model) throws Exception {
        welcomeService.multiThreadTest();
        return "content";
    }
}