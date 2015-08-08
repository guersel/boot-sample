package at.aygu.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.aygu.constants.MessageKeys;
import at.aygu.constants.SpringBeanNames;
import at.aygu.domain.User;
import at.aygu.domain.UserValidator;
import at.aygu.service.PersonService;
import at.aygu.service.RegistrationService;

/**
 * Sign up controller.
 * @author guersel
 *
 */
@Controller
public class SignupController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
	
	public static final String PATH_SIGNUP = "/signup";

    @Resource(name = SpringBeanNames.UserValidator)
    private UserValidator userValidator;

    @Resource(name = SpringBeanNames.PersonServiceImpl)
    private PersonService personService;
    
    @Resource
    private RegistrationService registrationService;
    
    @Resource
    private MessageSource validationMessageSource;

    /**
     * Show sign up view.
     */
    @RequestMapping(value = PATH_SIGNUP, method = RequestMethod.GET)
    public String showSignupForm(Model model) {
    	
    	model.addAttribute("person", new User());
    	
    	return "signup";
    }
    
    /**
     * Sign up an user. Save user information into database and send a registration email and redirect to log in view.
     */
    @RequestMapping(value = PATH_SIGNUP, method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signup(@ModelAttribute("person") User user, BindingResult result, Model model) {
        model.addAttribute("person", user);
        
        // Check if all user properties are available    	
        userValidator.validate(user, result);
        if (result.hasErrors()) {   
        	return "signup";
        }

        // Check if user with same username or email address exists
        boolean personExists = personService.existsUser(user);
        if (personExists) {
        	String message = validationMessageSource.getMessage(MessageKeys.USER_NOT_UNIQUE, new Object[]{}, LocaleContextHolder.getLocale());
        	result.rejectValue(User.PROPERTY_USERNAME, MessageKeys.USER_NOT_UNIQUE, message);
        	
        	return "signup";
        }
        
        registrationService.register(user);
                
        return "redirect:/signin";
    }

}
