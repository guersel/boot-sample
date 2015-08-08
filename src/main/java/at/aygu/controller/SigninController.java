package at.aygu.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import at.aygu.constants.MessageKeys;
import at.aygu.domain.User;

/**
 * Login controller.
 * @author guersel
 *
 */
@Controller
@RequestMapping("/signin")
public class SigninController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SigninController.class);
	
	private static final String LOGIN_ERROR_MESSAGE = "loginErrorMessage";
	
	@Resource
	private MessageSource messageSource;
	
	/**
	 * Show sign in view.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getSigninPage(final Model model) {
		
		// Initialize form model
		model.addAttribute("person", new User());
		
		// Check for unsuccessful logging before
		String loginErrorMessage = (String) model.asMap().get("loginError");
		if (StringUtils.isNotBlank(loginErrorMessage)) {
			model.addAttribute(LOGIN_ERROR_MESSAGE, loginErrorMessage);
		}
		
		return "signin";
	}
	
	/**
	 * This method will be called by the spring container if logging was unsuccessful.
	 * Generate an error message and redirect to the login page.
	 */
	@RequestMapping(value = "/failed", method= RequestMethod.GET)
	public String signinPageAfterFailedLogin(final RedirectAttributes attributes) {
		
		String message = messageSource.getMessage(MessageKeys.SIGNIN_LOGIN_FAILED, new Object[] {}, LocaleContextHolder.getLocale());
		attributes.addFlashAttribute(LOGIN_ERROR_MESSAGE, message);
		
		return "redirect:/signin";
	}
	
}
