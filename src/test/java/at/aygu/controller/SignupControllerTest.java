package at.aygu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import at.aygu.test.config.TestWebappConfiguration;

/**
 * Test cases of controller {@link SignupController}.
 * @author guersel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestWebappConfiguration
public class SignupControllerTest {
	
	@Autowired
	WebApplicationContext webAppContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	/**
	 * Try to sign with missing data.
	 * Expectation: Sign up fails because of validation error
	 */
	@Test
	public void signUpWithMissingData() throws Exception {
		mockMvc.perform(post("/signup")
				.param("username", "")
				.param("email", "")
				.param("password", "")
				.param("passwordConfirmation", "")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(model().hasErrors())
				.andExpect(view().name("signup"));
	}
	
	
	
	
}
