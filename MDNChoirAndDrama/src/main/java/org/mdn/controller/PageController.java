package org.mdn.controller;

import org.mdn.dao.PageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/page" })
public class PageController {

	@Autowired
	PageDAO pageDAO;

	@RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
	public ResponseEntity<String> insertPage(@RequestBody String json) {

		String response = jsonifyID(pageDAO.insert(json));
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@RequestMapping(value = { "/delete/{pageid}" })
	public ResponseEntity<String> deletePage(@PathVariable("pageid") String pageid) {

		String response = jsonifyID(pageDAO.delete(pageid));
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@RequestMapping(value = { "/update/{pageid}" }, method = RequestMethod.POST)
	public ResponseEntity<String> updatePage(@RequestBody String json, @PathVariable("pageid") String pageid) {

		String response = jsonifyID(pageDAO.update(pageid, json));
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@RequestMapping(value = { "/get/{pageid}" }, method = RequestMethod.POST)
	public ResponseEntity<String> getPage(@PathVariable("pageid") String pageid) {

		String response = pageDAO.get(pageid);
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	private String jsonifyID(String id) {
		return "{\"_id\":\"" + id + "\"}";
	}

}
