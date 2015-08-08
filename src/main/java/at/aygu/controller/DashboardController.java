package at.aygu.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.aygu.dto.PersonDto;

/**
 * The dashboard is the overview of all important information of an user.
 * @author guersel
 *
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {
	
		
	@RequestMapping(method = RequestMethod.GET)
	public String index() {				
		return "dashboard/index";
	}
	
	@RequestMapping(value = "/persons")
	@ResponseBody
	public List<PersonDto> getPersons() {
		
		List<PersonDto> persons = new ArrayList<>();
		persons.add(new PersonDto("Guersel", "Ayaz", LocalDate.of(1981, 10, 24)));
		persons.add(new PersonDto("Emrah", "Bozkaya", LocalDate.of(1981, 8, 12)));
		persons.add(new PersonDto("Peter", "Ayaz", LocalDate.of(1980, 11, 7)));
		persons.add(new PersonDto("Guersel", "Ayaz", LocalDate.of(1980, 6, 23)));
		persons.add(new PersonDto("Guersel", "Ayaz", LocalDate.of(1979, 2, 19)));
		
		
		return persons;
	}
	
}
