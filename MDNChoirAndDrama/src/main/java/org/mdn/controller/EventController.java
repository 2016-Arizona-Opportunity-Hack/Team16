package org.mdn.controller;

import org.mdn.model.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllEvents(Model model) {
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setTitle("some title");
        event.setDescription("some desc");
        events.add(event);

        events = events.stream().filter(e -> {
            if (e.getStartTime() == null) return false;
            Date currentDate = new Date();
            return e.getStartTime().after(currentDate);
        }).collect(Collectors.toList());

        model.addAttribute("events", events);
        return "admin/all_events";
    }
}
