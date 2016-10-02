package org.mdn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 10/2/2016.
 */
@Controller
@RequestMapping("/event")
public class EventController {

    @RequestMapping(method = RequestMethod.GET)
    public String getEventForm() {
        return "WEB-INF/pages/admin/event_form";
    }
}
