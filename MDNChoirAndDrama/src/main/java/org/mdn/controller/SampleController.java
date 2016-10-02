package org.mdn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jaydatta on 10/2/16.
 */
@Controller
public class SampleController {


    @RequestMapping("/sample")
    public String getSample(Model model){

    model.addAttribute("name","nagarkar");


        return "sample";
    }
}
