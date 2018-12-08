package com.cafe24.websample.web.welcome;

import com.cafe24.websample.web.welcome.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @Autowired
    WelcomeService welcomeService;

    /**
     * @url localhost/welcome/hello.ws
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/hello.ws", method = RequestMethod.GET)
    public String hello(Model model) throws Exception {
        List<WelcomeVO> list = welcomeService.selectWelcomeList();
        model.addAttribute("name", list.get(0).getUsername());
        model.addAttribute("title", "Welcome");
        return "welcome";
    }

    /**
     * @url localhost/welcome/content.ws
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/content.ws", method = RequestMethod.GET)
    public String content(Model model) throws Exception {
        return "content";
    }

    @RequestMapping(value = "/multi.ws", method = RequestMethod.GET)
    public String multiTest(Model model) throws Exception {
        welcomeService.multiThreadTest();
        return "content";
    }
}