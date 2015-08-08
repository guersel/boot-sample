package at.aygu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/components")
public class ComponentsController {
	
	@RequestMapping(value = "sortableTable", method = RequestMethod.GET)
	public String sortableTable() {
		return "components/sortableTable";
	}
	
	@RequestMapping(value = "signinBox", method = RequestMethod.GET)
	public String signinBox() {
		return "components/signinBox";
	}
}
