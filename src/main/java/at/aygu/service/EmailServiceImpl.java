package at.aygu.service;

import org.springframework.stereotype.Service;

import at.aygu.domain.User;

/**
 * Email service.
 * @author guersel
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	/**
	 * Send an email with registration information to the given user. 
	 * The email contains also an URL which must be opened by the user to finish the registration process.  
	 * @param user the receiver of the registration email
	 */
	@Override
	public void sendRegistrationConfirmation(User user) {
		// TODO
	}

}
