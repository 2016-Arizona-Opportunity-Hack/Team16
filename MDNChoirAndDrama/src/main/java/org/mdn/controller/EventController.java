package org.mdn.controller;

import org.mdn.model.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 10/2/2016.
 */
@Controller
@RequestMapping("/event")
public class EventController {

    @RequestMapping(method = RequestMethod.GET)
    public String getEventForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "admin/event_form";
    }
}
