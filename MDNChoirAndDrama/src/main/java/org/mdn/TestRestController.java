package org.mdn;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@RequestMapping(value = "/rest/testget", method = RequestMethod.GET)
	public ResponseEntity<String> testGet(ModelMap model, Principal principal, HttpServletRequest req) {
		System.out.println("testGet" + principal.getName());
		return new ResponseEntity<String>("testGet", HttpStatus.OK);
	}

	@RequestMapping(value = "hi", method = RequestMethod.GET)
	public void test(ModelMap model) {
		System.out.println("Test");
	}

}
