package org.mdn.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.mdn.dao.EventDAO;
import org.mdn.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 10/2/2016.
 */
@Controller
@RequestMapping("/event")
public class EventController {

	@Autowired
	EventDAO eventDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String getEventForm(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		return "admin/event_form";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllEvents(Model model) {
		List<Event> events = eventDAO.getEventList();

		events = events.stream().filter(e -> {
			if (e.getStartTime() == null)
				return false;
			Date currentDate = new Date();
			return e.getStartTime().after(currentDate);
		}).collect(Collectors.toList());

		model.addAttribute("events", events);
		return "admin/all_events";
	}

	@RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
	public String insertPage(Event event, Model model) throws ParseException {

		String response = eventDAO.insert(event);
		return "redirect:/event/get/" + response;

	}

	@RequestMapping(value = { "/delete/{eventid}" },method = RequestMethod.POST)
	public String deletePage(@PathVariable("eventid") String eventid) {

		eventDAO.delete(eventid);
		return "admin/all_events";

	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String updatePage(Event event, Model model) {

		String response = eventDAO.update(event);
		return "redirect:/event/get/" + response;
	}

	@RequestMapping(value = { "/get/{eventid}" }, method = RequestMethod.GET)
	public String getPage(@PathVariable("eventid") String eventid, Model model) {

		Event response = eventDAO.get(eventid);
		model.addAttribute("event", response);
		return "admin/event_details";

	}
}
