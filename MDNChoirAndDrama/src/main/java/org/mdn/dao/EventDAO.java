package org.mdn.dao;

import java.text.ParseException;
import java.util.List;

import org.mdn.model.Event;

public interface EventDAO {
	String insert(Event event) throws ParseException;

	String update(Event event);

	String delete(String eventid);

	Event get(String eventid);

	List<Event> getEventList();

}
