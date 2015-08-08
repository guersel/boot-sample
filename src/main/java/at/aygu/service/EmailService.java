package at.aygu.service;

import at.aygu.domain.User;

/**
 * Email service.
 * @author guersel
 *
 */
public interface EmailService {
	
	/**
	 * Send an email with registration information to the given user. 
	 * The email contains also an URL which must be opened by the user to finish the registration process.  
	 * @param user the receiver of the registration email
	 */
	public void sendRegistrationConfirmation(final User user);
}
