package org.mdn.dao;

public interface PageDAO {

	String insert(String json);

	String update(String pageid, String json);

	String delete(String pageid);

	String get(String pageid);

	String getAllPages();
}
