package at.aygu.dto;

import java.time.LocalDate;
import java.util.Date;

public class PersonDto {
	
	private String firstname;
	
	private String lastname;
	
	private LocalDate birthday;
	
	public PersonDto(String firstname, String lastname, LocalDate birthday) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
}
