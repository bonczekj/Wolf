package com.wolf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jirka on 25.01.2017.
 */
@Controller
public class WebController {
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }

}
