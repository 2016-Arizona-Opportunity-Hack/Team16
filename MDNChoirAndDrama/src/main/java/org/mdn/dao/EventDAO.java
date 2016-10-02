package org.mdn.dao;

import java.text.ParseException;
import java.util.ArrayList;

import org.mdn.model.Event;

public interface EventDAO {
	String insert(Event event) throws ParseException;

	String update(Event event);

	String delete(String eventid);

	Event get(String eventid);

	ArrayList<String> getEventList();

}
