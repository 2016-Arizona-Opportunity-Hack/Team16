package org.mdn.dao.impl;

import org.bson.types.ObjectId;
import org.mdn.dao.PageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Repository
public class PageDAOImpl implements PageDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String insert(String json) {

		DBCollection collection = mongoTemplate.getCollection("page");
		DBObject dbObject = (DBObject) JSON.parse(json);

		collection.insert(dbObject);

		return dbObject.get("_id").toString();
	}

	@Override
	public String update(String pageid, String json) {
		DBCollection collection = mongoTemplate.getCollection("page");
		DBObject dbObject = (DBObject) JSON.parse(json);
        dbObject.put("_id", new ObjectId(pageid));
		collection.update(new BasicDBObject("_id", new ObjectId(pageid)), dbObject, true, false);
		return pageid;
	}

	@Override
	public String delete(String pageid) {
		DBCollection collection = mongoTemplate.getCollection("page");
		BasicDBObject document = new BasicDBObject();
		document.put("_id", new ObjectId(pageid));
		collection.remove(document);
		return pageid;
	}

	@Override
	public String get(String pageid) {
		DBCollection collection = mongoTemplate.getCollection("page");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(pageid));
		DBObject dbObj = collection.findOne(query);
		dbObj.put("_id", dbObj.get("_id").toString());
		return dbObj.toString();
	}

}
