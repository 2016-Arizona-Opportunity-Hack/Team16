package org.mdn.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mdn.dao.EventDAO;
import org.mdn.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAOImpl implements EventDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final String EVENT_COLLECTION = "events";

	@Override
	public String insert(Event event) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		Date strdate = formatter.parse(event.getStartTimeString());
		event.setStartTime(strdate);

		Date enddate = formatter.parse(event.getEndTimeString());
		event.setEndTime(enddate);
		mongoTemplate.insert(event, EVENT_COLLECTION);
		return event.get_id();
	}

	@Override
	public String update(Event event) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(new ObjectId(event.get_id())));
		mongoTemplate.remove(query, EVENT_COLLECTION);
		mongoTemplate.insert(event, EVENT_COLLECTION);
		return event.get_id();
	}

	@Override
	public String delete(String eventid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(new ObjectId(eventid)));
		mongoTemplate.remove(query, EVENT_COLLECTION);
		return eventid;
	}

	@Override
	public Event get(String eventid) {
		Query query = new Query(Criteria.where("_id").is(new ObjectId(eventid)));
		return mongoTemplate.findOne(query, Event.class, EVENT_COLLECTION);
	}

	@Override
	public List<Event> getEventList() {
		return mongoTemplate.findAll(Event.class, EVENT_COLLECTION);
	}

}
