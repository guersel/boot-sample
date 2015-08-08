package at.aygu.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import at.aygu.domain.User;

/**
 * Registration service.
 * @author guersel
 *
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Resource
	private PersonService personService;
	
	@Resource
	private EmailService emailService;
	
	/**
	 * Register person into system. This includes to save the person and send a confirmation email.
	 * @param user the user to register
	 */
	@Transactional
	@Override
	public final void register(final User user) {
		personService.savePerson(user);
		
		emailService.sendRegistrationConfirmation(user);
	}

}
