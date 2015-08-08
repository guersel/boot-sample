package at.aygu.service;

import at.aygu.domain.User;

/**
 * Registration service.
 * @author guersel
 *
 */
public interface RegistrationService {
	
	/**
	 * Register person into system. This includes to save the person and send a confirmation email.
	 * @param user the user to register
	 */
	void register(final User user);
}
