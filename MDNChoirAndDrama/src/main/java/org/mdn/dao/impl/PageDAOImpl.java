package org.mdn.dao.impl;

import org.bson.types.ObjectId;
import org.mdn.dao.PageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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

	@Override
	public String getAllPages() {
		DBCollection collection = mongoTemplate.getCollection("page");
		DBCursor cursor = collection.find();

		// TODO Auto-generated method stub
		return c2a(cursor);
	}

	public String c2a(DBCursor cursor) {
		String results = "[";

		int i = 0;
		try {
			while (cursor.hasNext()) {
				if (i != 0)
					results += ",";
				results += cursor.next();
				++i;
			}
		} finally {
			cursor.close();
		}
		results += "]";
		return results;
	}

}
