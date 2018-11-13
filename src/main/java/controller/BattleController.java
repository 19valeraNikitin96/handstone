package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/battle/wait")
public class BattleController {

    @GetMapping
    public ModelAndView waiting() {

        ModelAndView out = new ModelAndView("wait");
        return out;

    }
}